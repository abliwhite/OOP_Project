package Profiles.Servlets;

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
import Subject.Models.DbModels.Subject;
import Subject.Models.ViewModels.SubjectViewModel;
import Subject.Servlets.SubjectServletParent;

/**
 * Servlet implementation class AdminSubjectSearchServlet
 */
@WebServlet("/AdminSubjectSearchServlet")
public class AdminSubjectSearchServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;
	private List<SubjectViewModel> subjectViewModels;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminSubjectSearchServlet() {
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
		ResponseModel res = (ResponseModel) request.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);

		String json = new Gson().toJson(res);

		response.setContentType(CommonConstants.DATA_TRANSFER_METHOD_JSON);
		response.setCharacterEncoding(CommonConstants.CHAR_ENCODING);

		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		subjectViewModels = subjectViewModels == null ? manager.getAllSubjectViewModels() : subjectViewModels;

		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		String searchResult = data.get("searchValue").getAsString();

		List<String> searchResults = subjectViewModels.stream()
				.filter(x -> (x.getSubject().getName() + " " + x.getTerm().getName()
						+ String.valueOf(x.getSubject().getYear())).contains(searchResult))
				.map(xx -> xx.getSubject().getName() + " " + xx.getTerm().getName()
						+ String.valueOf(xx.getSubject().getYear()))
				.distinct().collect(Collectors.toList());

		ResponseModel<String, Object> responseModel = new ResponseModel<>(searchResults, true,
				CommonConstants.SUCCESSFUL_MESSAGE);

		request.setAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE, responseModel);

		doGet(request, response);
	}

}
