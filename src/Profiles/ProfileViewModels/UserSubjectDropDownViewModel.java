package Profiles.ProfileViewModels;

public class UserSubjectDropDownViewModel {
	private String subjectIdentityString;
	private int subjectId;
	
	public UserSubjectDropDownViewModel(String subjectIdentityString,int subjectId){
		this.subjectId = subjectId;
		this.subjectIdentityString = subjectIdentityString;
	}
	
	public String getSubjectIdentityString() {
		return subjectIdentityString;
	}
	public void setSubjectIdentityString(String subjectIdentityString) {
		this.subjectIdentityString = subjectIdentityString;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}
