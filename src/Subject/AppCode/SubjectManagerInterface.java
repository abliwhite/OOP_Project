package Subject.AppCode;

import java.util.List;

import Subject.Models.Subject;
import Subject.Models.SubjectComponentTemplates;

public interface SubjectManagerInterface {
	
	public void AddSubject(Subject subject);
	
	public void AddSubjectComponentTemplate(SubjectComponentTemplates sct);
	
	public void UpdateSubjectComponentTemplate(SubjectComponentTemplates sct);

	public List<SubjectComponentTemplates> getAllSubjectComponentTemplates();
	
}
