package Chat.Servlets;

import Account.AppCode.AccountManager;
import Account.AppCode.AccountManagerInterface;
import Chat.AppCode.DbManagers.ChatDbManagerInterface;
import Common.AppCode.CommonServlet;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;

public abstract class ChatServletParent extends CommonServlet {

	private ChatDbManagerInterface chatDbManager;
	
	@Override
	public void initialManager() {
		chatDbManager = chatDbManager == null
				? (ChatDbManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: chatDbManager;

	}
	
}
