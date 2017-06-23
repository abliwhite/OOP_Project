package Subject.AppCode;

import java.util.List;

import Subject.Models.CommonSubjectTemplate;
import Subject.Models.Subject;
import Subject.Models.SubjectComponentTemplates;

public interface SubjectManagerInterface {

	public void AddSubject(Subject subject);

	public void AddSubjectComponentTemplate(SubjectComponentTemplates sct);

	public void UpdateSubjectComponentTemplate(SubjectComponentTemplates sct);

	public void DeleteSubjectComponentTemplateByID(int id);

	public List<SubjectComponentTemplates> getAllSubjectComponentTemplates();

	public List<SubjectComponentTemplates> getAllSubjectComponentTemplatesByIDList(
			List<CommonSubjectTemplate> commonSubjectTemplates);

	public List<CommonSubjectTemplate> getAllCommonSubjectTemplatesBySubjectID(int subjectId);

	public Subject getSubjectById(int subjectId);
}
