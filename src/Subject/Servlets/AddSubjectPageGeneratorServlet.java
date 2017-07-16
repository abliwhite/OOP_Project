package Subject.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Manager;

import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;
import Subject.Models.DbModels.SubjectComponentType;
import Subject.Models.DbModels.SubjectTerm;
import Subject.Models.ViewModels.CommonSubjectComponentViewModel;
import Subject.Models.ViewModels.SubjectTemplateListsViewModel;

/**
 * Servlet implementation class AddSubjectPageGeneratorServlet
 */
public class AddSubjectPageGeneratorServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSubjectPageGeneratorServlet() {
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
		redirectToLoginIfNotLogged(request, response);
		
		List<SubjectTerm> subjectTerms = manager.GetAllSubjectTerms();

		List<SubjectComponentType> names = manager.getAllSubjectComponentTypes();

		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
				new ResponseModel<Object, SubjectTemplateListsViewModel>(
						new SubjectTemplateListsViewModel(names, subjectTerms), true,
						CommonConstants.SUCCESSFUL_MESSAGE));

		request.getRequestDispatcher("Subject/Add.jsp").forward(request, response);
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
