package ajbc.webservice.rest.Client;

import java.util.UUID;

public class Device extends Hardware {
	
	private double reading;

	public Device(UUID id, Type type, String model, String manufacturer, double reading) {
		super(id, type, model, manufacturer);
		this.setReading(reading);
	}
	
	public Device() {
		
	}

	public double getReading() {
		return reading;
	}

	public void setReading(double reading) {
		this.reading = reading;
	}

	@Override
	public String toString() {
		return "Device [reading=" + reading + "]";
	}
	
	

}
