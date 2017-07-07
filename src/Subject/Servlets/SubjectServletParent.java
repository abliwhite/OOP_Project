package Subject.Servlets;

import java.util.Arrays;

import javax.servlet.http.HttpServlet;

import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;

public abstract class SubjectServletParent extends HttpServlet {

	public SubjectManagerInterface manager;

	public boolean numericStringValidation(String input) {
		char[] chars = input.toCharArray();

		for (char ch : chars) {
			if (!Character.isDigit(ch))
				return false;
		}
		return true;
	}

	public boolean fullNumericStringValidation(String input) {
		if (numericStringValidation(input)) {
			return Integer.parseInt(input) > 0;
		}
		return false;
	}

	public void initialManager() {
		manager = manager == null
				? (SubjectManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: manager;
	}

	public SubjectServletParent() {
		super();
	}

}
