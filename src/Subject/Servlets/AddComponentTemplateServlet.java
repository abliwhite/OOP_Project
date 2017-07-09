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
import Subject.Models.CommonSubjectTemplate;
import Subject.Models.SubjectComponentTemplates;

/**
 * Servlet implementation class AddComponentTemplateServlet
 */
@WebServlet("/AddComponentTemplateServlet")
public class AddComponentTemplateServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;

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
		// redirectToLoginIfNotLogged(request,response);

		ResponseModel<SubjectComponentTemplates, String> res = (ResponseModel<SubjectComponentTemplates, String>) request
				.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);

		if (res.isSuccess()) {
			
			String subjectId = res.getResultObject().toString();
			List<CommonSubjectTemplate> cst = manager
					.getAllCommonSubjectTemplatesBySubjectID(Integer.parseInt(subjectId));
			List<SubjectComponentTemplates> templateList = manager.getAllSubjectComponentTemplatesByIDList(cst);
			res.setResultList(templateList);
		}
		String json = new Gson().toJson(res);
		
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
		String percentage = data.get("percentage").getAsString();
		String number = data.get("number").getAsString();
		String subjectId = data.get("subjectId").getAsString();

		if (!(fullNumericStringValidation(percentage) && fullNumericStringValidation(number)
				&& fullNumericStringValidation(subjectId))) {

			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
					new ResponseModel("Please enter numeric!", false));
			doGet(request, response);
			return;
		}

		SubjectComponentTemplates sct = new SubjectComponentTemplates(name, Double.parseDouble(percentage),
				Integer.parseInt(number));

		if (manager.CheckIfExistsSubjectComponentTemplate(sct)) {

			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
					new ResponseModel("Subject component already exists!", false));
			doGet(request, response);
			return;
		}

		manager.AddSubjectComponentTemplate(sct);
		manager.AddCommonSubjectTemplate(new CommonSubjectTemplate(sct.getId(), Integer.parseInt(subjectId)));

		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
				new ResponseModel(subjectId.toString(), true, CommonConstants.SUCCESSFUL_MESSAGE));

		doGet(request, response);
	}

}
