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
import Common.Models.ResponseModel;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;
import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectInfo;

/**
 * Servlet implementation class EditSubjectServlet
 */
public class EditSubjectServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditSubjectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		returnDefaultJsonToView(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String name = data.get("name").getAsString();
		String year = data.get("year").getAsString();
		String termId = data.get("termId").getAsString();

		String subjectInfoId = data.get("subjectInfoId").getAsString();
		String language = data.get("language").getAsString();
		String ects = data.get("ects").getAsString();
		String lecturerName = data.get("lecturerName").getAsString();
		String subjectId = data.get("subjectId").getAsString();

		if (!(fullNumericStringValidation(termId) && fullNumericStringValidation(subjectId)
				&& fullNumericStringValidation(subjectInfoId))) {

			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
					new ResponseModel(false,"Please enter numeric!"));
			doGet(request, response);
			return;
		}

		SubjectInfo subjectInfo = new SubjectInfo(Integer.parseInt(subjectInfoId), lecturerName, null,
				Integer.parseInt(ects), language);
		manager.UpdateSubjectInfo(subjectInfo);

		Subject subject = new Subject(Integer.parseInt(subjectId),name, Integer.parseInt(termId), Integer.parseInt(year), subjectInfo.getId());
		manager.UpdateSubject(subject);

		/*
		 * Subject subject = new Subject(Integer.parseInt(subjectId), name,
		 * language, Integer.parseInt(ects), lecturerName, null);
		 */

		// manager.UpdateSubject(subject);
		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
				new ResponseModel(true,CommonConstants.SUCCESSFUL_MESSAGE));
		doGet(request, response);
	}

}
