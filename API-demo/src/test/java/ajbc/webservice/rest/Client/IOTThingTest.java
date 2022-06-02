package ajbc.webservice.rest.Client;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

public class IOTThingTest {
	
	private IOTThing iotThing;
	private Device device;
	private UUID uuid = UUID.nameUUIDFromBytes("id".getBytes());
	private Type type = Type.ACTUATOR;
	private String model = "newModel";
	private String manufacturer = "Yapan";
	private double reading = 3.1;
	private List<Device> devices = new ArrayList<Device>();
	
	public IOTThingTest() {
		device = new Device(uuid, type, model, manufacturer, reading );
		devices.add(device);
		iotThing = new IOTThing(uuid, type, model, manufacturer, devices);
	}
	
	@Test
	public void checkConstractor() {

		assertEquals(iotThing.getId(), uuid.toString());
		assertEquals(iotThing.getType(), type);
		assertEquals(iotThing.getModel(), model);
		assertEquals(iotThing.getManufacturer(), manufacturer);
		assertArrayEquals(iotThing.getDevices().toArray(), devices.toArray());
		
	}
}
