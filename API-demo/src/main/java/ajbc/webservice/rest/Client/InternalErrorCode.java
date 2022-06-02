package ajbc.webservice.rest.Client;

public enum InternalErrorCode {

	INVALID_ID(1234),
	
	ILLEGAL_PARAMETER(5678);

	private int codeNum;

	private InternalErrorCode(int codeNum) {
		this.codeNum = codeNum;
	}
	public int getCodeNum() {
		return codeNum;
	}
}
