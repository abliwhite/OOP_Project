package Subject.Servlets;

import java.io.IOException;
import java.util.List;

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
import Subject.Models.DbModels.CommonSubjectComponent;
import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectComponentType;
import Subject.Models.DbModels.SubjectInfo;

/**
 * Servlet implementation class AddSubjectServlet
 */
@SuppressWarnings("WeakerAccess")
public class AddSubjectServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSubjectServlet() {
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
		ResponseModel res = (ResponseModel) request.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);

		String json = new Gson().toJson(res);

		response.setContentType(CommonConstants.DATA_TRANSFER_METHOD_JSON);
		response.setCharacterEncoding(CommonConstants.CHAR_ENCODING);

		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//TODO change to protected
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String name = data.get("name").getAsString();
		String year = data.get("year").getAsString();
		String termId = data.get("termId").getAsString();
		String language = data.get("language").getAsString();
		String ects = data.get("ects").getAsString();
		String lecturerName = data.get("lecturerName").getAsString();

		if (!(fullNumericStringValidation(year) && fullNumericStringValidation(ects)
				&& fullNumericStringValidation(termId))) {
			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, new ResponseModel(false,"Please enter numeric!"));
			
			doGet(request, response);
			return;
		}

		Subject existedSubject = manager.getSubjectByFilter(name, Integer.parseInt(year), Integer.parseInt(termId));

		if (existedSubject != null) {
			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, new ResponseModel(false,"Subject already exists!"));
			
			doGet(request, response);
			return;
		}

		SubjectInfo subjectInfo = new SubjectInfo(lecturerName, null, Integer.parseInt(ects), language);
		manager.AddSubjectInfo(subjectInfo);
		int subjectInfoId = subjectInfo.getId();

		Subject subject = new Subject(name, Integer.parseInt(termId), Integer.parseInt(year), subjectInfoId);
		manager.AddSubject(subject);
		Integer subjectId = subject.getId();

		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
				new ResponseModel(subjectId.toString(), true,CommonConstants.SUCCESSFUL_MESSAGE));

		doGet(request, response);
	}

}
