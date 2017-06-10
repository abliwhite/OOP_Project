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
	
	public User checkLoginValidation(String name, String password) {
		for (DbAbstractModel db : dbFake) {
			User user = (User)db;
			if (user.getUsername().equals(name) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	public ResponseMessage updateUser(User user){
		return null;
	}
	
	public ResponseMessage updateProfile(User user){
		return null;
	}
}
