package Subject.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.RequestDispatcher;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;

/**
 * Servlet implementation class ComponentTemplateDeleteServlet
 */
@WebServlet("/ComponentTemplateDeleteServlet")
public class ComponentTemplateDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private SubjectManagerInterface manager;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComponentTemplateDeleteServlet() {
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
		manager = manager == null
				? (SubjectManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: manager;

		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		
		String id = data.get("id").getAsString();
		
		manager.DeleteSubjectComponentTemplateByID(Integer.parseInt(id));
		
		//doGet(request, response);
		javax.servlet.RequestDispatcher rd = getServletContext().getRequestDispatcher("/ComponentTemplateAddEditServlet");
		rd.forward(request, response);   
	}

}
