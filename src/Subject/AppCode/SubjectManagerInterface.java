package Subject.AppCode;

import java.util.List;

import Account.Models.User;
import Subject.Models.DbModels.CommonSubjectComponent;
import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectComponentType;
import Subject.Models.DbModels.SubjectInfo;
import Subject.Models.DbModels.SubjectTerm;
import Subject.Models.DbModels.UserSubject;
import Subject.Models.ViewModels.CommonSubjectComponentViewModel;
import Subject.Models.ViewModels.SubjectViewModel;
import Subject.Models.DbModels.*;
import Subject.Models.DbModels.*;
public interface SubjectManagerInterface {

	public void AddSubjectInfo(SubjectInfo subjectInfo);
	
	public void UpdateSubjectInfo(SubjectInfo subjectInfo);
	
	public List<SubjectTerm> GetAllSubjectTerms();
	
	public void AddSubject(Subject subject);
	
	public void UpdateSubject(Subject subject);
	
	public void AddCommonSubjectComponent(CommonSubjectComponent cst);
	
	public void DeleteCommonSubjectComponentByID(int id);
	
	public void deleteCommonSubjectComponentBySubjectId(int subjectId);

	public void AddSubjectComponentType(SubjectComponentType sct);
	
	public void UpdateCommonSubjectComponent(CommonSubjectComponent csc);

	public List<SubjectComponentType> getAllSubjectComponentTypes();

	public List<CommonSubjectComponentViewModel> getAllCommonSubjectComponentsViewModelBySubjectID(int subjectId);

	public List<SubjectViewModel> getAllSubjectViewModels();
	
	public SubjectViewModel getSubjectViewModelById(int subjectId);
	
	public Subject getSubjectByFilter(String subjectName, int year, int termId);
	
	public void addUserSubject(UserSubject us);
	
	public List<Subject> getAllSubjects();
	
	public void deleteSubjectById(int id);

	public void deleteCommonSubjectComponentMaterialsByCscIdList(List<Integer> cscIds);

	public void deleteUserSubjectComponentsByCscIdList(List<Integer> cscIds);

	public void deleteUserSubjectBySubjectId(int id);
	
	public void deleteUserSubjectByAllFields(int userId,int subjectId);

	public CommonSubjectComponentViewModel getCommonSubjectComponentViewmodelById(int componentId);

	public List<SubjectComponentMaterial> getSubjectComponentMaterialsByComponentId(int componentId);

	public List<User> getSubjectAllUsers(String subject, int year, int termId);

	public void deleteUserSubjectComponentByUserId(String userId);
	
	public List<Subject> getUserSubjects(int userId);
	
}



