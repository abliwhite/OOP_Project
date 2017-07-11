package Authentication.Servlets;

import java.awt.color.ProfileDataException;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import Account.AppCode.AccountManager;
import Account.AppCode.AccountManagerInterface;
import Account.Models.RegisterModel;
import Account.Models.User;
import Account.Models.UserProfile;
import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;
import Database.DbCertificate;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends AuthenticationServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		
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
		JSONObject data;

		try {
			data = new JSONObject(CommonConstants.getJsonString(request).toString());
		} catch (Exception e) {
			throw new IOException("Error parsing JSON request string");
		}

		try {
			String username = data.getString("username");
			String password = data.getString("password");

			String email = data.getString("email");
			String name = data.getString("name");
			String surname = data.getString("surname");
			String gender = data.getString("gender");

			ResponseModel resp = manager.checkRegistrationValidity(new RegisterModel(username, email));
			if (resp.isSuccess()) {

				UserProfile profile = new UserProfile(name, gender, CommonConstants.getDatetime(),
						surname);
				manager.addProfile(profile);

				User user = new User(username, password, email, DbCertificate.UserTable.STUDENT_ROLE,
						null, null, profile.getId(), profile);
				manager.addUser(user);

				request.getSession().setAttribute(CommonConstants.ONLINE_USER_ATTRIBUTE_NAME, user);
				response.getWriter().println(resp.getResultMessage());
			} else {
				response.getWriter().println(resp.getResultMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
