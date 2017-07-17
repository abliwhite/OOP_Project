package Chat.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.Models.DbModels.ActiveStatusEnum;
import Chat.Models.DbModels.GroupChat;
import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;

/**
 * Servlet implementation class AddGroupChatServlet
 */
public class AddGroupChatServlet extends ChatServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddGroupChatServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String lobbyId = data.get("lobbyId").getAsString();
		String name = data.get("groupChatName").getAsString();
		String privacyStatusId = data.get("privacyStatusId").getAsString();

		if (!(fullNumericStringValidation(lobbyId) && fullNumericStringValidation(privacyStatusId))) {
			ResponseModel responseModel = new ResponseModel(false, "Failed!");
			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, responseModel);
			doGet(request, response);
			return;
		}

		GroupChat groupChat = new GroupChat(name, CommonConstants.getDatetime(), Integer.parseInt(lobbyId),
				getUserFromSession(request).getId(), Integer.parseInt(privacyStatusId),
				ActiveStatusEnum.ACTIVE.ordinal());

		LobbyManager.instance().createGroupChat(groupChat);

		ResponseModel responseModel = new ResponseModel(true, CommonConstants.SUCCESSFUL_MESSAGE);
		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, responseModel);
		doGet(request, response);
	}

}
