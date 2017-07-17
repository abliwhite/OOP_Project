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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if(!checkRequestPermission(request)){
			//non permission
			return;
		}
		String componentId = request.getParameter("id");

		if (!fullNumericStringValidation(componentId)) {
			// redirect to err page
			return;
		}

		// LobbyManager.instance().getLobbyController(Integer.parseInt(componentId));

		ResponseModel responseModel = getLobbyViewModel(componentId, request);
		
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
