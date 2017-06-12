package Account.Models;

import java.util.Objects;

import Common.Models.DbAbstractModel;

public class User extends DbAbstractModel {

	private String username;
	private String password;
	private String email;

	// Only have get method role can't be changed
	private String role;

	private String gmailID;
	private String facebookID;

	// Only have get methods relation id can't be changed
	private int profileID;

	private UserProfile profile;

	public UserProfile getUserProfile() {
		return profile;
	}

	public void setUserProfile(UserProfile profile) {
		this.profile = profile;
	}

	public int getProfileID() {
		return profileID;
	}

	public String getFacebookID() {
		return facebookID;
	}

	public void setFacebookID(String facebookID) {
		this.facebookID = facebookID;
	}

	public User(Integer id, String username, String password, String email, String role, String gmailID, String facebookID,
			int profileID, UserProfile profile, String tableName) {
		super(tableName,id);
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.gmailID = gmailID;
		this.facebookID = facebookID;
		this.profileID = profileID;
		this.profile = profile;
	}

	public String getGmailID() {
		return gmailID;
	}

	public void setGmailID(String gmailID) {
		this.gmailID = gmailID;
	}

	public String getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getInsertValuesString() {
		return "(" + username + "," + password + "," + email + "," + role + "," + gmailID + "," + facebookID + ","
				+ profileID + ");";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (!(obj instanceof User))
			return false;

		User passed = (User) obj;

		return getId().equals(passed.getId()) && username.equals(passed.getUsername()) && gmailID.equals(passed.getGmailID())
				&& facebookID == passed.getFacebookID() && role.equals(passed.getRole())
				&& password.equals(passed.getPassword()) && email.equals(passed.getEmail())
				&& profileID == passed.getProfileID();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), username, gmailID, facebookID, role, password, email, profileID);
	}

}
