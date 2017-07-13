package Authentication.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DataBindingException;

import com.mysql.jdbc.Constants;

import Account.AppCode.AccountManager;
import Account.AppCode.AccountManagerInterface;
import Account.Models.AuthModel;
import Account.Models.User;
import Account.Models.UserProfile;
import Common.AppCode.DaoController;
import Common.AppCode.ViewTextContainer;
import Database.DbCertificate;
import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectTerm;
import Common.AppCode.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends AuthenticationServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		User user = manager.checkLoginValidation(new AuthModel(name, password));
		if (user != null) {
			request.setAttribute(ViewTextContainer.RESULT, "Success");
			
			//addUserInSession(request, user);
			List<Subject> allSubjects = manager.getAllSubjects();
			
			request.setAttribute("AllSubjects", allSubjects);
			if (user.getUsername().equals(DbCertificate.UserTable.ADMIN_USERNAME)
					&& user.getPassword().equals(DbCertificate.UserTable.ADMIN_PASSWORD)) {
				request.getRequestDispatcher("/Profiles/AdminProfile.jsp").forward(request, response);
			} else {
				if (user.getProfile() == null) {
					UserProfile profile = manager.getProfile(user);
					user.setUserProfile(profile);
				}
				List<Subject> userSubjects = manager.getUserSubjects(user);
				
				request.setAttribute("UserSubjects", userSubjects);
				request.setAttribute("user", user);
				request.getRequestDispatcher("/ProfilePageGeneratorServlet").forward(request, response);
			}
		} else {
			request.setAttribute(ViewTextContainer.RESULT, new ViewTextContainer());
			request.getRequestDispatcher("/Login/IncorrectDetails.jsp").forward(request, response);
		}
	}
}
