package Tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Common.Models.ResponseModel;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;
import Subject.Servlets.AddSubjectServlet;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddSubjectServletTest {

	private HttpServletRequest requestMock;
	private HttpServletResponse responseMock;
	private ServletContext servletContextMock;
	private SubjectManagerInterface subjectManagerMock;
	private StringWriter stringWriter;
	private AddSubjectServlet addSubjectServlet;

	@Before
	public void initMocks() throws IOException {
		requestMock = mock(HttpServletRequest.class);
		responseMock = mock(HttpServletResponse.class);
		servletContextMock = mock(ServletContext.class);
		subjectManagerMock = mock(SubjectManager.class);

		addSubjectServlet = new AddSubjectServlet() {
			public ServletContext getServletContext() {
				return servletContextMock; // return the mock
			}
		};

		stringWriter = new StringWriter();
		when(responseMock.getWriter()).thenReturn(new PrintWriter(stringWriter));

	}

	@Test
	public void testNonNumericValue() throws IOException, ServletException {
		/*
		 * when(requestMock.getParameter("name")).thenReturn("4");
		 * when(requestMock.getParameter("year")).thenReturn("ako");
		 * when(requestMock.getParameter("termId")).thenReturn("ako");
		 * when(requestMock.getParameter("language")).thenReturn("ako");
		 * when(requestMock.getParameter("ects")).thenReturn("6");
		 * when(requestMock.getParameter("lecturerName")).thenReturn("shoteqsa")
		 * ;
		 */
		when(requestMock.getServletContext()).thenReturn(servletContextMock);

		String json = "{\"name\":213213213, \"year\":222, \"termId\":ako, \"language\":ako, \"ects\":6,\"lecturerName\":shoteqsa}";

		when(requestMock.getReader()).thenReturn(new BufferedReader(new StringReader(json)));

		Mockito.doReturn(subjectManagerMock).when(servletContextMock)
				.getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE);
		// when(subjectManagerMock.getSubjectByFilter(any(),any(),any())).thenReturn(null);

		addSubjectServlet.doPost(requestMock, responseMock);
		//new BufferedReader(new StringReader(responseMock.)).readLine();
		
		String data = new Gson().toJson(responseMock.getWriter(), JsonObject.class);
		
		System.out.println(data);
		//ResponseModel res = (ResponseModel) responseMock.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);

		//assertEquals(res, new ResponseModel("Please enter numeric!", false));

		// Some other methods
		// verify(responseMock).getWriter();
		// verify(requestMock, times(2)).getParameter(anyString());
	}

}
