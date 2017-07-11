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

public abstract class CommonServlet extends HttpServlet {

	private String getRequestIp(HttpServletRequest request) {
		return request.getHeader("X-Forwarded-For");
	}

	public void addUserInSession(HttpServletRequest request, User user) {
		String userIpAddress = getRequestIp(request);

		HttpSession session = request.getSession(true);
		session.setAttribute(userIpAddress, user);
	}

	public void removeUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();
	}
	
	public User getUserFromSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if (session != null)
			return (User) session.getAttribute(getRequestIp(request));
		
		return null;
	}

	private boolean checkRequestPermission(HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		return session.getAttribute(getRequestIp(request)) != null;
	}
	
	private void redirectToLoginIfNotLogged(HttpServletRequest request,HttpServletResponse response){
		if(!checkRequestPermission(request)){
			try {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initialManager();
		//redirectToLoginIfNotLogged(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initialManager();
	}
	
	public abstract void initialManager();
	
	public CommonServlet(){
		super();
	}

}
