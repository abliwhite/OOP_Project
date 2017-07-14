package Subject.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;
import Subject.Models.DbModels.CommonSubjectComponent;
import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectComponentType;
import Subject.Models.DbModels.SubjectTerm;
import Subject.Models.ViewModels.CommonSubjectComponentViewModel;
import Subject.Models.ViewModels.SubjectTemplateListsViewModel;
import Subject.Models.ViewModels.SubjectViewModel;

/**
 * Servlet implementation class EditSubjectServlet
 */
@WebServlet("/EditSubjectPageGeneratorServlet")
public class EditSubjectPageGeneratorServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditSubjectPageGeneratorServlet() {
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
		String subjectId = request.getParameter("id");

		if (fullNumericStringValidation(subjectId)) {
			int id = Integer.parseInt(subjectId);

			SubjectViewModel svm = manager.getSubjectViewModelById(id);
			if (svm == null) {
				// page not found
				return;
			}

			List<CommonSubjectComponentViewModel> cscvm = manager.getAllCommonSubjectComponentsViewModelBySubjectID(id);

			List<CommonSubjectComponentViewModel> commonSubjectComponentViewModels = manager
					.getAllCommonSubjectComponentsViewModelBySubjectID(id);

			List<SubjectTerm> subjectTerms = manager.GetAllSubjectTerms();
			List<SubjectComponentType> typeNames = manager.getAllSubjectComponentTypes();
			SubjectTemplateListsViewModel stv = new SubjectTemplateListsViewModel(typeNames, subjectTerms);

			svm.setCommonSubjectComponentViewModels(commonSubjectComponentViewModels);
			svm.setSubjectTemplateListsViewModel(stv);
			svm.setCommonSubjectComponentViewModels(cscvm);

			ResponseModel resp = new ResponseModel<Object, SubjectViewModel>(svm, true,
					CommonConstants.SUCCESSFUL_MESSAGE);

			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, resp);
			request.getRequestDispatcher("Subject/Edit.jsp").forward(request, response);
		} else {
			// page not found
		}
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
