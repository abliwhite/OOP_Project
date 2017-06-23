package Subject.AppCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.mysql.jdbc.Statement;

import Common.AppCode.CommonConstants;
import Common.AppCode.DaoController;
import Common.Models.ResponseMessage;
import Database.DbCertificate;
import Database.MyDBInfo;
import Subject.Models.CommonSubjectTemplate;
import Subject.Models.Subject;
import Subject.Models.SubjectComponentTemplates;

public class SubjectManager extends DaoController implements SubjectManagerInterface {

	public static final String SUBJECT_MANAGER_ATTRIBUTE = "Subject Manager Attribute";

	private List<String> subjectColumnNames;
	private List<String> subjectComponentColumnNames;

	public SubjectManager(DataSource pool) {
		super(pool);
		subjectColumnNames = getColumnsNames(DbCertificate.ProfileTable.TABLE_NAME);
		subjectComponentColumnNames = getColumnsNames(DbCertificate.SubjectComponentTemplateTable.TABLE_NAME);

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
					DbCertificate.SubjectComponentTemplateTable.TABLE_NAME);

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

			String selectAllQuery = generator.getSelectAllQuery(DbCertificate.SubjectComponentTemplateTable.TABLE_NAME);

			ResultSet rs = st.executeQuery(selectAllQuery);

			result = getSubjectComponentTemplatesList(rs);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private List<SubjectComponentTemplates> getSubjectComponentTemplatesList(ResultSet rs) throws SQLException {
		List<SubjectComponentTemplates> result = new ArrayList<SubjectComponentTemplates>();

		while (rs.next()) {
			int i = rs.getInt(DbCertificate.SubjectComponentTemplateTable.COLUMN_NAME_ID);
			String n = rs.getString(DbCertificate.SubjectComponentTemplateTable.COLUMN_NAME_NAME);
			double m = rs.getDouble(DbCertificate.SubjectComponentTemplateTable.COLUMN_NAME_MARKPERCENTAGE);
			int num = rs.getInt(DbCertificate.SubjectComponentTemplateTable.COLUMN_NAME_NUMBER);

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
					DbCertificate.SubjectComponentTemplateTable.TABLE_NAME, sct.getId());

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
					DbCertificate.SubjectComponentTemplateTable.TABLE_NAME);

			java.sql.Statement st = con.createStatement();
			st.execute(generator.getUseDatabaseQuery());

			st.execute(deleteStatement);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<SubjectComponentTemplates> getAllSubjectComponentTemplatesByIDList(
			List<CommonSubjectTemplate> commonSubjectTemplates) {
		List<SubjectComponentTemplates> result = new ArrayList<SubjectComponentTemplates>();
		if (commonSubjectTemplates.size() == 0)
			return result;

		try {
			java.sql.Connection con = getConnection();

			String selectQuery = generator.getSelectByIDQuery(DbCertificate.SubjectComponentTemplateTable.TABLE_NAME,
					DbCertificate.SubjectComponentTemplateTable.COLUMN_NAME_ID, commonSubjectTemplates.size());

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());
			setValues(getSubjectTemplateIDsForInClause(commonSubjectTemplates), st);

			ResultSet rs = st.executeQuery();
			result = getSubjectComponentTemplatesList(rs);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private List<String> getSubjectTemplateIDsForInClause(List<CommonSubjectTemplate> commonSubjectTemplates) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < commonSubjectTemplates.size(); i++) {
			Integer id = commonSubjectTemplates.get(i).getSubjectComponentTemplateID();
			if (id != null)
				result.add(id.toString());
		}

		return result;
	}

	// todo gasatestia
	@Override
	public List<CommonSubjectTemplate> getAllCommonSubjectTemplatesBySubjectID(int subjectId) {
		List<CommonSubjectTemplate> result = new ArrayList<CommonSubjectTemplate>();

		try {
			java.sql.Connection con = getConnection();

			String selectQuery = generator.getSelectByIDQuery(DbCertificate.CommonSubjectComponentTable.TABLE_NAME,
					DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_SUBJECT_TEMPLATE_ID, 1);

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(String.valueOf(subjectId)), st);
			ResultSet rs = st.executeQuery();

			result = getCommonSubjectTemplateList(rs);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	private List<CommonSubjectTemplate> getCommonSubjectTemplateList(ResultSet rs) throws SQLException {
		List<CommonSubjectTemplate> result = new ArrayList<CommonSubjectTemplate>();

		while (rs.next()) {
			int id = rs.getInt(DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_ID);
			int sctId = rs.getInt(DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_SUBJECT_COMPONENT_TEMPLATE_ID);
			int stId = rs.getInt(DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_SUBJECT_TEMPLATE_ID);

			CommonSubjectTemplate temp = new CommonSubjectTemplate(id, sctId, stId);
			result.add(temp);
		}

		return result;
	}

	@Override
	public Subject getSubjectById(int subjectId) {
		Subject result = null;
		try {
			java.sql.Connection con = getConnection();

			String selectQuery = generator.getSelectByIDQuery(DbCertificate.SubjectTable.TABLE_NAME,
					DbCertificate.SubjectTable.COLUMN_NAME_ID, 1);

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(String.valueOf(subjectId)), st);
			ResultSet rs = st.executeQuery();
			List<Subject> temp = getSubjectsList(rs);

			result = temp.size() == 0 ? result : temp.get(0);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	private List<Subject> getSubjectsList(ResultSet rs) throws SQLException {
		List<Subject> result = new ArrayList<Subject>();

		while (rs.next()) {
			int id = rs.getInt(DbCertificate.SubjectTable.COLUMN_NAME_ID);
			int ects = rs.getInt(DbCertificate.SubjectTable.COLUMN_NAME_ECTS);
			String language = rs.getString(DbCertificate.SubjectTable.COLUMN_NAME_LANGUAGE);
			String name = rs.getString(DbCertificate.SubjectTable.COLUMN_NAME_NAME);
			String syllabusPath = rs.getString(DbCertificate.SubjectTable.COLUMN_NAME_SYLLABUSPATH);
			String lecturerName = rs.getString(DbCertificate.SubjectTable.COLUMN_NAME_LECTURERNAME);

			Subject temp = new Subject(id, name, language, ects, lecturerName, syllabusPath);
			result.add(temp);
		}

		return result;
	}
}
