package Authentication.Models;

import java.util.Objects;

public class UserModel {

	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	private String gmailID;
	private String facebookID;
	private int profileID;

	public Integer getProfileID() {
		return profileID;
	}

	public void setProfileID(Integer profileID) {
		this.profileID = profileID;
	}

	public String getFacebookID() {
		return facebookID;
	}

	public void setFacebookID(String facebookID) {
		this.facebookID = facebookID;
	}

	public UserModel(int id, String username, String password, String email, String role, String gmailID,
			String facebookID, int profileID) {
		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setRole(role);
		this.setEmail(email);
		this.setGmailID(gmailID);
		this.setFacebookID(facebookID);
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

	public void setRole(String role) {
		this.role = role;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (!(obj instanceof UserModel))
			return false;

		UserModel passed = (UserModel) obj;

		return id == passed.getId() && username.equals(passed.getUsername()) && gmailID.equals(passed.getGmailID())
				&& facebookID == passed.getFacebookID() && role.equals(passed.getRole())
				&& password.equals(passed.getPassword()) && email.equals(passed.getEmail())
				&& profileID == passed.getProfileID();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, gmailID, facebookID, role, password, email, profileID);
	}

}
