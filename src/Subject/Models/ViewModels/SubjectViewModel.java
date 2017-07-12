package Subject.Models.ViewModels;

import java.util.List;

import Subject.Models.DbModels.Subject;

public class SubjectViewModel {

	public static final String SUBJECT_VIEW_ENTITY_ATTRIBUTE = "Subject view entity attribute";
	
	private Subject subject;
	private List<SubjectComponentTemplatesViewModel> subjecComponentTemplatesViewEnties;

	public SubjectViewModel(Subject subject,
			List<SubjectComponentTemplatesViewModel> subjecComponentTemplatesViewEnties) {
		this.subjecComponentTemplatesViewEnties = subjecComponentTemplatesViewEnties;
		this.subject = subject;
	}

	public List<SubjectComponentTemplatesViewModel> getSubjecComponentTemplatesViewEnties() {
		return subjecComponentTemplatesViewEnties;
	}

	public void setSubjecComponentTemplatesViewEnties(List<SubjectComponentTemplatesViewModel> subjecComponentTemplatesViewEnties) {
		this.subjecComponentTemplatesViewEnties = subjecComponentTemplatesViewEnties;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	

}
