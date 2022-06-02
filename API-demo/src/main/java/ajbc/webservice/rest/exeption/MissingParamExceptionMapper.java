package ajbc.webservice.rest.exeption;


import ajbc.webservice.rest.Client.ErrorMessage;
import ajbc.webservice.rest.Client.InternalErrorCode;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
@Provider
public class MissingParamExceptionMapper implements ExceptionMapper<MissingParamException>{

	@Override
	public Response toResponse(MissingParamException e) {
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), InternalErrorCode.ILLEGAL_PARAMETER, "google.com");
		return Response.status(Status.NOT_FOUND)
		.entity(errorMessage)
		.build();
	}
}
