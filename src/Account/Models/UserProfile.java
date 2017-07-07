package Account.Models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UserProfile {

	private Integer id;
	private String name;
	private String surname;

	private String gender;
	// Only have get method create date can't be changed
	private String createDate;

	public UserProfile(Integer id, String name, String gender, String createDate, String surname) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.createDate = createDate;
		this.surname = surname;
	}

	public UserProfile(String name, String gender, String createDate, String surname) {
		this((Integer) null, name, gender, createDate, surname);
	}

	public String getCreateDate() {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// Todo dasafixia
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (!(obj instanceof UserProfile))
			return false;

		UserProfile passed = (UserProfile) obj;

		return id.equals(passed.getId()) && name.equals(passed.getName()) && surname.equals(passed.getSurname())
				&& gender.equals(passed.getGender()) && createDate.equals(passed.getCreateDate());
	}

	public int hashCode() {
		return Objects.hash(getId(), name, surname, gender, createDate);
	}

}
