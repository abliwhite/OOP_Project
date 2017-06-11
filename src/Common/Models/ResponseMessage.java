package Common.Models;

public class ResponseMessage {
	
	private String errorMessage;
	private boolean isSuccess;
	
	public ResponseMessage(String errorMessage,boolean isSuccess){
		this.errorMessage= errorMessage;
		this.isSuccess = isSuccess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}
}
