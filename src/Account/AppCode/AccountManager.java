package Account.AppCode;

import Account.Models.User;
import Common.AppCode.DaoController;
import Common.Models.ResponseMessage;

public class AccountManager extends DaoController {

	public static final String ACCOUNT_MANAGER_ATTRIBUTE = "Account Manager Attribure";
	
	public AccountManager(){
		super();
	}
	
	public ResponseMessage checkLoginValidation(User user){
		return null;
	}
	
	public ResponseMessage updateUser(User user){
		return null;
	}
	
	public ResponseMessage updateProfile(User user){
		return null;
	}
}
