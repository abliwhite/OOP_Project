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
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;
import Subject.Models.CommonSubjectTemplate;
import Subject.Models.Subject;
import Subject.Models.SubjectComponentTemplates;
import Subject.Models.SubjectInfo;

/**
 * Servlet implementation class AddSubjectServlet
 */
@WebServlet("/AddSubjectServlet")
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
		int subjectId = (int) request.getAttribute("SubjectId");

		String json = new Gson().toJson(subjectId);

		response.setContentType(CommonConstants.DATA_TRANSFER_METHOD_JSON);
		response.setCharacterEncoding(CommonConstants.CHAR_ENCODING);

		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initialManager();
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String name = data.get("name").getAsString();
		String year = data.get("year").getAsString();
		String termId = data.get("termId").getAsString();
		String language = data.get("language").getAsString();
		String ects = data.get("ects").getAsString();
		String lecturerName = data.get("lecturerName").getAsString();

		if (fullNumericStringValidation(year) && fullNumericStringValidation(ects)
				&& fullNumericStringValidation(termId)) {

			SubjectInfo subjectInfo = new SubjectInfo(lecturerName, null, Integer.parseInt(ects), language);
			manager.AddSubjectInfo(subjectInfo);
			int subjectInfoId = subjectInfo.getId();

			Subject subject = new Subject(name, Integer.parseInt(termId), Integer.parseInt(year), subjectInfoId);
			manager.AddSubject(subject);
			int subjectId = subjectInfo.getId();

			request.setAttribute("SubjectId", subjectId);
		} else {
			// validation error
		}

		doGet(request, response);
	}

}
