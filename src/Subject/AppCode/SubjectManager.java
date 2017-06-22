package Subject.AppCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.mysql.jdbc.Statement;

import Common.AppCode.DaoController;
import Database.DbCertificate;
import Database.MyDBInfo;
import Subject.Models.Subject;
import Subject.Models.SubjectComponentTemplates;

public class SubjectManager extends DaoController implements SubjectManagerInterface {

	public static final String SUBJECT_MANAGER_ATTRIBUTE = "Subject Manager Attribute";

	private List<String> subjectColumnNames;
	private List<String> subjectComponentColumnNames;

	public SubjectManager(DataSource pool) {
		super(pool);
		subjectColumnNames = getColumnsNames(DbCertificate.ProfileTable.TABLE_NAME);
		subjectComponentColumnNames = getColumnsNames(DbCertificate.SubjectComponentTamplateTable.TABLE_NAME);

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
			String insertQuery = generator.getInsertQuery(subjectComponentColumnNames,
					DbCertificate.SubjectComponentTamplateTable.TABLE_NAME);

			con.createStatement().executeQuery(generator.getUseDatabaseQuery());
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
		return Arrays.asList(sct.getName(), Double.toString(sct.getMarkPercentage()),
				Integer.toString(sct.getNumber()));
	}

	private List<String> getSubjectValues(Subject subject) {
		return Arrays.asList(Integer.toString(subject.getEcts()), subject.getName(), subject.getLanguage(),
				subject.getSyllabusPath(), subject.getLecturerName());
	}

	@Override
	public List<SubjectComponentTemplates> getAllSubjectComponentTemplates() {
		List<SubjectComponentTemplates> result = new ArrayList<SubjectComponentTemplates>();

		try {
			java.sql.Connection con = getConnection();
			java.sql.Statement st = con.createStatement();

			String selectAllQuery = generator.getSelectAllQuery(DbCertificate.SubjectComponentTamplateTable.TABLE_NAME);

			ResultSet rs = st.executeQuery(selectAllQuery);

			result = getComponentTemplatesList(rs);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private List<SubjectComponentTemplates> getComponentTemplatesList(ResultSet rs) throws SQLException {
		List<SubjectComponentTemplates> result = new ArrayList<SubjectComponentTemplates>();

		while (rs.next()) {
			int i = rs.getInt(DbCertificate.SubjectComponentTamplateTable.COLUMN_NAME_ID);
			String n = rs.getString(DbCertificate.SubjectComponentTamplateTable.COLUMN_NAME_NAME);
			double m = rs.getDouble(DbCertificate.SubjectComponentTamplateTable.COLUMN_NAME_MARKPERCENTAGE);
			int num = rs.getInt(DbCertificate.SubjectComponentTamplateTable.COLUMN_NAME_NUMBER);

			SubjectComponentTemplates temp = new SubjectComponentTemplates(i, n, m, num);
			result.add(temp);
		}

		return result;
	}

	@Override
	public void UpdateSubjectComponentTemplate(SubjectComponentTemplates sct) {
		try {
			java.sql.Connection con = getConnection();
			String updateStatement = generator.getUpdateByIdQuery(subjectComponentColumnNames,
					DbCertificate.SubjectComponentTamplateTable.TABLE_NAME, sct.getId());

			java.sql.PreparedStatement st = con.prepareStatement(updateStatement);

			setValues(getSubjectComponentTemplateValues(sct), st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void DeleteSubjectComponentTemplateByID(int id) {
		try {
			java.sql.Connection con = getConnection();
			String deleteStatement = generator.getDeleteByIdQuery(id,
					DbCertificate.SubjectComponentTamplateTable.TABLE_NAME);

			java.sql.Statement st = con.createStatement();
			st.execute(deleteStatement);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
