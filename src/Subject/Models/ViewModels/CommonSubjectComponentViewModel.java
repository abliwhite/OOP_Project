package Subject.Models.ViewModels;
import java.util.List;

import Subject.Models.*;
import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectComponentMaterial;
import Subject.Models.DbModels.SubjectComponentType;

public class CommonSubjectComponentViewModel {

	private int id;
	private double markPercentage;
	private int number;
	private int subjectId;
	private Subject subject;
	private SubjectComponentType subjectComponentType;
	private List <SubjectComponentMaterial> materials;
	

	public CommonSubjectComponentViewModel(int id,int subjectId, double markPercentage, int number,
			SubjectComponentType subjectComponentType) {
		this.id = id;
		this.subjectId = subjectId;
		this.markPercentage = markPercentage;
		this.number = number;
		this.subjectComponentType = subjectComponentType;
	}
	public CommonSubjectComponentViewModel(int id,int subjectId, double markPercentage, int number,
			SubjectComponentType subjectComponentType, Subject subject) {
		this.id = id;
		this.subjectId = subjectId;
		this.markPercentage = markPercentage;
		this.number = number;
		this.subjectComponentType = subjectComponentType;
		this.subject = subject;
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
	public void setSubject(Subject subject){
		this.subject=subject;
	}
	public Subject getSubject(){
		return subject;
	}
	public void setMaterials(List<SubjectComponentMaterial> materials){
		this.materials=materials;
	}
	public List<SubjectComponentMaterial> getMaterials(){
		return materials;
	}
}
