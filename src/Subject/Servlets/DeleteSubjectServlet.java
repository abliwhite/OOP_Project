package Subject.Servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;

/**
 * Servlet implementation class DeleteSubjectServlet
 */
@WebServlet("/DeleteSubjectServlet")
public class DeleteSubjectServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteSubjectServlet() {
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

		String subjectId = data.get("subjectId").getAsString();

		if (!fullNumericStringValidation(subjectId)) {

			request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, new ResponseModel(false,"Error"));
			doGet(request, response);
			return;
		}
		int id = Integer.parseInt(subjectId);

		List<Integer> cscIds = manager.getAllCommonSubjectComponentsViewModelBySubjectID(id).stream().map(x -> x.getId())
				.collect(Collectors.toList());
		
		//TODO check if works
		if(!cscIds.isEmpty()){
			manager.deleteUserSubjectComponentsByCscIdList(cscIds);
			manager.deleteCommonSubjectComponentMaterialsByCscIdList(cscIds);
		}
				
		manager.deleteCommonSubjectComponentBySubjectId(id);
		manager.deleteUserSubjectBySubjectId(id);
		manager.deleteSubjectById(id);

		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE,
				new ResponseModel(true,CommonConstants.SUCCESSFUL_MESSAGE));
		doGet(request, response);
	}

}
