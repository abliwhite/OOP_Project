package Subject.Models;

public class Subject {

	private int id;
	private String name;
	private String language;
	private int ects;
	private String lecturerName;
	private String syllabusPath;

	public Subject(int id, String name, String language, int ects, String lecturerName, String syllabusPath) {
		this.id = id;
		this.name = name;
		this.language = language;
		this.ects = ects;
		this.lecturerName = lecturerName;
		this.syllabusPath = syllabusPath;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLanguage() {
		return language;
	}

	public int getEcts() {
		return ects;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public String getSyllabusPath() {
		return syllabusPath;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}

	public void setSyllabusPath(String syllabusPath) {
		this.syllabusPath = syllabusPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ects;
		result = prime * result + id;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((lecturerName == null) ? 0 : lecturerName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((syllabusPath == null) ? 0 : syllabusPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;

		Subject other = (Subject) obj;

		return other.name.equals(name) && other.ects == ects && other.id == id && other.language.equals(language)
				&& other.lecturerName.equals(lecturerName) && other.syllabusPath.equals(syllabusPath);
	}
}
