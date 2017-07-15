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
import Subject.Models.DbModels.SubjectComponentType;

/**
 * Servlet implementation class EditComponentTemplateServlet
 */
public class EditComponentTemplateServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComponentTemplateServlet() {
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
		returnDefaultJsonToView(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initialManager();
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String id = data.get("id").getAsString();
		String typeId = data.get("typeId").getAsString();
		String percentage = data.get("percentage").getAsString();
		String number = data.get("number").getAsString();
		// String subjectId = data.get("subjectId").getAsString();

		if (!(fullNumericStringValidation(id) && fullNumericStringValidation(percentage)
				&& fullNumericStringValidation(number) && fullNumericStringValidation(typeId))) {

			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
					new ResponseModel(false,"Please enter numeric!"));
			doGet(request, response);
			return;
		}

		CommonSubjectComponent csc = new CommonSubjectComponent(Integer.parseInt(id), Integer.parseInt(typeId),
				Double.parseDouble(percentage), Integer.parseInt(number));
		manager.UpdateCommonSubjectComponent(csc);

		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
				new ResponseModel(true,CommonConstants.SUCCESSFUL_MESSAGE));

		doGet(request, response);

	}
}
