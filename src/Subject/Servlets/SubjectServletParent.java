package Subject.Servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Chat.AppCode.DbManagers.ChatDbManager;
import Chat.AppCode.DbManagers.ChatDbManagerInterface;
import Common.AppCode.CommonServlet;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;

public abstract class SubjectServletParent extends CommonServlet {

	public SubjectManagerInterface manager;
	public ChatDbManagerInterface chatDbManager;

	@Override
	public void initialManager() {
		chatDbManager = chatDbManager == null
				? (ChatDbManagerInterface) getServletContext().getAttribute(ChatDbManager.CHAT_DB_MANAGER_ATTRIBUTE)
				: chatDbManager;

		manager = manager == null
				? (SubjectManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: manager;
	}

}
