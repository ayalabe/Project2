package ajbc.webservice.rest.exeption;

public class MissingDataException extends RuntimeException {

	private static final long serialVersionUID = 5420708576426789075L;
	
	public MissingDataException(String msg) {
		super(msg);
	}

}
