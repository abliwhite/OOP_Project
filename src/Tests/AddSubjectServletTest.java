package Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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

	@Before
	public void initMocks() throws IOException {
		requestMock = mock(HttpServletRequest.class);
		responseMock = mock(HttpServletResponse.class);
		servletContextMock = mock(ServletContext.class);
		subjectManagerMock = mock(SubjectManager.class);

		stringWriter = new StringWriter();
		when(responseMock.getWriter()).thenReturn(new PrintWriter(stringWriter));
		Mockito.doReturn(subjectManagerMock).when(servletContextMock)
				.getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE);

	}

	@Test
	public void testNonNumericValue() throws IOException, ServletException {
		when(requestMock.getParameter("name")).thenReturn("4");
		when(requestMock.getParameter("year")).thenReturn("ako");
		when(requestMock.getParameter("termId")).thenReturn("ako");
		when(requestMock.getParameter("language")).thenReturn("ako");
		when(requestMock.getParameter("ects")).thenReturn("6");
		when(requestMock.getParameter("lecturerName")).thenReturn("shoteqsa");
		when(requestMock.getServletContext()).thenReturn(servletContextMock);

		// when(subjectManagerMock.getSubjectByFilter(any(),any(),any())).thenReturn(null);

		new AddSubjectServlet().doPost(requestMock, responseMock);
		ResponseModel res = (ResponseModel) requestMock.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);

		assertEquals(res, new ResponseModel("Please enter numeric!", false));

		// Some other methods
		// verify(responseMock).getWriter();
		// verify(requestMock, times(2)).getParameter(anyString());
	}

}
