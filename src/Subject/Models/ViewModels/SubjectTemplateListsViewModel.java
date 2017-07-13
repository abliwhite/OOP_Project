package Subject.Models.ViewModels;

import java.util.List;

import Subject.Models.DbModels.SubjectComponentType;
import Subject.Models.DbModels.SubjectTerm;

public class SubjectTemplateListsViewModel {

	// sc stands for subject component
	private List<SubjectComponentType> scNames;

	private List<SubjectTerm> sTerms;

	public SubjectTemplateListsViewModel(List<SubjectComponentType> scNames, List<SubjectTerm> sTerms) {
		this.scNames = scNames;
		this.sTerms = sTerms;
	}

	public List<SubjectComponentType> getScNames() {
		return scNames;
	}

	public void setScNames(List<SubjectComponentType> scNames) {
		this.scNames = scNames;
	}

	public List<SubjectTerm> getsTerms() {
		return sTerms;
	}

	public void setsTerms(List<SubjectTerm> sTerms) {
		this.sTerms = sTerms;
	}
}
