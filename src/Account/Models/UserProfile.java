package Account.Models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import Common.Models.DbAbstractModel;

public class UserProfile extends DbAbstractModel {

	// Only have get method id can't be changed
	private Integer id;

	private String name;
	private String surname;

	private String gender;
	// Only have get method create date can't be changed
	private LocalDateTime createDate;

	public UserProfile(int id, String name, String gender, LocalDateTime createDate, String tableName,String surname) {
		super(tableName);
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.createDate = createDate;
		this.surname = surname;
	}

	public LocalDateTime getCreateDate() {
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

	//Todo dasafixia
	@Override
	public String getInsertValuesString() {
		
		return "(" + name + "," + surname + "," + gender + "," + createDate + ");";
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
