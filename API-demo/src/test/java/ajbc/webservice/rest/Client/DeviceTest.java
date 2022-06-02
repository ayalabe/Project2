package ajbc.webservice.rest.Client;

import static org.junit.Assert.assertEquals;
import java.util.UUID;

import org.junit.Test;

public class DeviceTest {
	
	private Device device;
	private UUID uuid = UUID.nameUUIDFromBytes("id".getBytes());
	private Type type = Type.ACTUATOR;
	private String model = "newModel";
	private String manufacturer = "Yapan";
	private double reading = 3.1;
	
	public DeviceTest() {
		device = new Device(uuid, type, model, manufacturer, reading );
	}
	
	@Test
	public void checkConstractor() {

		assertEquals(device.getId(), uuid.toString());
		assertEquals(device.getType(), type);
		assertEquals(device.getModel(), model);
		assertEquals(device.getManufacturer(), manufacturer);
		assertEquals(device.getReading(), reading ,reading);
	}


}
