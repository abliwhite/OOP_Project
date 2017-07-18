package Profiles.ProfileViewModels;

import java.util.List;

import Account.Models.User;
import Subject.Models.DbModels.Subject;

public class StudentProfileViewModel {
	private User user;
	private List<UserSubjectDropDownViewModel> allSubjects;
	private List<Subject> userSubjects;
	
	public StudentProfileViewModel(User user, List<UserSubjectDropDownViewModel> allSubjects, List<Subject> userSubjects) {
		this.user = user;
		this.allSubjects = allSubjects;
		this.userSubjects = userSubjects;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<UserSubjectDropDownViewModel> getAllSubjects() {
		return allSubjects;
	}
	public void setAllSubjects(List<UserSubjectDropDownViewModel> allSubjects) {
		this.allSubjects = allSubjects;
	}
	public List<Subject> getUserSubjects() {
		return userSubjects;
	}
	public void setUserSubjects(List<Subject> userSubjects) {
		this.userSubjects = userSubjects;
	}

	
}
