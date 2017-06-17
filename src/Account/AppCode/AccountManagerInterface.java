package Account.AppCode;

import Account.Models.AuthModel;
import Account.Models.RegisterModel;
import Account.Models.User;
import Account.Models.UserProfile;
import Common.Models.ResponseMessage;

public interface AccountManagerInterface {

	public void addProfile(UserProfile profile);

	public void addUser(User user);
	
	public ResponseMessage checkRegistrationValidity(RegisterModel register);
	
	public void updateUser(User user);
	
	public void updateProfile(UserProfile profile);
	
	public User checkLoginValidation(AuthModel auth);
}
