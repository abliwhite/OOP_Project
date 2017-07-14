package Subject.Models.ViewModels;

import java.util.List;

import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectInfo;
import Subject.Models.DbModels.SubjectTerm;

public class SubjectViewModel {

	public static final String SUBJECT_VIEW_ENTITY_ATTRIBUTE = "Subject view entity attribute";

	private Subject subject;
	private SubjectTerm term;
	private SubjectInfo info;

	private List<CommonSubjectComponentViewModel> commonSubjectComponentViewModels;
	private SubjectTemplateListsViewModel subjectTemplateListsViewModel;

	public SubjectViewModel(Subject subject, List<CommonSubjectComponentViewModel> commonSubjectComponentViewModels,
			SubjectTemplateListsViewModel subjectTemplateListsViewModel, SubjectTerm term, SubjectInfo info) {
		this.subject = subject;
		this.subjectTemplateListsViewModel = subjectTemplateListsViewModel;
		this.commonSubjectComponentViewModels = commonSubjectComponentViewModels;
		this.term = term;
		this.info = info;

	}

	public SubjectViewModel(Subject subject, SubjectTerm term, SubjectInfo info) {
		this(subject, null, null, term, info);
	}

	public List<CommonSubjectComponentViewModel> getCommonSubjectComponentViewModels() {
		return commonSubjectComponentViewModels;
	}

	public void setCommonSubjectComponentViewModels(
			List<CommonSubjectComponentViewModel> commonSubjectComponentViewModels) {
		this.commonSubjectComponentViewModels = commonSubjectComponentViewModels;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public SubjectTemplateListsViewModel getSubjectTemplateListsViewModel() {
		return subjectTemplateListsViewModel;
	}

	public void setSubjectTemplateListsViewModel(SubjectTemplateListsViewModel subjectTemplateListsViewModel) {
		this.subjectTemplateListsViewModel = subjectTemplateListsViewModel;
	}

	public SubjectTerm getTerm() {
		return term;
	}

	public void setTerm(SubjectTerm term) {
		this.term = term;
	}

	public SubjectInfo getInfo() {
		return info;
	}

	public void setInfo(SubjectInfo info) {
		this.info = info;
	}

}
