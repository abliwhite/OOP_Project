package Chat.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Common.Models.ResponseModel;

/**
 * Servlet implementation class RefreshLobbyPageServlet
 */
public class RefreshLobbyPageServlet extends ChatServletParent {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshLobbyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		returnDefaultJsonToView(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		
		String componentId = data.get("componentId").getAsString();
		
		if(!(fullNumericStringValidation(componentId))){
			//errr
			return;
		}
		
		ResponseModel responseModel = getLobbyViewModel(componentId, request);
		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, responseModel);
	}

}
