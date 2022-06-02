package ajbc.webservice.rest.Client;

public class Utility {
	
	public static Type convertStringToType(String type) {
		Type typeRes = null;
		switch(type) {
			case "controller":
				typeRes = Type.CONTROLLER;
				break;
			case "sensor":
				typeRes = Type.SENSOR;
				break;
			case "actuator":
				typeRes = Type.ACTUATOR;
				break;
		}
		return typeRes;
	}

}
