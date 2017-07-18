package Subject.Servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// redirectToLoginIfNotLogged(request,response);
		super.doGet(request, response);
		returnDefaultJsonToView(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);// use in every do post method
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String subjectId = data.get("subjectId").getAsString();
		String userId = data.get("userId").getAsString();

		if (!(fullNumericStringValidation(subjectId) && fullNumericStringValidation(userId))) {

		} else {
			int uId = Integer.parseInt(userId);
			int sId = Integer.parseInt(subjectId);

			UserSubject us = new UserSubject(uId, sId);
			manager.addUserSubject(us);

			List<String> subjects = manager.getUserSubjects(uId).stream().map(x -> x.getName())
					.collect(Collectors.toList());
			ResponseModel responseModel = new ResponseModel(subjects, true, CommonConstants.SUCCESSFUL_MESSAGE);
			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, responseModel);
		}
		doGet(request, response);
	}

}
