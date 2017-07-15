package Common.Models;

import java.util.List;

public class ResponseModel<T,S> {
	
	public static final String RESPONSE_MESSAGE_ATTRIBUTE = "Response message attribute";
	
	private String resultMessage;
	private boolean isSuccess;
	private List<T> resultList;
	private S resultObject;
	

	public ResponseModel(String resultMessage,boolean isSuccess){
		this.resultMessage = resultMessage;
		this.isSuccess = isSuccess;
	}
	
	public ResponseModel(S resultObject,boolean isSuccess,String resultMessage){
		this.resultMessage = resultMessage;
		this.resultObject = resultObject;
		this.isSuccess = isSuccess;
	}
	
	public ResponseModel(List<T> resultList,boolean isSuccess,String resultMessage){
		this.resultMessage= resultMessage;
		this.resultList = resultList;
		this.isSuccess = isSuccess;
	}
	
	public ResponseModel(List<T> resultList,S resultObject,boolean isSuccess,String resultMessage){
		this.resultMessage= resultMessage;
		this.resultList = resultList;
		this.resultObject = resultObject;
		this.isSuccess = isSuccess;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}


	public List<T> getResultList() {
		return resultList;
	}


	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}


	public S getResultObject() {
		return resultObject;
	}


	public void setResultObject(S resultObject) {
		this.resultObject = resultObject;
	}
}
