package Account.AppCode;

import org.apache.tomcat.jdbc.pool.DataSource;

import Account.Models.AuthModel;
import Account.Models.RegisterModel;
import Account.Models.User;
import Common.AppCode.CommonConstants;
import Common.AppCode.DaoController;
import Common.Models.DbAbstractModel;
import Common.Models.ResponseMessage;

public class AccountManager extends DaoController {

	public static final String ACCOUNT_MANAGER_ATTRIBUTE = "Account Manager Attribure";

	public AccountManager(DataSource pool) {
		super(pool);
	}


	public User checkLoginValidation(AuthModel auth) {
		for (DbAbstractModel db : dbFake) {
			User user = (User) db;
			if (user.getUsername().equals(auth.getUsername()) && user.getPassword().equals(auth.getPassword())) {
				return user;
			}
		}
		return null;
	}

	public ResponseMessage checkRegistrationValidation(RegisterModel register) {
		for (DbAbstractModel db : dbFake) {
			User user = (User) db;
			if (user.getUsername().equals(register.getUsername())) {
				return new ResponseMessage(CommonConstants.UNSUCCESSFULL_REGISTRATION_USERNAME_CASE, false);
			}
			if(user.getEmail().equals(register.getEmail())){
				return new ResponseMessage(CommonConstants.UNSUCCESSFULL_REGISTRATION_EMAIL_CASE, false);
			}
		}
		return new ResponseMessage(CommonConstants.SUCCESSFULL_MESSAGE, true);
	}

	public ResponseMessage updateUser(User user) {
		return null;
	}

	public ResponseMessage updateProfile(User user) {
		return null;
	}
}
