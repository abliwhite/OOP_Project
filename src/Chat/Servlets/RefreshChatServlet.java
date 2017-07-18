package Chat.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Account.Models.User;
import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.InternalMessage;
import Chat.Models.ViewModels.GroupChatViewModel;
import Chat.Models.ViewModels.InternalMessageViewModel;
import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;

/**
 * Servlet implementation class RefreshChatServlet
 */
@WebServlet("/RefreshChatServlet")
public class RefreshChatServlet extends ChatServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RefreshChatServlet() {
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
		String groupId = data.get("groupId").getAsString();
		String lobbyId = data.get("lobbyId").getAsString();

		if (!(fullNumericStringValidation(groupId) && fullNumericStringValidation(lobbyId))) {

			ResponseModel responseModel = new ResponseModel(false, "Enter numeric!");
			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, responseModel);
			doGet(request, response);
			return;
		}
		int lId = Integer.parseInt(lobbyId);
		int gId = Integer.parseInt(groupId);

		List<InternalMessageViewModel> messages = new ArrayList<InternalMessageViewModel>();
		LobbyManager.instance().getMessagesByGroupId(lId, gId).forEach(x -> {
			messages.add(new InternalMessageViewModel(x, accountManager.getUserById(x.getSenderID())));
		});
		
		Set<User> users = LobbyManager.instance().getUsersByGroupId(lId, gId);
		GroupChat groupChat = LobbyManager.instance().getGroupChatById(lId, gId);

		GroupChatViewModel groupChatViewModel = new GroupChatViewModel(groupChat, users, messages);

		ResponseModel responseModel = new ResponseModel<Object, GroupChatViewModel>(groupChatViewModel, true,
				CommonConstants.SUCCESSFUL_MESSAGE);
		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, responseModel);
		doGet(request, response);
	}

}
