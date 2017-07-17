package Subject.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Account.Models.User;
import Authentication.Servlets.AuthenticationServletParent;
import Subject.Models.DbModels.Subject;

/**
 * Servlet implementation class UserSubjectPageGeneratorServlet
 */
public class UserSubjectPageGeneratorServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSubjectPageGeneratorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		String subject = request.getParameter("subject");
		String year = request.getParameter("year");
		String termId = request.getParameter("term");
	
		Subject existedSubject = manager.getSubjectByFilter(subject, Integer.parseInt(year), Integer.parseInt(termId));
		
		List<User> classmates = manager.getSubjectAllUsers(subject, Integer.parseInt(year), Integer.parseInt(termId));
		
		request.setAttribute("classmates", classmates);
		request.setAttribute("subject", existedSubject);
		request.getRequestDispatcher("/Subject/SubjectPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
