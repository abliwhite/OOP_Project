package Subject.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Common.AppCode.CommonConstants;
import Common.Models.ResponseModel;
import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectComponentMaterial;
import Subject.Models.ViewModels.CommonSubjectComponentViewModel;
import Subject.Models.ViewModels.SubjectViewModel;

/**
 * Servlet implementation class ComponentMaterialsPageGeneratorServlet
 */
public class ComponentMaterialsPageGeneratorServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComponentMaterialsPageGeneratorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		redirectToLoginIfNotLogged(request, response);
		
		int componentId = Integer.parseInt(request.getParameter("id"));
		CommonSubjectComponentViewModel svm= manager.getCommonSubjectComponentViewmodelById(componentId);
		List<SubjectComponentMaterial> materials=manager.getSubjectComponentMaterialsByComponentId(componentId);
		svm.setMaterials(materials);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ResponseModel resp = new ResponseModel<Object, CommonSubjectComponentViewModel>(svm, true,
				CommonConstants.SUCCESSFUL_MESSAGE);

		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, resp);
		request.getRequestDispatcher("Subject/Materials.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
