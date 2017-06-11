package Authentication.Servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import Account.AppCode.AccountManager;
import Account.Models.RegisterModel;
import Account.Models.User;
import Common.AppCode.CommonConstants;
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
		JSONObject data = new Gson().fromJson(request.getReader(), JSONObject.class);
		ServletContext context = getServletContext();
		AccountManager manager = (AccountManager) context.getAttribute(AccountManager.ACCOUNT_MANAGER_ATTRIBUTE);

		User user = null;

		try {
			String username = data.get("username").toString();
			String password = data.get("password").toString();
			
			String email = data.get("email").toString();
			String name = data.get("name").toString();
			String surname = data.get("surname").toString();
			String gender = data.get("gender").toString();
			
			ResponseMessage resp =manager.checkRegistrationValidation(new RegisterModel(username, email));
			if(resp.isSuccess()){
				
			}else{
				response.getWriter().println(resp.getErrorMessage());;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
