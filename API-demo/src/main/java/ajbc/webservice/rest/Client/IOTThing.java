package ajbc.webservice.rest.Client;

import java.util.List;
import java.util.UUID;

public class IOTThing extends Hardware {

	private List<Device> devices;

	public IOTThing(UUID id, Type type, String model, String manufacturer, List<Device> devices) {
		super(id, type, model, manufacturer);
		this.devices = devices;
	}


	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@Override
	public String toString() {
		return  super.toString()+" IOTThing [devices=" + devices + "]" ;
	}








}
