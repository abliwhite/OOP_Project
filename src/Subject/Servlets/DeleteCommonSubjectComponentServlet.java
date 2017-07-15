package Subject.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.RequestDispatcher;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;

/**
 * Servlet implementation class ComponentTemplateDeleteServlet
 */
public class DeleteCommonSubjectComponentServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCommonSubjectComponentServlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String id = data.get("id").getAsString();

		if (!fullNumericStringValidation(id)) {

			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, new ResponseModel(false,"Error"));
			doGet(request, response);
			return;
		}

		manager.DeleteCommonSubjectComponentByID(Integer.parseInt(id));
		//manager.DeleteSubjectComponentTemplateByID(Integer.parseInt(id));

		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
				new ResponseModel(false,CommonConstants.SUCCESSFUL_MESSAGE));
		doGet(request, response);
	}

}
