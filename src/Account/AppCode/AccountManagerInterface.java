package Account.AppCode;

import java.util.List;

import Account.Models.AuthModel;
import Account.Models.RegisterModel;
import Account.Models.User;
import Account.Models.UserProfile;
import Common.Models.ResponseModel;
import Subject.Models.Subject;

public interface AccountManagerInterface {

	public void addProfile(UserProfile profile);

	public void addUser(User user);
	
	public ResponseModel checkRegistrationValidity(RegisterModel register);
	
	public void updateUser(User user);
	
	public void updateProfile(UserProfile profile);
	
	public User checkLoginValidation(AuthModel auth);
	
	public UserProfile getProfile(User user);

	public List<Subject> getUserSubjects(User user);
	
}
