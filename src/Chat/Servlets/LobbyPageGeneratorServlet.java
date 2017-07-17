package Chat.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.Lobby;
import Chat.Models.ViewModels.GroupChatViewModel;
import Chat.Models.ViewModels.LobbyViewModel;
import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;
import Subject.AppCode.SubjectManager;
import Subject.Models.ViewModels.CommonSubjectComponentViewModel;

/**
 * Servlet implementation class LobbyServlet
 */
public class LobbyPageGeneratorServlet extends ChatServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LobbyPageGeneratorServlet() {
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
		String componentId = request.getParameter("id");

		if (!fullNumericStringValidation(componentId)) {
			// redirect to err page
			return;
		}

		// LobbyManager.instance().getLobbyController(Integer.parseInt(componentId));

		int id = Integer.parseInt(componentId);
		int userId = getUserFromSession(request).getId();

		CommonSubjectComponentViewModel svm = subjectManager.getCommonSubjectComponentViewmodelById(id);
		List<GroupChat> userGroupChats = chatDbManager.getGroupChatByUserId(userId);
		List<GroupChatViewModel> activeGroupChats = new ArrayList<GroupChatViewModel>();
		Lobby lobby = chatDbManager.getLobbyByComponentId(id);
		LobbyManager.instance().getActiveGroupChats(id).forEach(x->{
			activeGroupChats.add(new GroupChatViewModel(x, LobbyManager.instance().getUsersByGroupId(lobby.getId(), x.getId())));
		});
		

		LobbyViewModel lobbyViewModel = new LobbyViewModel(svm, lobby, userGroupChats, activeGroupChats);

		ResponseModel responseModel = new ResponseModel<Object, LobbyViewModel>(lobbyViewModel, true,
				CommonConstants.SUCCESSFUL_MESSAGE);
		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, responseModel);
		request.getRequestDispatcher("Chat/Lobby.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
