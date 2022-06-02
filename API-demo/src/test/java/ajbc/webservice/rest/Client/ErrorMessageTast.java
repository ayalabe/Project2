package ajbc.webservice.rest.Client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ErrorMessageTast {
	
	private ErrorMessage errorMessage;
	private String errorMes = "Err";
	private InternalErrorCode errorCode = InternalErrorCode.ILLEGAL_PARAMETER;
	private String docsLink = "www.google";
	
	
	public ErrorMessageTast() {
		errorMessage = new ErrorMessage(errorMes, errorCode, docsLink);
	}
	
	
	@Test
	public void checkConstractor() {

		assertEquals(errorMessage.getErrorMessage(), errorMes);
		assertEquals(errorMessage.getErrorCode(), errorCode);
		assertEquals(errorMessage.getDocsLink(), docsLink);
		
	}

}
