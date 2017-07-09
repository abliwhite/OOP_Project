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
import Database.DbCertificate;
import Database.MyDBInfo;
import Subject.Models.CommonSubjectTemplate;
import Subject.Models.Subject;
import Subject.Models.SubjectComponentTemplates;
import Subject.Models.SubjectInfo;
import Subject.Models.SubjectTerm;
import Subject.Models.UserSubject;

public class SubjectManager extends DaoController implements SubjectManagerInterface {

	public static final String SUBJECT_MANAGER_ATTRIBUTE = "Subject Manager Attribute";

	private List<String> subjectInfoColumnNames;
	private List<String> subjectColumnNames;
	private List<String> subjectComponentColumnNames;
	private List<String> commonSubjectTemplateColumnNames;
	private List<String> userSubjectColumnNames;

	public SubjectManager(DataSource pool) {
		super(pool);
		subjectInfoColumnNames = getColumnsNames(DbCertificate.SubjectInfoTable.TABLE_NAME);
		subjectColumnNames = getColumnsNames(DbCertificate.SubjectTable.TABLE_NAME);
		subjectComponentColumnNames = getColumnsNames(DbCertificate.SubjectComponentTemplateTable.TABLE_NAME);
		commonSubjectTemplateColumnNames = getColumnsNames(DbCertificate.CommonSubjectComponentTable.TABLE_NAME);
		userSubjectColumnNames = getColumnsNames(DbCertificate.UserSubjectTable.TABLE_NAME);
	}

	@Override
	public void AddSubjectInfo(SubjectInfo subjectInfo) {
		try {
			java.sql.Connection con = getConnection();
			String insertQuery = generator.getInsertQuery(subjectInfoColumnNames,
					DbCertificate.SubjectInfoTable.TABLE_NAME);
			con.createStatement().executeQuery(generator.getUseDatabaseQuery());
			java.sql.PreparedStatement st = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

			setValues(getSubjectInfoValues(subjectInfo), st);

			st.executeUpdate();
			try (ResultSet generatedKeys = st.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					subjectInfo.setId(generatedKeys.getInt(1));
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

	private List<String> getSubjectInfoValues(SubjectInfo subjectInfo) {
		return Arrays.asList(subjectInfo.getLecturerName(), subjectInfo.getSyllabusPath(),
				Integer.toString(subjectInfo.getEcts()), subjectInfo.getLanguage());
	}

	@Override
	public void AddSubject(Subject subject) {
		try {
			java.sql.Connection con = getConnection();
			String insertQuery = generator.getInsertQuery(subjectColumnNames, DbCertificate.SubjectTable.TABLE_NAME);
			con.createStatement().executeQuery(generator.getUseDatabaseQuery());
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
		return Arrays.asList(subject.getName(), Integer.toString(subject.getTermId()),
				Integer.toString(subject.getYear()),Integer.toString(subject.getSubjectInfoID()));
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
			String name = rs.getString(DbCertificate.SubjectTable.COLUMN_NAME_NAME);
			int termId = rs.getInt(DbCertificate.SubjectTable.COLUMN_NAME_TERM_ID);
			int year = rs.getInt(DbCertificate.SubjectTable.COLUMN_NAME_YEAR);
			int subjectInfoID = rs.getInt(DbCertificate.SubjectTable.COLUMN_NAME_SUBJECT_INFO_ID);
			/*
			 * int ects =
			 * rs.getInt(DbCertificate.SubjectTable.COLUMN_NAME_ECTS); String
			 * language =
			 * rs.getString(DbCertificate.SubjectTable.COLUMN_NAME_LANGUAGE);
			 * String name =
			 * rs.getString(DbCertificate.SubjectTable.COLUMN_NAME_NAME); String
			 * syllabusPath =
			 * rs.getString(DbCertificate.SubjectTable.COLUMN_NAME_SYLLABUSPATH)
			 * ; String lecturerName =
			 * rs.getString(DbCertificate.SubjectTable.COLUMN_NAME_LECTURERNAME)
			 * ;
			 */
			Subject temp = new Subject(id, name, termId, year, subjectInfoID);
			result.add(temp);
		}

		return result;
	}

	@Override
	public void AddCommonSubjectTemplate(CommonSubjectTemplate cst) {
		try {
			java.sql.Connection con = getConnection();
			String insertQuery = generator.getInsertQuery(commonSubjectTemplateColumnNames,
					DbCertificate.CommonSubjectComponentTable.TABLE_NAME);
			java.sql.PreparedStatement st = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

			setValues(getCommonSubjectTemplateValues(cst), st);

			st.executeUpdate();
			try (ResultSet generatedKeys = st.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					cst.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException();
				}
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<String> getCommonSubjectTemplateValues(CommonSubjectTemplate cst) {
		return Arrays.asList(String.valueOf(cst.getSubjectComponentTemplateID()),
				String.valueOf(cst.getSubjectTemplateID()));
	}

	@Override
	public void UpdateSubject(Subject subject) {
		try {
			java.sql.Connection con = getConnection();
			String updateStatement = generator.getUpdateByIdQuery(subjectColumnNames,
					DbCertificate.SubjectTable.TABLE_NAME, subject.getId());

			java.sql.PreparedStatement st = con.prepareStatement(updateStatement);

			setValues(getSubjectValues(subject), st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void DeleteCommonSubjectTemplateByIDFields(int subjectId, int subjectComponentId) {
		try {
			java.sql.Connection con = getConnection();
			String deleteStatement = "DELETE FROM " + DbCertificate.CommonSubjectComponentTable.TABLE_NAME + " WHERE "
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_SUBJECT_COMPONENT_TEMPLATE_ID + " = "
					+ subjectComponentId + " and "
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_SUBJECT_TEMPLATE_ID + " = " + subjectId;

			java.sql.Statement st = con.createStatement();
			st.execute(generator.getUseDatabaseQuery());

			st.execute(deleteStatement);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<SubjectTerm> GetAllSubjectTerms() {
		List<SubjectTerm> result = null;
		try {
			java.sql.Connection con = getConnection();
			String selectAllQuery = generator.getSelectAllQuery(DbCertificate.SubjectTermTable.TABLE_NAME);

			java.sql.Statement st = con.createStatement();
			st.execute(generator.getUseDatabaseQuery());

			ResultSet rs = st.executeQuery(selectAllQuery);

			result = getSubjectTermsList(rs);

			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private List<SubjectTerm> getSubjectTermsList(ResultSet rs) throws SQLException {
		List<SubjectTerm> result = new ArrayList<SubjectTerm>();
		
		while (rs.next()) {
			int id = rs.getInt(DbCertificate.SubjectTermTable.COLUMN_NAME_ID);
			String name = rs.getString(DbCertificate.SubjectTermTable.COLUMN_NAME_NAME);
			
			SubjectTerm temp = new SubjectTerm(name, id);
			result.add(temp);
		}

		return result;
	}

	@Override
	public Subject getSubjectByFilter(String subjectName, int year, int termId) {
		Subject subject = null;
		try {
			java.sql.Connection con = getConnection();
			String selectQuery = "SELECT * FROM " + DbCertificate.SubjectTable.TABLE_NAME + " WHERE "
					+ DbCertificate.SubjectTable.COLUMN_NAME_NAME + " = \"" + subjectName + "\""
					+ " AND " + DbCertificate.SubjectTable.COLUMN_NAME_YEAR + " = " + year
					+ " AND " + DbCertificate.SubjectTable.COLUMN_NAME_TERM_ID + " = " + termId;

			
			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());

			ResultSet rs = st.executeQuery();

			List<Subject> result = getSubjectsList(rs);
			
			if (!result.isEmpty()) {
				subject = result.get(0);
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subject;
	}

	@Override
	public void addUserSubject(UserSubject us) {
		try {
			java.sql.Connection con = getConnection();
			String insertQuery = generator.getInsertQuery(userSubjectColumnNames, DbCertificate.UserSubjectTable.TABLE_NAME);
			con.createStatement().executeQuery(generator.getUseDatabaseQuery());
			java.sql.PreparedStatement st = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

			setValues(getUserSubjectValues(us), st);

			st.executeUpdate();
			try (ResultSet generatedKeys = st.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					us.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException();
				}
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<String> getUserSubjectValues(UserSubject us) {
		return Arrays.asList(Integer.toString(us.getUserId()), Integer.toString(us.getSubjectId()));
	}

	@Override
	public boolean CheckIfExistsSubjectComponentTemplate(SubjectComponentTemplates sct) {
		boolean result = false;
		try {
			java.sql.Connection con = getConnection();
			String selectQuery = "SELECT * FROM " + DbCertificate.SubjectComponentTemplateTable.TABLE_NAME + 
					" WHERE "
					+ DbCertificate.SubjectComponentTemplateTable.COLUMN_NAME_NAME + " = " + "?"
					+ " AND " + DbCertificate.SubjectComponentTemplateTable.COLUMN_NAME_NUMBER + " = " + "?"
					+ " AND " + DbCertificate.SubjectComponentTemplateTable.COLUMN_NAME_MARKPERCENTAGE + " = " + "?";

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			setValues(Arrays.asList(sct.getName(),Integer.toString(sct.getNumber()),Double.toString(sct.getMarkPercentage())), st);
			
			st.executeQuery(generator.getUseDatabaseQuery());
			ResultSet rs = st.executeQuery();

			result = rs.next();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
