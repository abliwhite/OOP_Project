package Authentication.Models;

import java.sql.Date;
import java.util.Objects;

public class UserProfile {

	//Only have get method id can't be changed
	private int id;
	
	private String name;
	private String surname;
	
	private String gender;
	//Only have get method create date can't be changed
	private Date createDate;

	public UserProfile(int id, String name, String gender, Date createDate) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (!(obj instanceof UserProfile))
			return false;
		

		UserProfile passed = (UserProfile) obj;

		return id == passed.getId() && name.equals(passed.getName()) && surname.equals(passed.getSurname())
				&& gender.equals(passed.getGender()) && createDate.equals(passed.getCreateDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, surname, gender, createDate);
	}

}
