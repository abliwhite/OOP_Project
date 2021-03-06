package Chat.Servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Account.AppCode.AccountManager;
import Account.AppCode.AccountManagerInterface;
import Account.Models.User;
import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.AppCode.DbManagers.ChatDbManager;
import Chat.AppCode.DbManagers.ChatDbManagerInterface;
import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.Lobby;
import Chat.Models.DbModels.PrivacyStatus;
import Chat.Models.ViewModels.GroupChatViewModel;
import Chat.Models.ViewModels.LobbyViewModel;
import Common.AppCode.CommonConstants;
import Common.AppCode.CommonServlet;
import Common.Models.ResponseModel;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;
import Subject.Models.ViewModels.CommonSubjectComponentViewModel;

public abstract class ChatServletParent extends CommonServlet {

	public ChatDbManagerInterface chatDbManager;
	public SubjectManagerInterface subjectManager;
	public AccountManagerInterface accountManager;

	@Override
	public void initialManager() {
		chatDbManager = chatDbManager == null
				? (ChatDbManagerInterface) getServletContext().getAttribute(ChatDbManager.CHAT_DB_MANAGER_ATTRIBUTE)
				: chatDbManager;

		subjectManager = subjectManager == null
				? (SubjectManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: subjectManager;
		accountManager = accountManager == null

				? (AccountManagerInterface) getServletContext().getAttribute(AccountManager.ACCOUNT_MANAGER_ATTRIBUTE)
				: accountManager;
	}

	public ResponseModel getLobbyViewModel(String componentId, HttpServletRequest request) {
		int id = Integer.parseInt(componentId);
		User user = getUserFromSession(request);
		int userId = user.getId();

		CommonSubjectComponentViewModel svm = subjectManager.getCommonSubjectComponentViewmodelById(id);
		List<GroupChat> userGroupChats = chatDbManager.getGroupChatByUserId(userId);
		List<GroupChatViewModel> activeGroupChats = new ArrayList<GroupChatViewModel>();
		Lobby lobby = chatDbManager.getLobbyByComponentId(id);

		LobbyManager.instance().getActiveGroupChats(id).forEach(x -> {
			activeGroupChats.add(
					new GroupChatViewModel(x, LobbyManager.instance().getUsersByGroupId(lobby.getId(), x.getId())));
		});

		List<PrivacyStatus> privacyStatuses = chatDbManager.getAllPrivacyStatuses();
		GroupChat activeUserGroupChat = LobbyManager.instance().getUserActiveGroupChat(user, lobby.getId());
		GroupChatViewModel activeUserGroupChatViewModel = null;
		if(activeUserGroupChat != null)
			activeUserGroupChatViewModel = new GroupChatViewModel(activeUserGroupChat,
				LobbyManager.instance().getUsersByGroupId(lobby.getId(), activeUserGroupChat.getId()));

		LobbyViewModel lobbyViewModel = new LobbyViewModel(svm, lobby, userGroupChats, activeGroupChats,
				privacyStatuses, activeUserGroupChatViewModel);

		ResponseModel responseModel = new ResponseModel<Object, LobbyViewModel>(lobbyViewModel, true,
				CommonConstants.SUCCESSFUL_MESSAGE);

		return responseModel;
	}

}
