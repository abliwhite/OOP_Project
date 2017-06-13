package Account.Models;

import java.util.Objects;


public class User {

	private Integer id;
	private String username;
	private String password;
	private String email;

	// Only have get method role can't be changed
	private String role;

	private String gmailID;
	private String facebookID;

	// Only have get methods relation id can't be changed
	private Integer profileID;

	private UserProfile profile;

	public UserProfile getUserProfile() {
		return profile;
	}

	public void setUserProfile(UserProfile profile) {
		this.profile = profile;
	}

	public Integer getProfileID() {
		return profileID;
	}

	public String getFacebookID() {
		return facebookID;
	}

	public void setFacebookID(String facebookID) {
		this.facebookID = facebookID;
	}

	public User(Integer id, String username, String password, String email, String role, String gmailID, String facebookID,
			Integer profileID, UserProfile profile) {
		
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
	
	public Integer getId(){
		return id;
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

	public String getInsertValuesString() {
		return "(" + username + "," + password + "," + email + "," + role + "," + gmailID + "," + facebookID + ","
				+ profileID + ");";
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (!(obj instanceof User))
			return false;

		User passed = (User) obj;

		return id.equals(passed.getId()) && username.equals(passed.getUsername()) && gmailID.equals(passed.getGmailID())
				&& facebookID == passed.getFacebookID() && role.equals(passed.getRole())
				&& password.equals(passed.getPassword()) && email.equals(passed.getEmail())
				&& profileID == passed.getProfileID();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, gmailID, facebookID, role, password, email, profileID);
	}

}
