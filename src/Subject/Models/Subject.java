package Subject.Models;

import java.util.Objects;

public class Subject {

	private Integer id;
	private String name;
	private String language;
	private int ects;
	private String lecturerName;
	private String syllabusPath;

	public Subject(int id, String name, String language, int ects, String lecturerName, String syllabusPath) {
		
		this.name = name;
		this.language = language;
		this.ects = ects;
		this.lecturerName = lecturerName;
		this.syllabusPath = syllabusPath;
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

	public Integer getId(){
		return id;
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
		return Objects.hash(id, name, language, ects, lecturerName, syllabusPath);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Subject other = (Subject) obj;

		return other.name.equals(name) && other.ects == ects && other.getId().equals(id) && other.language.equals(language)
				&& other.lecturerName.equals(lecturerName) && other.syllabusPath.equals(syllabusPath);
	}
}
