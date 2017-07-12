package Subject.Models.DbModels;

public class SubjectInfo {

	private Integer id;
	private String lecturerName;
	private String syllabusPath;
	private int ects;
	private String language;

	public SubjectInfo(Integer id, String lecturerName, String syllabusPath, int ects, String language) {
		this.id = id;
		this.lecturerName = lecturerName;
		this.syllabusPath = syllabusPath;
		this.ects = ects;
		this.language = language;
	}

	public SubjectInfo(String lecturerName, String syllabusPath, int ects, String language) {
		this((Integer) null, lecturerName, syllabusPath, ects, language);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}

	public String getSyllabusPath() {
		return syllabusPath;
	}

	public void setSyllabusPath(String syllabusPath) {
		this.syllabusPath = syllabusPath;
	}

	public int getEcts() {
		return ects;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
