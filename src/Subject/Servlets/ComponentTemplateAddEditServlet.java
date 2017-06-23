package Subject.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
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
import Subject.Models.SubjectComponentTemplates;

/**
 * Servlet implementation class ComponentTemplateAddServlet
 */
@WebServlet("/ComponentTemplateAddEditServlet")
public class ComponentTemplateAddEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SubjectManagerInterface manager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ComponentTemplateAddEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		manager = manager == null
				? (SubjectManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: manager;

		List<SubjectComponentTemplates> templateList = manager.getAllSubjectComponentTemplates();

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

		manager = manager == null
				? (SubjectManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: manager;

		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String id = data.get("id") == null ? null : data.get("id").getAsString();
		String name = data.get("name").getAsString();
		String percentage = data.get("percentage").getAsString();
		String number = data.get("number").getAsString();

		SubjectComponentTemplates sct = null;

		if (id == null) {
			sct = new SubjectComponentTemplates(null, name, Double.parseDouble(percentage), Integer.parseInt(number));
			manager.AddSubjectComponentTemplate(sct);
		} else {
			sct = new SubjectComponentTemplates(Integer.parseInt(id), name, Double.parseDouble(percentage),
					Integer.parseInt(number));
			manager.UpdateSubjectComponentTemplate(sct);
		}

		doGet(request, response);
	}

}
