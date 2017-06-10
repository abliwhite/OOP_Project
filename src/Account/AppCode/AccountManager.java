package Account.AppCode;

import Account.Models.User;
import Common.AppCode.DaoController;
import Common.Models.DbAbstractModel;
import Common.Models.ResponseMessage;

public class AccountManager extends DaoController {

	public static final String ACCOUNT_MANAGER_ATTRIBUTE = "Account Manager Attribure";
	
	public AccountManager(){
		super();
	}
	
	public boolean checkLoginValidation(String name, String password) {
		for (DbAbstractModel db : dbFake) {
			User a = (User)db;
			if (a.getUsername().equals(name) && a.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public ResponseMessage updateUser(User user){
		return null;
	}
	
	public ResponseMessage updateProfile(User user){
		return null;
	}
}
