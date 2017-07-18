package Subject.Models.DbModels;

public class SubjectComponentMaterial {

	private int id;
	private String materialPath;
	private String uploadDate;
	private String subjectComponentId;

	public SubjectComponentMaterial(Integer id, String materialPath, String uploadDate, String subjectComponentId) {
		this.id = id;
		this.materialPath = materialPath;
		this.uploadDate = uploadDate;
		this.subjectComponentId = subjectComponentId;
	}

	public SubjectComponentMaterial(String materialPath, String uploadDate, String subjectComponentId) {
		this.materialPath=materialPath;
		this.subjectComponentId=subjectComponentId;
		this.uploadDate=uploadDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaterialPath() {
		return materialPath;
	}

	public void setMaterialPath(String materialPath) {
		this.materialPath = materialPath;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getSubjectComponentId() {
		return subjectComponentId;
	}

	public void setSubjectComponentId(String subjectComponentId) {
		this.subjectComponentId = subjectComponentId;
	}
}
