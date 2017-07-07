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
import Subject.Models.SubjectComponentTemplates;

/**
 * Servlet implementation class AddComponentTemplateServlet
 */
@WebServlet("/AddComponentTemplateServlet")
public class AddComponentTemplateServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;
	private SubjectManagerInterface manager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComponentTemplateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initialManager();
		String subjectId = (String) request.getAttribute("SubjectId");

		List<CommonSubjectTemplate> cst = manager.getAllCommonSubjectTemplatesBySubjectID(Integer.parseInt(subjectId));
		List<SubjectComponentTemplates> templateList = manager.getAllSubjectComponentTemplatesByIDList(cst);

		String json = new Gson().toJson(templateList);

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

		// String id = data.get("id") == null ? null :
		// data.get("id").getAsString();
		String name = data.get("name").getAsString();
		String percentage = data.get("percentage").getAsString();
		String number = data.get("number").getAsString();
		String subjectId = data.get("subjectId").getAsString();

		if (fullNumericStringValidation(percentage) && fullNumericStringValidation(number)
				&& fullNumericStringValidation(subjectId)) {

			SubjectComponentTemplates sct = new SubjectComponentTemplates(name, Double.parseDouble(percentage),
					Integer.parseInt(number));

			manager.AddSubjectComponentTemplate(sct);
			manager.AddCommonSubjectTemplate(new CommonSubjectTemplate(sct.getId(), Integer.parseInt(subjectId)));

			request.setAttribute("SubjectId", subjectId);
		} else {
			// incorrect paramaters
		}

		doGet(request, response);
	}

}
