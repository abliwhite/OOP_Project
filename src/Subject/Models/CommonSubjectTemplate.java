package Subject.Models;

public class CommonSubjectTemplate {

	private Integer id;
	private int subjectComponentTemplateID;
	private int subjectTemplateID;

	public CommonSubjectTemplate(Integer id, int subjectComponentTemplateID, int subjectTemplateID) {
		this.id = id;
		this.subjectComponentTemplateID = subjectComponentTemplateID;
		this.subjectTemplateID = subjectTemplateID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSubjectComponentTemplateID() {
		return subjectComponentTemplateID;
	}

	public void setSubjectComponentTemplateID(int subjectComponentTemplateID) {
		this.subjectComponentTemplateID = subjectComponentTemplateID;
	}

	public int getSubjectTemplateID() {
		return subjectTemplateID;
	}

	public void setSubjectTemplateID(int subjectTemplateID) {
		this.subjectTemplateID = subjectTemplateID;
	}
}
