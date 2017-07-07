package Subject.AppCode;

import java.util.List;

import Subject.Models.CommonSubjectTemplate;
import Subject.Models.Subject;
import Subject.Models.SubjectComponentTemplates;
import Subject.Models.SubjectInfo;
import Subject.Models.SubjectTerm;

public interface SubjectManagerInterface {

	public void AddSubjectInfo(SubjectInfo subjectInfo);
	
	public List<SubjectTerm> GetAllSubjectTerms();
	
	public void AddSubject(Subject subject);
	
	public void UpdateSubject(Subject subject);
	
	public void AddCommonSubjectTemplate(CommonSubjectTemplate cst);
	
	public void DeleteCommonSubjectTemplateByIDFields(int subjectId,int subjectComponentId);

	public void AddSubjectComponentTemplate(SubjectComponentTemplates sct);

	public void UpdateSubjectComponentTemplate(SubjectComponentTemplates sct);

	public void DeleteSubjectComponentTemplateByID(int id);

	public List<SubjectComponentTemplates> getAllSubjectComponentTemplates();

	public List<SubjectComponentTemplates> getAllSubjectComponentTemplatesByIDList(
			List<CommonSubjectTemplate> commonSubjectTemplates);

	public List<CommonSubjectTemplate> getAllCommonSubjectTemplatesBySubjectID(int subjectId);

	public Subject getSubjectById(int subjectId);
}
