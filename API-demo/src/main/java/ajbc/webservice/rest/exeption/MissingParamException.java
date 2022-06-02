package ajbc.webservice.rest.exeption;

public class MissingParamException extends RuntimeException {

	private static final long serialVersionUID = 5420708576426789075L;
	
	public MissingParamException(String msg) {
		super(msg);
	}

}
