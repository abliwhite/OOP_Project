package Subject.AppCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.mysql.jdbc.Statement;

import Common.AppCode.CommonConstants;
import Common.AppCode.DaoController;
import Database.DbCertificate;
import Database.MyDBInfo;
import Subject.Models.DbModels.CommonSubjectComponent;
import Subject.Models.DbModels.Subject;
import Subject.Models.DbModels.SubjectComponentMaterial;
import Subject.Models.DbModels.SubjectComponentType;
import Subject.Models.DbModels.SubjectInfo;
import Subject.Models.DbModels.SubjectTerm;
import Subject.Models.DbModels.UserSubject;
import Subject.Models.ViewModels.CommonSubjectComponentViewModel;
import Subject.Models.ViewModels.SubjectViewModel;

public class SubjectManager extends DaoController implements SubjectManagerInterface {

	public static final String SUBJECT_MANAGER_ATTRIBUTE = "Subject Manager Attribute";

	private List<String> subjectInfoColumnNames;
	private List<String> subjectColumnNames;
	private List<String> subjectComponentTypeColumnNames;
	private List<String> commonSubjectTemplateColumnNames;
	private List<String> userSubjectColumnNames;

	public SubjectManager(DataSource pool) {
		super(pool);
		subjectInfoColumnNames = getColumnsNames(DbCertificate.SubjectInfoTable.TABLE_NAME);
		subjectColumnNames = getColumnsNames(DbCertificate.SubjectTable.TABLE_NAME);
		subjectComponentTypeColumnNames = getColumnsNames(DbCertificate.SubjectComponentTypeTable.TABLE_NAME);
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
	public void AddSubjectComponentType(SubjectComponentType sct) {
		try {
			java.sql.Connection con = getConnection();
			String insertQuery = generator.getInsertQuery(subjectComponentTypeColumnNames,
					DbCertificate.SubjectComponentTypeTable.TABLE_NAME);

			con.createStatement().executeQuery(generator.getUseDatabaseQuery());
			java.sql.PreparedStatement st = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

			setValues(getSubjectComponentTypeValues(sct), st);
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

	private List<String> getSubjectComponentTypeValues(SubjectComponentType sct) {
		return Arrays.asList(sct.getName());
	}

	private List<String> getSubjectValues(Subject subject) {
		return Arrays.asList(subject.getName(), Integer.toString(subject.getTermId()),
				Integer.toString(subject.getYear()), Integer.toString(subject.getSubjectInfoID()));
	}

	@Override
	public List<SubjectComponentType> getAllSubjectComponentTypes() {
		List<SubjectComponentType> result = new ArrayList<SubjectComponentType>();

		try {
			java.sql.Connection con = getConnection();
			java.sql.Statement st = con.createStatement();

			String selectAllQuery = generator.getSelectAllQuery(DbCertificate.SubjectComponentTypeTable.TABLE_NAME);

			ResultSet rs = st.executeQuery(selectAllQuery);

			result = getSubjectComponentTypeList(rs);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private List<SubjectComponentType> getSubjectComponentTypeList(ResultSet rs) throws SQLException {
		List<SubjectComponentType> result = new ArrayList<SubjectComponentType>();

		while (rs.next()) {
			int i = rs.getInt(DbCertificate.SubjectComponentTypeTable.COLUMN_NAME_ID);
			String n = rs.getString(DbCertificate.SubjectComponentTypeTable.COLUMN_NAME_NAME);

			SubjectComponentType temp = new SubjectComponentType(i, n);
			result.add(temp);
		}

		return result;
	}

	// todo gasatestia
	@Override
	public List<CommonSubjectComponentViewModel> getAllCommonSubjectComponentsViewModelBySubjectID(int subjectId) {
		List<CommonSubjectComponentViewModel> result = new ArrayList<CommonSubjectComponentViewModel>();

		try {
			java.sql.Connection con = getConnection();

			String selectQuery = "SELECT " + DbCertificate.CommonSubjectComponentTable.TABLE_NAME + "."
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_ID + ","
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_TYPE_ID + ","
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_MARKPERCENTAGE + ","
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_NUMBER + ","
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_SUBJECT_ID + ","
					+ DbCertificate.SubjectComponentTypeTable.COLUMN_NAME_NAME + " FROM "
					+ DbCertificate.CommonSubjectComponentTable.TABLE_NAME + " INNER JOIN "
					+ DbCertificate.SubjectComponentTypeTable.TABLE_NAME + " ON "
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_TYPE_ID + " = "
					+ DbCertificate.SubjectComponentTypeTable.UNIQUE_COLUMN_NAME_ID + " WHERE "
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_SUBJECT_ID + " = ?";

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			con.createStatement().executeQuery(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(String.valueOf(subjectId)), st);
			ResultSet rs = st.executeQuery();

			result = getCommonSubjectComponentViewModelList(rs);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	private List<CommonSubjectComponentViewModel> getCommonSubjectComponentViewModelList(ResultSet rs)
			throws SQLException {
		List<CommonSubjectComponentViewModel> result = new ArrayList<CommonSubjectComponentViewModel>();

		while (rs.next()) {
			int id = rs.getInt(DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_ID);
			int typeId = rs.getInt(DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_TYPE_ID);
			String typeName = rs.getString(DbCertificate.SubjectComponentTypeTable.COLUMN_NAME_NAME);
			double markPercentage = rs.getDouble(DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_MARKPERCENTAGE);
			int number = rs.getInt(DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_NUMBER);
			int subjectId = rs.getInt(DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_SUBJECT_ID);

			CommonSubjectComponentViewModel temp = new CommonSubjectComponentViewModel(id, subjectId, markPercentage,
					number, new SubjectComponentType(typeId, typeName));
			result.add(temp);
		}

		return result;
	}

	@Override
	public SubjectViewModel getSubjectViewModelById(int subjectId) {
		SubjectViewModel result = null;
		try {
			java.sql.Connection con = getConnection();

			String selectQuery = "SELECT " + DbCertificate.SubjectTable.UNIQUE_COLUMN_NAME_ID + ","
					+ DbCertificate.SubjectTable.UNIQUE_NAME_NAME + "," + DbCertificate.SubjectTable.COLUMN_NAME_YEAR
					+ "," + DbCertificate.SubjectInfoTable.COLUMN_NAME_ECTS + ","
					+ DbCertificate.SubjectInfoTable.COLUMN_NAME_LANGUAGE + ","
					+ DbCertificate.SubjectInfoTable.COLUMN_NAME_LECTURER_NAME + ","
					+ DbCertificate.SubjectInfoTable.UNIQUE_COLUMN_NAME_ID + " as "
					+ DbCertificate.SubjectInfoTable.ALTERNATIVE_COLUMN_NAME_ID + ","
					+ DbCertificate.SubjectTermTable.UNIQUE_COLUMN_NAME_ID + " as "
					+ DbCertificate.SubjectTermTable.ALTERNATIVE_COLUMN_NAME_ID + ","
					+ DbCertificate.SubjectTermTable.UNIQUE_COLUMN_NAME_NAME + " as "
					+ DbCertificate.SubjectTermTable.ALTERNATIVE_COLUMN_NAME_NAME + " FROM "
					+ DbCertificate.SubjectTable.TABLE_NAME + " INNER JOIN " + DbCertificate.SubjectInfoTable.TABLE_NAME
					+ " ON " + DbCertificate.SubjectTable.COLUMN_NAME_SUBJECT_INFO_ID + " = "
					+ DbCertificate.SubjectInfoTable.UNIQUE_COLUMN_NAME_ID + " INNER JOIN "
					+ DbCertificate.SubjectTermTable.TABLE_NAME + " ON "
					+ DbCertificate.SubjectTermTable.UNIQUE_COLUMN_NAME_ID + " = "
					+ DbCertificate.SubjectTable.COLUMN_NAME_TERM_ID + " WHERE "
					+ DbCertificate.SubjectTable.UNIQUE_COLUMN_NAME_ID + " = ?";

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(String.valueOf(subjectId)), st);
			ResultSet rs = st.executeQuery();
			List<SubjectViewModel> temp = getSubjectViewModelList(rs);

			result = temp.size() == 0 ? result : temp.get(0);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	private List<SubjectViewModel> getSubjectViewModelList(ResultSet rs) throws SQLException {
		List<SubjectViewModel> result = new ArrayList<SubjectViewModel>();

		while (rs.next()) {
			int id = rs.getInt(DbCertificate.SubjectTable.COLUMN_NAME_ID);
			String name = rs.getString(DbCertificate.SubjectTable.COLUMN_NAME_NAME);
			int year = rs.getInt(DbCertificate.SubjectTable.COLUMN_NAME_YEAR);

			int termId = rs.getInt(DbCertificate.SubjectTermTable.ALTERNATIVE_COLUMN_NAME_ID);
			String termName = rs.getString(DbCertificate.SubjectTermTable.ALTERNATIVE_COLUMN_NAME_NAME);

			int subjectInfoID = rs.getInt(DbCertificate.SubjectInfoTable.ALTERNATIVE_COLUMN_NAME_ID);
			int ects = rs.getInt(DbCertificate.SubjectInfoTable.COLUMN_NAME_ECTS);
			String lecturerName = rs.getString(DbCertificate.SubjectInfoTable.COLUMN_NAME_LECTURER_NAME);
			String language = rs.getString(DbCertificate.SubjectInfoTable.COLUMN_NAME_LANGUAGE);

			Subject subject = new Subject(id, name, termId, year, subjectInfoID);
			SubjectInfo subjectInfo = new SubjectInfo(subjectInfoID, lecturerName, null, ects, language);
			SubjectTerm subjectTerm = new SubjectTerm(termName, termId);

			result.add(new SubjectViewModel(subject, subjectTerm, subjectInfo));
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
	public void AddCommonSubjectComponent(CommonSubjectComponent csc) {
		try {
			java.sql.Connection con = getConnection();
			String insertQuery = generator.getInsertQuery(commonSubjectTemplateColumnNames,
					DbCertificate.CommonSubjectComponentTable.TABLE_NAME);
			java.sql.PreparedStatement st = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

			setValues(getCommonSubjectComponentValues(csc), st);

			st.executeUpdate();
			try (ResultSet generatedKeys = st.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					csc.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException();
				}
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<String> getCommonSubjectComponentValues(CommonSubjectComponent csc) {
		return Arrays.asList(String.valueOf(csc.getTypeId()), String.valueOf(csc.getMarkPercentage()),
				String.valueOf(csc.getNumber()), String.valueOf(csc.getSubjectId()));
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
	public void DeleteCommonSubjectComponentByID(int id) {
		try {
			java.sql.Connection con = getConnection();
			String deleteStatement = generator.getDeleteByIdQuery(DbCertificate.CommonSubjectComponentTable.TABLE_NAME);

			java.sql.PreparedStatement st = con.prepareStatement(deleteStatement);
			st.execute(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(String.valueOf(id)), st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void UpdateCommonSubjectComponent(CommonSubjectComponent csc) {
		try {
			java.sql.Connection con = getConnection();
			String updateStatement = generator.getUpdateByIdQuery(commonSubjectTemplateColumnNames,
					DbCertificate.CommonSubjectComponentTable.TABLE_NAME, csc.getId());

			java.sql.PreparedStatement st = con.prepareStatement(updateStatement);

			setValues(Arrays.asList(String.valueOf(csc.getTypeId()), String.valueOf(csc.getMarkPercentage()),
					String.valueOf(csc.getNumber()), String.valueOf(csc.getSubjectId())), st);
			st.executeUpdate();

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
					+ DbCertificate.SubjectTable.COLUMN_NAME_NAME + " = \"" + subjectName + "\"" + " AND "
					+ DbCertificate.SubjectTable.COLUMN_NAME_YEAR + " = " + year + " AND "
					+ DbCertificate.SubjectTable.COLUMN_NAME_TERM_ID + " = " + termId;

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
			String insertQuery = generator.getInsertQuery(userSubjectColumnNames,
					DbCertificate.UserSubjectTable.TABLE_NAME);
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
	public void UpdateSubjectInfo(SubjectInfo subjectInfo) {
		try {
			java.sql.Connection con = getConnection();
			String updateStatement = generator.getUpdateByIdQuery(subjectInfoColumnNames,
					DbCertificate.SubjectInfoTable.TABLE_NAME, subjectInfo.getId());

			java.sql.PreparedStatement st = con.prepareStatement(updateStatement);

			setValues(getSubjectInfoValues(subjectInfo), st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Subject> getAllSubjects() {
		List<Subject> subjects = null;

		try {
			java.sql.Connection con = getConnection();
			String selectQuery = generator.getSelectAllQuery(DbCertificate.SubjectTable.TABLE_NAME);

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());

			ResultSet rs = st.executeQuery();
			subjects = getSubjectsList(rs);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subjects;
	}

	@Override
	public List<SubjectViewModel> getAllSubjectViewModels() {
		List<SubjectViewModel> result = null;
		try {
			java.sql.Connection con = getConnection();

			String selectQuery = "SELECT " + DbCertificate.SubjectTable.UNIQUE_COLUMN_NAME_ID + ","
					+ DbCertificate.SubjectTable.UNIQUE_NAME_NAME + "," + DbCertificate.SubjectTable.COLUMN_NAME_YEAR
					+ "," + DbCertificate.SubjectInfoTable.COLUMN_NAME_ECTS + ","
					+ DbCertificate.SubjectInfoTable.COLUMN_NAME_LANGUAGE + ","
					+ DbCertificate.SubjectInfoTable.COLUMN_NAME_LECTURER_NAME + ","
					+ DbCertificate.SubjectInfoTable.UNIQUE_COLUMN_NAME_ID + " as "
					+ DbCertificate.SubjectInfoTable.ALTERNATIVE_COLUMN_NAME_ID + ","
					+ DbCertificate.SubjectTermTable.UNIQUE_COLUMN_NAME_ID + " as "
					+ DbCertificate.SubjectTermTable.ALTERNATIVE_COLUMN_NAME_ID + ","
					+ DbCertificate.SubjectTermTable.UNIQUE_COLUMN_NAME_NAME + " as "
					+ DbCertificate.SubjectTermTable.ALTERNATIVE_COLUMN_NAME_NAME + " FROM "
					+ DbCertificate.SubjectTable.TABLE_NAME + " INNER JOIN " + DbCertificate.SubjectInfoTable.TABLE_NAME
					+ " ON " + DbCertificate.SubjectTable.COLUMN_NAME_SUBJECT_INFO_ID + " = "
					+ DbCertificate.SubjectInfoTable.UNIQUE_COLUMN_NAME_ID + " INNER JOIN "
					+ DbCertificate.SubjectTermTable.TABLE_NAME + " ON "
					+ DbCertificate.SubjectTermTable.UNIQUE_COLUMN_NAME_ID + " = "
					+ DbCertificate.SubjectTable.COLUMN_NAME_TERM_ID;

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());

			ResultSet rs = st.executeQuery();
			result = getSubjectViewModelList(rs);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void deleteSubjectById(int id) {
		try {
			java.sql.Connection con = getConnection();
			String deleteStatement = generator.getDeleteByIdQuery(DbCertificate.SubjectTable.TABLE_NAME);

			java.sql.PreparedStatement st = con.prepareStatement(deleteStatement);
			st.execute(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(String.valueOf(id)), st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCommonSubjectComponentBySubjectId(int subjectId) {
		try {
			java.sql.Connection con = getConnection();
			String deleteStatement = generator.getDeleteByAnyIDQuery(
					DbCertificate.CommonSubjectComponentTable.TABLE_NAME,
					DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_SUBJECT_ID);

			java.sql.PreparedStatement st = con.prepareStatement(deleteStatement);
			st.execute(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(String.valueOf(subjectId)), st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCommonSubjectComponentMaterialsByCscIdList(List<Integer> cscIds) {
		try {
			java.sql.Connection con = getConnection();
			String deleteStatement = generator.getDeleteByIdListQuery(
					DbCertificate.SubjectComponentMaterialTable.TABLE_NAME,
					DbCertificate.SubjectComponentMaterialTable.COLUMN_NAME_SUBJECT_COMPONENT_ID, cscIds.size());

			java.sql.PreparedStatement st = con.prepareStatement(deleteStatement);
			st.execute(generator.getUseDatabaseQuery());

			List<String> values = cscIds.stream().map(x -> String.valueOf(x.intValue())).collect(Collectors.toList());
			setValues(values, st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUserSubjectComponentsByCscIdList(List<Integer> cscIds) {
		try {
			java.sql.Connection con = getConnection();
			String deleteStatement = generator.getDeleteByIdListQuery(
					DbCertificate.UserSubjectComponentTable.TABLE_NAME,
					DbCertificate.UserSubjectComponentTable.COLUMN_NAME_COMMON_SUBJECT_COMPONENT_ID, cscIds.size());

			java.sql.PreparedStatement st = con.prepareStatement(deleteStatement);
			st.execute(generator.getUseDatabaseQuery());

			List<String> values = cscIds.stream().map(x -> String.valueOf(x.intValue())).collect(Collectors.toList());
			setValues(values, st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUserSubjectBySubjectId(int id) {
		try {
			java.sql.Connection con = getConnection();
			String deleteStatement = generator.getDeleteByAnyIDQuery(DbCertificate.UserSubjectTable.TABLE_NAME,
					DbCertificate.UserSubjectTable.COLUMN_NAME_SUBJECT_ID);

			java.sql.PreparedStatement st = con.prepareStatement(deleteStatement);
			st.execute(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(String.valueOf(id)), st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public CommonSubjectComponentViewModel getCommonSubjectComponentViewmodelById(int componentId) {
		CommonSubjectComponentViewModel result = null;
		try {
			java.sql.Connection con = getConnection();
			String selectStatement = "SELECT " + DbCertificate.SubjectTable.UNIQUE_COLUMN_NAME_ID + ","
					+ DbCertificate.SubjectTable.UNIQUE_NAME_NAME + "," + DbCertificate.SubjectTable.COLUMN_NAME_YEAR
					+ "," + DbCertificate.SubjectTable.COLUMN_NAME_SUBJECT_INFO_ID + ","
					+ DbCertificate.SubjectTable.COLUMN_NAME_TERM_ID + ","
					+ DbCertificate.CommonSubjectComponentTable.UNIQUE_COLUMN_NAME_ID + ","
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_MARKPERCENTAGE + ","
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_NUMBER + ","
					+ DbCertificate.SubjectComponentTypeTable.UNIQUE_COLUMN_NAME_ID + ","
					+ DbCertificate.SubjectComponentTypeTable.UNIQUE_COLUMN_NAME_NAME + " FROM "
					+ DbCertificate.SubjectTable.TABLE_NAME + " INNER JOIN "
					+ DbCertificate.CommonSubjectComponentTable.TABLE_NAME + " ON "
					+ DbCertificate.SubjectTable.UNIQUE_COLUMN_NAME_ID + " = "
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_SUBJECT_ID + " INNER JOIN "
					+ DbCertificate.SubjectComponentTypeTable.TABLE_NAME + " ON "
					+ DbCertificate.SubjectComponentTypeTable.UNIQUE_COLUMN_NAME_ID + " = "
					+ DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_TYPE_ID + " WHERE "
					+ DbCertificate.CommonSubjectComponentTable.UNIQUE_COLUMN_NAME_ID + " = " + componentId;
			java.sql.PreparedStatement st = con.prepareStatement(selectStatement);
			st.executeQuery(generator.getUseDatabaseQuery());
			ResultSet rs = st.executeQuery(selectStatement);
			result = getCommonSubjectComponentViewModel(rs);

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private CommonSubjectComponentViewModel getCommonSubjectComponentViewModel(ResultSet rs) throws SQLException {
		CommonSubjectComponentViewModel result=null;
		while(rs.next()){
		int subjectId = rs.getInt(DbCertificate.SubjectTable.COLUMN_NAME_ID);
		String subjectName = rs.getString(DbCertificate.SubjectTable.COLUMN_NAME_NAME);
		int subjectYear = rs.getInt(DbCertificate.SubjectTable.COLUMN_NAME_YEAR);
		int termId = rs.getInt(DbCertificate.SubjectTable.COLUMN_NAME_TERM_ID);
		int subjectInfoID = rs.getInt(DbCertificate.SubjectInfoTable.ALTERNATIVE_COLUMN_NAME_ID);

		int id=rs.getInt(DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_ID);
		double markPercentage=rs.getDouble(DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_MARKPERCENTAGE);
		int number=rs.getInt(DbCertificate.CommonSubjectComponentTable.COLUMN_NAME_NUMBER);
		
		int typeId=rs.getInt(DbCertificate.SubjectComponentTypeTable.COLUMN_NAME_ID);
		String typeName=rs.getString(DbCertificate.SubjectComponentTypeTable.COLUMN_NAME_NAME);
		
		SubjectComponentType subjectComponentType= new SubjectComponentType(typeId,typeName);
		Subject subject = new Subject(subjectId, subjectName, termId, subjectYear, subjectInfoID);
		result = new CommonSubjectComponentViewModel(id,subjectId,markPercentage,number,subjectComponentType,subject);
		}
		return result;
	}
	public List<SubjectComponentMaterial> getSubjectComponentMaterialsByComponentId(int componentId){
		List<SubjectComponentMaterial> result = new ArrayList<SubjectComponentMaterial>();
		try {
			java.sql.Connection con = getConnection();
		
		String selectStatement=generator.getSelectAllQuery(DbCertificate.SubjectComponentMaterialTable.TABLE_NAME)+" WHERE "+DbCertificate.SubjectComponentMaterialTable.COLUMN_NAME_SUBJECT_COMPONENT_ID + " = "+componentId;
		java.sql.PreparedStatement st = con.prepareStatement(selectStatement);
		st.executeQuery(generator.getUseDatabaseQuery());
		ResultSet rs = st.executeQuery(selectStatement);
		result = getMaterialsList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	private List<SubjectComponentMaterial> getMaterialsList(ResultSet rs) throws SQLException {
		List<SubjectComponentMaterial> result=new ArrayList<SubjectComponentMaterial>();
		while(rs.next()){
		 int id=rs.getInt(DbCertificate.SubjectComponentMaterialTable.COLUMN_NAME_ID);
		 String path=rs.getString(DbCertificate.SubjectComponentMaterialTable.COLUMN_NAME_MATERIAL_PATH);
		 String componentId=rs.getString(DbCertificate.SubjectComponentMaterialTable.COLUMN_NAME_SUBJECT_COMPONENT_ID);
		 String uploadDate=rs.getString(DbCertificate.SubjectComponentMaterialTable.COLUMN_NAME_UPLOAD_DATE);
		 
		 SubjectComponentMaterial temp = new SubjectComponentMaterial(id,path,uploadDate,componentId);
		 result.add(new SubjectComponentMaterial(id,path,uploadDate,componentId));
		}
		return result;
	}
}
