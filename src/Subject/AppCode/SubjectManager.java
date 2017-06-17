package Subject.AppCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.mysql.jdbc.Statement;

import Account.Models.UserProfile;
import Common.AppCode.DaoController;
import Database.DbCertificate;
import Subject.Models.Subject;
import Subject.Models.SubjectComponentTemplates;

public class SubjectManager extends DaoController implements SubjectManagerInterface {

	private List<String> subjectColumnNames;
	private List<String> subjectComponentColumnNames;
	public SubjectManager(DataSource pool) {
		super(pool);
		subjectColumnNames = getColumnsNames(DbCertificate.ProfileTable.TABLE_NAME);
		subjectComponentColumnNames=getColumnsNames(DbCertificate.SubjectComponentTamplateTable.TABLE_NAME);
	}

	@Override
	public void AddSubject(Subject subject) {
		try {
			java.sql.Connection con = getConnection();
			String insertQuery = generator.getInsertQuery(subjectColumnNames, DbCertificate.SubjectTable.TABLE_NAME);
			java.sql.PreparedStatement st = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			setValues(getSubjectValues(subject), st);
			st.executeUpdate();
			try (ResultSet generatedKeys = st.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					subject.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException();
				}
			}

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void AddSubjectComponentTemplate(SubjectComponentTemplates sct) {
		try {
			java.sql.Connection con = getConnection();
			String insertQuery = generator.getInsertQuery(subjectComponentColumnNames, DbCertificate.SubjectTable.TABLE_NAME);
			java.sql.PreparedStatement st = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			setValues(getSubjectComponentTemplateValues(sct), st);
			st.executeUpdate();
			try (ResultSet generatedKeys = st.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					sct.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException();
				}
			}

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private List<String> getSubjectComponentTemplateValues(SubjectComponentTemplates sct) {
		return Arrays.asList(sct.getName(),Integer.toString(sct.getNumber()),Double.toString(sct.getMarkPercentage()));
	}

	private List<String> getSubjectValues(Subject subject) {
		return Arrays.asList(Integer.toString(subject.getEcts()), subject.getName(), subject.getLanguage(),
				subject.getSyllabusPath(), subject.getLecturerName());
	}

}
