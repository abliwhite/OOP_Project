package Subject.Models.ViewModels;

import Subject.Models.DbModels.SubjectComponentType;

public class CommonSubjectComponentViewModel {

	private int id;
	private double markPercentage;
	private int number;
	private int subjectId;
	private SubjectComponentType subjectComponentType;

	public CommonSubjectComponentViewModel(int id,int subjectId, double markPercentage, int number,
			SubjectComponentType subjectComponentType) {
		this.id = id;
		this.subjectId = subjectId;
		this.markPercentage = markPercentage;
		this.number = number;
		this.subjectComponentType = subjectComponentType;
	}

	public SubjectComponentType getSubjectComponentType() {
		return subjectComponentType;
	}

	public void setSubjectComponentType(SubjectComponentType subjectComponentType) {
		this.subjectComponentType = subjectComponentType;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

}
