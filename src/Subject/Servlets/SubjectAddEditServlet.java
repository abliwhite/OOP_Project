package Subject.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

import Account.AppCode.AccountManager;
import Account.AppCode.AccountManagerInterface;
import Common.AppCode.CommonConstants;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;
import Subject.Models.CommonSubjectTemplate;
import Subject.Models.Subject;
import Subject.Models.SubjectComponentTemplates;
import Subject.Models.SubjectComponentTemplatesViewEntity;
import Subject.Models.SubjectViewEntity;

/**
 * Servlet implementation class SubjectAddEditServlet
 */
@WebServlet("/SubjectAddEditServlet")
public class SubjectAddEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectManagerInterface manager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubjectAddEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String subjectId = request.getParameter("id");
		manager = manager == null
				? (SubjectManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: manager;

		if (!isEdit(subjectId)) {
			addCase(request);
		} else {
			int id = Integer.parseInt(subjectId);
			editCase(request, id);
		}

		request.getRequestDispatcher("Subject/SubjectAddEdit.jsp").forward(request, response);
	}

	private Boolean isEdit(String subjectId) {
		if (subjectId == null)
			return false;
		boolean result = true;
		for (int i = 0; i < subjectId.length(); i++) {
			result = result && Character.isDigit(subjectId.charAt(i));
		}
		return result;
	}

	private void editCase(HttpServletRequest request, int subjectId) {
		List<CommonSubjectTemplate> cst = manager.getAllCommonSubjectTemplatesBySubjectID(subjectId);
		List<SubjectComponentTemplates> sc = manager.getAllSubjectComponentTemplatesByIDList(cst);

		Subject subject = manager.getSubjectById(subjectId);

		// check subject nullable
		if (subject == null){
			addCase(request);
			return;
		}
		
		SubjectViewEntity subjectViewEntity = new SubjectViewEntity(subject,
				getSubjectComponentTemplatesViewEntities(sc));

		request.setAttribute(SubjectViewEntity.SUBJECT_VIEW_ENTITY_ATTRIBUTE, subjectViewEntity);
		request.setAttribute(CommonConstants.ADD_EDIT_PAGE_TITLE_ATTRIBUTE, "Edit");
	}

	private List<SubjectComponentTemplatesViewEntity> getSubjectComponentTemplatesViewEntities(
			List<SubjectComponentTemplates> sc) {
		List<SubjectComponentTemplatesViewEntity> result = new ArrayList<SubjectComponentTemplatesViewEntity>();

		for (int i = 0; i < sc.size(); i++) {
			result.add(new SubjectComponentTemplatesViewEntity(sc.get(i)));
		}
		return result;
	}

	private void addCase(HttpServletRequest request) {
		request.setAttribute(CommonConstants.ADD_EDIT_PAGE_TITLE_ATTRIBUTE, "Add");

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
