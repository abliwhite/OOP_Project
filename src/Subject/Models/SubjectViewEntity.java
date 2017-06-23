package Subject.Models;

import java.util.List;

public class SubjectViewEntity {

	public static final String SUBJECT_VIEW_ENTITY_ATTRIBUTE = "Subject view entity attribute";
	
	private Subject subject;
	private List<SubjectComponentTemplatesViewEntity> subjecComponentTemplatesViewEnties;

	public SubjectViewEntity(Subject subject,
			List<SubjectComponentTemplatesViewEntity> subjecComponentTemplatesViewEnties) {
		this.subjecComponentTemplatesViewEnties = subjecComponentTemplatesViewEnties;
		this.subject = subject;
	}

	public List<SubjectComponentTemplatesViewEntity> getSubjecComponentTemplatesViewEnties() {
		return subjecComponentTemplatesViewEnties;
	}

	public void setSubjecComponentTemplatesViewEnties(List<SubjectComponentTemplatesViewEntity> subjecComponentTemplatesViewEnties) {
		this.subjecComponentTemplatesViewEnties = subjecComponentTemplatesViewEnties;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	

}
