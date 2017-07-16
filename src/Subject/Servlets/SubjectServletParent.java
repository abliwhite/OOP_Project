package Subject.Servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.AppCode.CommonServlet;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;

public abstract class SubjectServletParent extends CommonServlet {

	public SubjectManagerInterface manager;

	@Override
	public void initialManager() {
		manager = manager == null
				? (SubjectManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: manager;
	}
	
}
