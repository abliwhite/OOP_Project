package Subject.Servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Account.AppCode.AccountManager;
import Account.AppCode.AccountManagerInterface;
import Common.AppCode.CommonConstants;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;

/**
 * Servlet implementation class SubjectAddEditServlet
 */
@WebServlet("/SubjectAddEditServlet")
public class SubjectAddEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String requestType = request.getParameter("type");
		ServletContext context = getServletContext();
		SubjectManagerInterface manager = (SubjectManagerInterface) context
				.getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE);
		
		
		if (requestType.equals("Add")) {
			addCase(request);
		}else{
			editCase(request);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("SubjectAddEdit.jsp").forward(request, response);
	}

	private void editCase(HttpServletRequest request) {
		request.setAttribute(CommonConstants.ADD_EDIT_PAGE_TITLE_ATTRIBUTE, "Edit");
		
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
