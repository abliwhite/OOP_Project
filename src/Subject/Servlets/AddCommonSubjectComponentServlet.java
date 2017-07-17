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

import Chat.Models.DbModels.Lobby;
import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;
import Subject.Models.DbModels.CommonSubjectComponent;
import Subject.Models.DbModels.SubjectComponentType;
import Subject.Models.ViewModels.CommonSubjectComponentViewModel;

/**
 * Servlet implementation class AddComponentTemplateServlet
 */
public class AddCommonSubjectComponentServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCommonSubjectComponentServlet() {
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
		// redirectToLoginIfNotLogged(request,response);

		ResponseModel<CommonSubjectComponentViewModel, String> res = (ResponseModel<CommonSubjectComponentViewModel, String>) request
				.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);

		if (res.isSuccess()) {

			String subjectId = res.getResultObject().toString();
			List<CommonSubjectComponentViewModel> cst = manager
					.getAllCommonSubjectComponentsViewModelBySubjectID(Integer.parseInt(subjectId));
			// getAllSubjectComponentTypesByIDList
			res.setResultList(cst);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String percentage = data.get("percentage").getAsString();
		String number = data.get("number").getAsString();
		String subjectId = data.get("subjectId").getAsString();
		String typeId = data.get("typeId").getAsString();

		if (!(fullNumericStringValidation(percentage) && fullNumericStringValidation(number)
				&& fullNumericStringValidation(subjectId) && fullNumericStringValidation(typeId))) {

			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
					new ResponseModel(false,"Please enter numeric!"));
			doGet(request, response);
			return;
		}

		CommonSubjectComponent csc = new CommonSubjectComponent(Integer.parseInt(typeId), Integer.parseInt(subjectId),
				Double.parseDouble(percentage), Integer.parseInt(number));
		
		manager.AddCommonSubjectComponent(csc);
		chatDbManager.addLobby(new Lobby(csc.getId()));
		
		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
				new ResponseModel(subjectId.toString(), true, CommonConstants.SUCCESSFUL_MESSAGE));

		doGet(request, response);
	}

}
