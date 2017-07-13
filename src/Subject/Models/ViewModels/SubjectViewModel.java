package Subject.Models.ViewModels;

import java.util.List;

import Subject.Models.DbModels.Subject;

public class SubjectViewModel {

	public static final String SUBJECT_VIEW_ENTITY_ATTRIBUTE = "Subject view entity attribute";
	
	private Subject subject;
	private List<CommonSubjectComponentViewModel> CommonSubjectComponentViewModels;

	public SubjectViewModel(Subject subject,
			List<CommonSubjectComponentViewModel> CommonSubjectComponentViewModels) {
		this.CommonSubjectComponentViewModels = CommonSubjectComponentViewModels;
		this.subject = subject;
	}

	public List<CommonSubjectComponentViewModel> getCommonSubjectComponentViewModels() {
		return CommonSubjectComponentViewModels;
	}

	public void setSubjecComponentTemplatesViewEnties(List<CommonSubjectComponentViewModel> CommonSubjectComponentViewModels) {
		this.CommonSubjectComponentViewModels = CommonSubjectComponentViewModels;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	

}
