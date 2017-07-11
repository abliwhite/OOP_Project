package Authentication.Servlets;

import Account.AppCode.AccountManager;
import Account.AppCode.AccountManagerInterface;
import Common.AppCode.CommonServlet;
import Subject.AppCode.SubjectManager;
import Subject.AppCode.SubjectManagerInterface;

public abstract class AuthenticationServletParent extends CommonServlet {

	public AccountManagerInterface manager;

	@Override
	public void initialManager() {
		manager = manager == null
				? (AccountManagerInterface) getServletContext().getAttribute(AccountManager.ACCOUNT_MANAGER_ATTRIBUTE)
				: manager;
	}
}
