package Subject.AppCode;

import Subject.Models.Subject;
import Subject.Models.SubjectComponentTemplates;

public interface SubjectManagerInterface {
	
	public void AddSubject(Subject subject);
	
	public void AddSubjectComponentTemplate(SubjectComponentTemplates sct);
	
}
