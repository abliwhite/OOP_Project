package Authentication.Servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Account.Models.User;
import Common.AppCode.DaoController;
import Common.AppCode.DaoInterface;
import Common.AppCode.ViewTextContainer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		ServletContext context = getServletContext();
		Account.AppCode.AccountManager am = (Account.AppCode.AccountManager) context
				.getAttribute(Account.AppCode.AccountManager.ACCOUNT_MANAGER_ATTRIBUTE);

		am.addProperty(new User(1, "admin", "admin", null, null, null, null, 0, null, null));

		User user = am.checkLoginValidation(name, password);
		if (user != null) {
			request.setAttribute(ViewTextContainer.RESULT, "Gilocav");
			if (user.getUsername().equals("admin")) {
				request.getRequestDispatcher("/Profiles/AdminProfile.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/Profiles/UserProfile.jsp").forward(request, response);
			}
		} else {
			request.setAttribute(ViewTextContainer.RESULT, new ViewTextContainer());
			request.getRequestDispatcher("/Login/IncorrectDetails.jsp").forward(request, response);
		}
	}
}
