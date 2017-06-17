package Subject.AppCode;

import org.apache.tomcat.jdbc.pool.DataSource;

import Common.AppCode.DaoController;
import Subject.Models.Subject;
import Subject.Models.SubjectComponentTemplates;

public class SubjectManager extends DaoController implements SubjectManagerInterface {

	public SubjectManager(DataSource pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void AddSubject(Subject subject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AddSubjectComponentTemplate(SubjectComponentTemplates sct) {
		// TODO Auto-generated method stub
		
	}

}
