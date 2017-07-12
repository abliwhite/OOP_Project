package Subject.Models.ViewModels;

import Subject.Models.DbModels.SubjectComponentTemplates;

public class SubjectComponentTemplatesViewModel {

	private SubjectComponentTemplates subjectComponentTemplate;

	public SubjectComponentTemplatesViewModel(SubjectComponentTemplates subjectComponentTemplate) {
		this.subjectComponentTemplate = subjectComponentTemplate;
	}

	public SubjectComponentTemplates getSubjectComponentTemplate() {
		return subjectComponentTemplate;
	}

	public void setSubjectComponentTemplate(SubjectComponentTemplates subjectComponentTemplate) {
		this.subjectComponentTemplate = subjectComponentTemplate;
	}
	

}
