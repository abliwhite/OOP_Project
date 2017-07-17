package Common.AppCode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Account.Models.User;
import Common.Models.ResponseModel;

public abstract class CommonServlet extends HttpServlet {

	private String getRequestIp(HttpServletRequest request) {
		return request.getHeader("X-Forwarded-For");
	}

	public void addUserInSession(HttpServletRequest request, User user) {
		
		HttpSession session = request.getSession(true);
		session.setAttribute(session.getId(), user);
	}

	private boolean numericStringValidation(String input) {
		char[] chars = input.toCharArray();

		for (char ch : chars) {
			if (!Character.isDigit(ch))
				return false;
		}
		return true;
	}

	public boolean fullNumericStringValidation(String input) {
		if (input == null)
			return false;

		if (numericStringValidation(input)) {
			return Integer.parseInt(input) > 0;
		}
		return false;
	}

	public void removeUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();
	}

	public User getUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null)
			return (User) session.getAttribute(session.getId());

		return null;
	}

	private boolean checkRequestPermission(HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		return session.getAttribute(session.getId()) != null;
	}

	public void redirectToLoginIfNotLogged(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestPermission(request)) {
			try {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void returnDefaultJsonToView(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResponseModel res = (ResponseModel) request.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);

		String json = new Gson().toJson(res);

		response.setContentType(CommonConstants.DATA_TRANSFER_METHOD_JSON);
		response.setCharacterEncoding(CommonConstants.CHAR_ENCODING);

		response.getWriter().write(json);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initialManager();
		//redirectToLoginIfNotLogged(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initialManager();
	}

	public abstract void initialManager();

	public CommonServlet() {
		super();
	}

}
