package Profiles.Servlets;

import Account.AppCode.AccountManager;
import Account.AppCode.AccountManagerInterface;
import Common.AppCode.CommonConstants;
import Common.AppCode.CommonServlet;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;

public class ProfileServletParent extends CommonServlet {
	public SubjectManagerInterface subjectManager;
	public AccountManagerInterface accountManager;

	@Override
	public void initialManager() {
		subjectManager = subjectManager == null
				? (SubjectManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: subjectManager;

		accountManager = accountManager == null
				? (AccountManagerInterface) getServletContext().getAttribute(AccountManager.ACCOUNT_MANAGER_ATTRIBUTE)
				: accountManager;
	}
}
