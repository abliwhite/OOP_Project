package Profiles.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;
import Subject.Models.DbModels.Subject;
import Subject.Servlets.SubjectServletParent;

/**
 * Servlet implementation class AdminProfilePageGeneratoServlet
 */
@WebServlet("/AdminProfilePageGeneratorServlet")
public class AdminProfilePageGeneratorServlet extends ProfileServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminProfilePageGeneratorServlet() {
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
		List<Subject> allSubjects = subjectManager.getAllSubjects();

		ResponseModel<Subject, Object> responseModel = new ResponseModel<Subject, Object>(allSubjects, true,
				CommonConstants.SUCCESSFUL_MESSAGE);

		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, responseModel);
		request.getRequestDispatcher("/Profiles/AdminProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
