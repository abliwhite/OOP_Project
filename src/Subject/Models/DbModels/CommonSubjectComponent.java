package Subject.Models.DbModels;

public class CommonSubjectComponent {

	private Integer id;
	private int typeId;
	private int subjectId;
	private double markPercentage;
	private int number;

	public CommonSubjectComponent(Integer id, int typeId, int subjectId, double markPercentage, int number) {
		this.id = id;
		this.typeId = typeId;
		this.subjectId = subjectId;
		this.markPercentage = markPercentage;
		this.number = number;
	}

	public CommonSubjectComponent(int typeId, int subjectID, double markPercentage, int number) {
		this((Integer) null, typeId, subjectID, markPercentage, number);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
}
