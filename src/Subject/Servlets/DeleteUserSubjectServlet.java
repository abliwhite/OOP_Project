package Subject.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class DeleteUserSubjectServlet
 */
public class DeleteUserSubjectServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserSubjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String subjectId = data.get("subjectId").getAsString();
		String userId = data.get("userId").getAsString();
		
		if(!(fullNumericStringValidation(subjectId) && fullNumericStringValidation(userId))){
			//error
			return;
		}
		
		int subjId = Integer.parseInt(subjectId);
		int usId = Integer.parseInt(userId);
		
		
		
	}

}
