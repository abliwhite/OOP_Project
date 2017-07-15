package Profiles.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Account.Models.User;
import Account.Models.UserProfile;
import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectTerm;
import Subject.Servlets.SubjectServletParent;

/**
 * Servlet implementation class ProfilePageGeneratorServlet
 */
public class StudentProfilePageGeneratorServlet extends ProfileServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentProfilePageGeneratorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		String userId = request.getParameter("id");
		
		if(!fullNumericStringValidation(userId)){
			
			return;
		}
		// TODO yleobebia shesasworebelia

		User user = accountManager.getUserById(Integer.parseInt(userId));
		
		if (user == null) {
			//TODO errois page
			return;
		}

		if (user.getProfile() == null) {
			UserProfile profile = accountManager.getProfile(user);
			user.setUserProfile(profile);
		}
		List<Subject> userSubjects = accountManager.getUserSubjects(user);
		List<Subject> allSubjects = subjectManager.getAllSubjects();

		request.setAttribute("UserSubjects", userSubjects);
		request.setAttribute("user", user);
		request.setAttribute("AllSubjects", allSubjects);
		
		List<SubjectTerm> SubjectTerms = subjectManager.GetAllSubjectTerms();

		request.setAttribute("SubjectTerms", SubjectTerms);

		request.getRequestDispatcher("/Profiles/UserProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
