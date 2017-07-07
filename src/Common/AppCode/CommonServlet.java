package Common.AppCode;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Account.Models.User;

public abstract class CommonServlet extends HttpServlet {

	private String getRequestIp(HttpServletRequest request) {
		return request.getHeader("X-Forwarded-For");
	}

	public void AddUserInSession(HttpServletRequest request, User user) {
		String userIpAddress = getRequestIp(request);

		HttpSession session = request.getSession(true);
		session.setAttribute(userIpAddress, user);
	}

	public void RemoveUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();
	}

	public boolean checkRequestPermission(HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		return session.getAttribute(getRequestIp(request)) != null;
	}
	
	public abstract void initialManager();
	
	public CommonServlet(){
		super();
	}

}
