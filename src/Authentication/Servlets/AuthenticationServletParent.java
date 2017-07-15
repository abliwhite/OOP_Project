package Authentication.Servlets;

import Account.AppCode.AccountManager;
import Account.AppCode.AccountManagerInterface;
import Common.AppCode.CommonServlet;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;

public abstract class AuthenticationServletParent extends CommonServlet {

	public AccountManagerInterface accountManager;
	public SubjectManagerInterface subjectManager;

	@Override
	public void initialManager() {
		accountManager = accountManager == null
				? (AccountManagerInterface) getServletContext().getAttribute(AccountManager.ACCOUNT_MANAGER_ATTRIBUTE)
				: accountManager;

		subjectManager = subjectManager == null
				? (SubjectManagerInterface) getServletContext().getAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE)
				: subjectManager;
	}
}
