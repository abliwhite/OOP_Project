package Common.AppCode;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import Account.AppCode.AccountManager;
import Database.MyDBInfo;
import Subject.AppCode.SubjectManager;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent e)  { 
    	DataSource pool = getDataSource();
    	ServletContext context = e.getServletContext();
    	
    	context.setAttribute(AccountManager.ACCOUNT_MANAGER_ATTRIBUTE, new AccountManager(pool));
    	context.setAttribute(SubjectManager.SUBJECT_MANAGER_ATTRIBUTE, new SubjectManager(pool));
    }
    
    //Todo gasatania sadme 
	private DataSource getDataSource() {
		DataSource pool = new DataSource();
		PoolProperties properties = new PoolProperties();
		
		//unicodi
		properties.setUrl("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER + "/mysql");
		properties.setDriverClassName("com.mysql.jdbc.Driver");
		properties.setUsername(MyDBInfo.MYSQL_USERNAME);
		properties.setPassword(MyDBInfo.MYSQL_PASSWORD);

		pool.setPoolProperties(properties);
		return pool;
	}
	
}
