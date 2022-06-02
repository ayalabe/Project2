package ajbc.webservice.rest.DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ajbc.webservice.rest.Client.Device;
import ajbc.webservice.rest.Client.IOTThing;
import ajbc.webservice.rest.Client.Type;

public class DBMock {
	
	private static DBMock instance = null;
	private static Map<UUID, IOTThing> iotThingsMap;
	private static Map<UUID, Device> deviceMap;
	
	
	
	public static synchronized DBMock getInstance() {
		if(instance==null)
			instance = new DBMock();
		return instance;
	}
	
	public DBMock() {
		iotThingsMap = new HashMap<UUID, IOTThing>();
		deviceMap = new HashMap<UUID, Device>();
		seed();
	}
	
	private void seed() {
		UUID uuid2 = UUID.nameUUIDFromBytes("testdevice".getBytes());
		List<Device> devices = new ArrayList<Device>();
		
		devices.add(new Device(uuid2, Type.CONTROLLER, "web", "manufa", 1.7));
		devices.add(new Device(UUID.randomUUID(), Type.ACTUATOR, "sen", "actuar", 1.8));
		devices.add(new Device(UUID.randomUUID(), Type.SENSOR, "act", "manufa", 80));
		devices.add(new Device(UUID.randomUUID(), Type.CONTROLLER, "cont", "manufa", 13.7));
		UUID uuid = UUID.nameUUIDFromBytes("testname".getBytes());
		IOTThing iotThing = new IOTThing(uuid, Type.ACTUATOR, "model", "manufactor", devices);
		
		iotThingsMap.put(UUID.fromString(iotThing.getId()) , iotThing);
		
		for (Device device : devices) {
			deviceMap.put(UUID.fromString(iotThing.getId()), device);
		}
	}
	
	public Map<UUID, IOTThing> getIotThingsMap() {
		return iotThingsMap;
	}
	
	public Map<UUID, Device> getDeviceMap() {
		return deviceMap;
	}
	
	
	

}
