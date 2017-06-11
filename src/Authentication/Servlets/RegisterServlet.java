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
import Account.Models.RegisterModel;
import Account.Models.User;
import Account.Models.UserProfile;
import Common.AppCode.CommonConstants;
import Common.AppCode.DbCertificate;
import Common.Models.ResponseMessage;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject data;
		
		
		try {
			data = new JSONObject(getJsonString(request).toString());
		} catch (Exception e) {
			throw new IOException("Error parsing JSON request string");
		}
		
		ServletContext context = getServletContext();
		AccountManager manager = (AccountManager) context.getAttribute(AccountManager.ACCOUNT_MANAGER_ATTRIBUTE);
		User user = null;

		try {
			String username = data.getString("username");
			String password = data.getString("password");

			String email = data.getString("email");
			String name = data.getString("name");
			String surname = data.getString("surname");
			String gender = data.getString("gender");
			
			ResponseMessage resp = manager.checkRegistrationValidation(new RegisterModel(username, email));
			if (resp.isSuccess()) {
				response.getWriter().println(resp.getResultMessage());
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				
				@SuppressWarnings("null")
				UserProfile profile = new UserProfile((Integer) null,name,gender,now,DbCertificate.PROFILE_TABLE_NAME,surname);
				
				manager.addProperty(profile);
				
				RequestDispatcher forwarder = request.getRequestDispatcher("index.jsp");
				forwarder.forward(request, response);
			} else {
				
				response.getWriter().println(resp.getResultMessage());
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private StringBuffer getJsonString(HttpServletRequest request) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			/* report an error */ }
		return jb;
	}

}
