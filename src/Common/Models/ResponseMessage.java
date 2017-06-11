package Common.Models;

public class ResponseMessage {
	
	private String resultMessage;
	private boolean isSuccess;
	
	public ResponseMessage(String resultMessage,boolean isSuccess){
		this.resultMessage= resultMessage;
		this.isSuccess = isSuccess;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}
}
