package Common.AppCode;

public class ViewTextContainer {
	
	public static final String LOGIN_VALIDATION_ERROR = "Name of password incorrect. Try again!";
	public static final String LOGIN_HEAD_VALIDATION_ERROR = "Try again.";
<<<<<<< HEAD
	
	public static final String REGISTER_PAGE_TITLE = "Create Account";
	public static final String REGISTER_PAGE_WELCOME_WARNING_MESSAGE = "Create New Account";
	public static final String REGISTER_PAGE_ERROR_COMMENT_MESSAGE = "Please enter another name and password";
	
	private String registerPageWarningError;
	
	public ViewTextContainer(String registerPageWarningError){
		this.registerPageWarningError = registerPageWarningError;
	}
	
	public String getRegisterPageWarningError(){
		return registerPageWarningError;
	}	
=======
	public static final String RESULT = "result";

>>>>>>> 7cc6bf203f1ec67af434f99612fda3e6c6ec084d
}
