package Chat.Models.ViewModels;

import java.util.List;

import javax.swing.text.ComponentView;

import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.Lobby;
import Chat.Models.DbModels.PrivacyStatus;
import Subject.Models.ViewModels.CommonSubjectComponentViewModel;

public class LobbyViewModel {

	private Lobby lobby;
	private List<GroupChat> userGroupChats;
	private List<GroupChatViewModel> activeGroupchats;
	private List<PrivacyStatus> privacyStatuses;

	private CommonSubjectComponentViewModel cscViewModel;

	public LobbyViewModel(CommonSubjectComponentViewModel cscViewModel, Lobby lobby, List<GroupChat> userGroupChats,
			List<GroupChatViewModel> activeGroupChats, List<PrivacyStatus> privacyStatuses) {
		this.lobby = lobby;
		this.userGroupChats = userGroupChats;
		this.activeGroupchats = activeGroupChats;
		this.cscViewModel = cscViewModel;
		this.privacyStatuses = privacyStatuses;
	}

	public List<GroupChat> getArchivedGroupChats() {
		return userGroupChats;
	}

	public void setArchivedGroupChats(List<GroupChat> userGroupChats) {
		this.userGroupChats = userGroupChats;
	}

	public List<GroupChatViewModel> getActiveGroupchats() {
		return activeGroupchats;
	}

	public void setActiveGroupchats(List<GroupChatViewModel> activeGroupchats) {
		this.activeGroupchats = activeGroupchats;
	}

	public Lobby getLobby() {
		return lobby;
	}

	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}

	public CommonSubjectComponentViewModel getCscViewModel() {
		return cscViewModel;
	}

	public void setCscViewModel(CommonSubjectComponentViewModel cscViewModel) {
		this.cscViewModel = cscViewModel;
	}

}
