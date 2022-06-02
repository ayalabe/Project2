package ajbc.webservice.rest.DB;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import ajbc.webservice.rest.Client.Device;
import ajbc.webservice.rest.Client.IOTThing;
import ajbc.webservice.rest.Client.Type;

public class DBServiceTest {
	
	private IOTThing thing;
	private Device device;
	private UUID uuid = UUID.nameUUIDFromBytes("id".getBytes());
	private Type type = Type.ACTUATOR;
	private String model = "newModel";
	private String manufacturer = "Yapan";
	private double reading = 3.1;
	private List<Device> devices = new ArrayList<Device>();
	DBService dbService;
	private DBMock db;
	private static Map<UUID, IOTThing> iotThingsMap;
	public DBServiceTest() {
		device = new Device(uuid, type, model, manufacturer, reading );
		devices.add(device);
		thing = new IOTThing(uuid, type, model, manufacturer, devices);
		db = DBMock.getInstance();
		dbService = new DBService();
		iotThingsMap = db.getIotThingsMap();
		db.getDeviceMap();
	}
	
	@Test
	public void testGetAllIOTThing() {
		Collection<IOTThing> allThings = iotThingsMap.values();
		List<IOTThing> iotThings = new ArrayList<IOTThing>();
		iotThings.addAll(allThings);
		List<IOTThing> iotThingsReturn = dbService.getAllIOTThing();
		assertArrayEquals(iotThingsReturn.toArray(), iotThings.toArray());
	}
	
	@Test
	public void testUpdatData() {
		dbService.updatData(thing);
		assertTrue(iotThingsMap.containsKey(uuid));
		assertTrue(iotThingsMap.containsValue(thing));
	}
	

	@Test
	public void testGetIotThingById() {
		dbService.updatData(thing);
		IOTThing iotTh = dbService.getIotThingById(uuid);
		assertEquals(iotTh, thing);
	}
	
	@Test
	public void testGetDeviceById() {
		dbService.updatData(thing);
		Device deviceReturn = dbService.getDeviceById(uuid);
		assertEquals(deviceReturn, device);
	}
	
	@Test
	public void testGetIotThingByTypemodelmanufacturer() {
		List<IOTThing> iotThings = new ArrayList<IOTThing>();
		iotThings.add(thing);
		dbService.updatData(thing);
		List<IOTThing> iotThingsReturn = dbService.getIotThingByTypemodelmanufacturer(type, manufacturer, model);
		assertArrayEquals(iotThingsReturn.toArray(), iotThings.toArray());
	}
	
	@Test
	public void testGetDeviceByTypeModelManufacturerThingId() {
		dbService.updatData(thing);
		Device deviceReturn = dbService.getDeviceByTypeModelManufacturerThingId(uuid, type, manufacturer, model);
		assertEquals(deviceReturn,device);
	}
	
	
}
