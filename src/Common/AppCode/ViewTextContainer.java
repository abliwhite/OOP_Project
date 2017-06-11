package Common.AppCode;

public class ViewTextContainer {
	
	public static final String LOGIN_VALIDATION_ERROR = "Name or password incorrect. Try again!";
	public static final String LOGIN_HEAD_VALIDATION_ERROR = "Try again.";

	
	public static final String REGISTER_PAGE_TITLE = "Create Account";
	public static final String REGISTER_PAGE_WELCOME_WARNING_MESSAGE = "Create New Account";
	
	private String registerPageWarningError;
	
	public ViewTextContainer(){
		registerPageWarningError = "";
	}
	
	public void setRegisterPageWarningError(String registerPageWarningError){
		this.registerPageWarningError = registerPageWarningError;
	}
	
	public String getRegisterPageWarningError(){
		return registerPageWarningError;
	}	
	public static final String RESULT = "result";


}
