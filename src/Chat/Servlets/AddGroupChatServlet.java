package Chat.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Chat.Models.DbModels.ActiveStatusEnum;
import Chat.Models.DbModels.GroupChat;
import Common.AppCode.CommonConstants;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			// err
			return;
		}

		GroupChat groupChat = new GroupChat(name, CommonConstants.getDatetime(), Integer.parseInt(lobbyId),
				getUserFromSession(request).getId(), Integer.parseInt(privacyStatusId),
				ActiveStatusEnum.ACTIVE.ordinal());
		
		chatDbManager.addGroupChat(groupChat);
		

	}

}
