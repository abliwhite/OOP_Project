package Subject.Models;

import java.util.Objects;

import Common.Models.DbAbstractModel;

public class SubjectComponentTemplates extends DbAbstractModel {

	private String name;
	private double markPercentage;
	private int number;

	public SubjectComponentTemplates(int id, String name, double markPercentage, int number, String tableName) {
		super(tableName,id);
		
		this.name = name;
		this.markPercentage = markPercentage;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMarkPercentage() {
		return markPercentage;
	}

	public void setMarkPercentage(double markPercentage) {
		this.markPercentage = markPercentage;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String getInsertValuesString() {
		return "(" + name + "," + markPercentage + "," + number + ");";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), name, markPercentage, number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		SubjectComponentTemplates other = (SubjectComponentTemplates) obj;

		return other.getId().equals(getId()) && other.markPercentage == markPercentage && other.name.equals(name)
				&& other.number == number;
	}
}
