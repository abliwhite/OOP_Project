package Chat.Servlets;

import Account.AppCode.AccountManager;
import Account.AppCode.AccountManagerInterface;
import Chat.AppCode.DbManagers.ChatDbManager;
import Chat.AppCode.DbManagers.ChatDbManagerInterface;
import Common.AppCode.CommonServlet;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;

public abstract class ChatServletParent extends CommonServlet {

	public ChatDbManagerInterface chatDbManager;
	public SubjectManagerInterface subjectManager;

	@Override
	public void initialManager() {
		chatDbManager = chatDbManager == null
				? (ChatDbManagerInterface) getServletContext().getAttribute(ChatDbManager.CHAT_DB_MANAGER_ATTRIBUTE)
				: chatDbManager;

		subjectManager = subjectManager == null
				? (SubjectManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: subjectManager;

	}

}
