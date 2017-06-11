package Account.Models;

import java.security.NoSuchAlgorithmException;

import Common.AppCode.PasswordEncryptor;

public class AuthModel {

	private String username;
	//Encrypted one
	private String password;

	public AuthModel(String username, String password) {
		
		this.username = username;
		try {
			this.password = PasswordEncryptor.getEncryptedPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
}
