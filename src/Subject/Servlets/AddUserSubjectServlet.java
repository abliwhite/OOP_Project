package Subject.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Common.AppCode.CommonConstants;
import Subject.Models.DbModels.CommonSubjectComponent;
import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectComponentType;
import Subject.Models.DbModels.UserSubject;

/**
 * Servlet implementation class AddUserSubjectServlet
 */
public class AddUserSubjectServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserSubjectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//redirectToLoginIfNotLogged(request,response);
		String userId = (String) request.getAttribute("userId");

		String json = new Gson().toJson(userId);

		response.setContentType(CommonConstants.DATA_TRANSFER_METHOD_JSON);
		response.setCharacterEncoding(CommonConstants.CHAR_ENCODING);

		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);//use in every do post method
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String name = data.get("subjectName").getAsString();
		String year = data.get("subjectYear").getAsString();
		String termId = data.get("subjectTermId").getAsString();
		String userId =  data.get("userId").getAsString();

		if (!(fullNumericStringValidation(year) && fullNumericStringValidation(termId))) {
			
		} else {
			Subject subject = manager.getSubjectByFilter(name, Integer.parseInt(year), Integer.parseInt(termId));
			UserSubject us = new UserSubject(Integer.parseInt(userId), subject.getId());
			manager.addUserSubject(us);
			
			
			request.setAttribute("userId", userId);
		}
		doGet(request, response);
	}

}
