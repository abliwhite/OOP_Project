package Profiles.Servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Account.Models.User;
import Account.Models.UserProfile;
import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;
import Profiles.ProfileViewModels.StudentProfileViewModel;
import Profiles.ProfileViewModels.UserSubjectDropDownViewModel;
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

		if (!fullNumericStringValidation(userId)) {

			return;
		}
		// TODO yleobebia shesasworebelia

		User user = accountManager.getUserById(Integer.parseInt(userId));

		if (user == null) {
			// TODO errois page
			return;
		}

		if (user.getProfile() == null) {
			UserProfile profile = accountManager.getProfile(user);
			user.setUserProfile(profile);
		}
		List<Subject> userSubjects = accountManager.getUserSubjects(user);
		List<UserSubjectDropDownViewModel> allSubjects = subjectManager.getAllSubjects()
				.stream().map(x -> new UserSubjectDropDownViewModel(x.getName() + " "
						+ (x.getTermId() == 1 ? "Fall" : "Spring")  + String.valueOf(x.getYear()), x.getId()))
				.collect(Collectors.toList());

		StudentProfileViewModel spViewModel = new StudentProfileViewModel(user, allSubjects, userSubjects);

		ResponseModel responseModel = new ResponseModel<Object, StudentProfileViewModel>(spViewModel, true,
				CommonConstants.SUCCESSFUL_MESSAGE);

		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, responseModel);
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
