package Subject.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Subject.Models.CommonSubjectTemplate;
import Subject.Models.SubjectComponentTemplates;

/**
 * Servlet implementation class AddUserSubjectServlet
 */
public class AddUserSubjectServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserSubjectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initialManager();
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		String name = data.get("subjectName").getAsString();
		String year = data.get("subjectYear").getAsString();
		String termId = data.get("subjectTermId").getAsString();

		if (fullNumericStringValidation(year) && fullNumericStringValidation(termId)) {
			
		} else {
			// incorrect paramaters
		}

		doGet(request, response);
	}

}
