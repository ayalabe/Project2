package ajbc.webservice.rest.DB;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import ajbc.webservice.rest.Client.Device;
import ajbc.webservice.rest.Client.IOTThing;
import ajbc.webservice.rest.Client.Type;
import ajbc.webservice.rest.exeption.MissingDataException;
import ajbc.webservice.rest.exeption.MissingParamException;


public class DBService {

	private DBMock db;
	private static Map<UUID, IOTThing> iotThingsMap;
	private static Map<UUID, Device> deviceMap;


	public DBService() {
		db = DBMock.getInstance();
		iotThingsMap = db.getIotThingsMap();
		deviceMap = db.getDeviceMap();
	}
	

	private void updatDataDevaice(List<Device> befor, List<Device> after) {
		
		for (Device device : befor) {
			if(!after.contains(device)) {
				deviceMap.remove(device.getId());
			}
		}
		
	}

	public void updatData(IOTThing iotThing) {
		if(iotThingsMap.containsKey(iotThing.getId())) {
			updatDataDevaice(iotThingsMap.get(iotThing.getId()).getDevices(), iotThing.getDevices());
			iotThingsMap.get(iotThing.getId()).setDevices(iotThing.getDevices());
		}
		else {
			iotThingsMap.put(UUID.fromString(iotThing.getId()), iotThing);
		}
		deviceMap.putAll(iotThing.getDevices().stream()
				.collect(Collectors.toMap(d -> UUID.fromString(d.getId()), Function.identity()))); 
		
	}

	public static void printDB() {
		System.out.println("iotThingsMap");
		for (UUID key: iotThingsMap.keySet()){  
			System.out.println(key+ " = " + iotThingsMap.get(key));
		} 
		
		System.out.println("deviceMap");
		for (UUID key: deviceMap.keySet()){  
			System.out.println(key+ " = " + deviceMap.get(key));
		} 
	}

	public IOTThing getIotThingById(UUID id) {
		IOTThing iotThing = iotThingsMap.get(id);
		if(iotThing == null)
			throw new MissingDataException("iotThing with id %s is not in DB".formatted(id.toString())); 
		return iotThingsMap.get(id);
	}
	
	public List<IOTThing> getAllIOTThing(){
		Collection<IOTThing> allThings = iotThingsMap.values();
		List<IOTThing> iotThings = new ArrayList<IOTThing>();
		iotThings.addAll(allThings);
		return iotThings;
	}


	public Device getDeviceById(UUID id) {
		Device device = deviceMap.get(id);
		if(device == null)
			throw new MissingDataException("Device with id is not in DB"); 
		return deviceMap.get(id);
	}


	public List<IOTThing> getIotThingByTypemodelmanufacturer(Type type, String manufacturer, String model) {
		
		if(type == null || manufacturer == null || model == null)
			throw new MissingParamException("One or more parameters is null"); 
		
		Collection<IOTThing> allThings = iotThingsMap.values();
		List<IOTThing> iotThings = new ArrayList<IOTThing>();
		List<IOTThing> spesifIotThings = new ArrayList<IOTThing>();
		iotThings.addAll(allThings);
		for (IOTThing iotThing : allThings) {
			if(iotThing.getType().equals(type) && iotThing.getManufacturer().equals(manufacturer) && iotThing.getModel().equals(model))
				spesifIotThings.add(iotThing);
		}
		return spesifIotThings;
	}


	public Device getDeviceByTypeModelManufacturerThingId(UUID id, Type type, String manufacturer, String model) {
		
		if(type == null || manufacturer == null || model == null)
			throw new MissingParamException("One or more parameters is null"); 
		
		IOTThing iotThing = iotThingsMap.get(id);
		if(iotThing == null) {
			throw new MissingDataException("iotThing with id is not in DB"); 
		}
		
		for (Device device : iotThing.getDevices()) {
			if(device.getType().equals(type) && device.getManufacturer().equals(manufacturer) && device.getModel().equals(model))
				return device;
		}
		return null;
	}

}
