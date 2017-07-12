package Subject.AppCode;

import java.util.List;

import Subject.Models.DbModels.CommonSubjectTemplate;
import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectComponentTemplates;
import Subject.Models.DbModels.SubjectInfo;
import Subject.Models.DbModels.SubjectTerm;
import Subject.Models.DbModels.UserSubject;

public interface SubjectManagerInterface {

	public void AddSubjectInfo(SubjectInfo subjectInfo);
	
	public List<SubjectTerm> GetAllSubjectTerms();
	
	public void AddSubject(Subject subject);
	
	public void UpdateSubject(Subject subject);
	
	public void AddCommonSubjectTemplate(CommonSubjectTemplate cst);
	
	public void DeleteCommonSubjectTemplateByIDFields(int subjectId,int subjectComponentId);

	public void AddSubjectComponentTemplate(SubjectComponentTemplates sct);
	
	public SubjectComponentTemplates getSubjectComponentTemplateByName(SubjectComponentTemplates sct);
	
	public SubjectComponentTemplates getSubjectComponentTemplatesByAllFields(SubjectComponentTemplates sct);

	public void UpdateSubjectComponentTemplate(SubjectComponentTemplates sct);

	public void DeleteSubjectComponentTemplateByID(int id);

	public List<SubjectComponentTemplates> getAllSubjectComponentTemplates();

	public List<SubjectComponentTemplates> getAllSubjectComponentTemplatesByIDList(
			List<CommonSubjectTemplate> commonSubjectTemplates);

	public List<CommonSubjectTemplate> getAllCommonSubjectTemplatesBySubjectID(int subjectId);

	public Subject getSubjectById(int subjectId);
	
	public Subject getSubjectByFilter(String subjectName, int year, int termId);
	
	public void addUserSubject(UserSubject us);
	
}
