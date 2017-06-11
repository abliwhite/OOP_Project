package Account.Models;

public class RegisterModel  {

	private String email;
	private String username;

	public RegisterModel(String username, String email) {
		this.email = email;
		this.username = username;
	}

	public String getEmail(){
		return email;
	}
	
	public String getUsername(){
		return username;
	}
}
