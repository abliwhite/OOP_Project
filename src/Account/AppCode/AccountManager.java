package Account.AppCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import Account.Models.AuthModel;
import Account.Models.RegisterModel;
import Account.Models.User;
import Account.Models.UserProfile;
import Common.AppCode.CommonConstants;
import Common.AppCode.DaoController;
import Common.Models.ResponseModel;
import Database.DbCertificate;
import Database.MyDBInfo;
import Database.QueryGenerator;
import Subject.AppCode.SubjectManager;
import Subject.Models.DbModels.Subject;

public class AccountManager extends DaoController implements AccountManagerInterface {

	public static final String ACCOUNT_MANAGER_ATTRIBUTE = "Account Manager Attribute";

	private List<String> userColumnNames;
	private List<String> profileColumnNames;

	public AccountManager(DataSource pool) {
		super(pool);
		userColumnNames = getColumnsNames(DbCertificate.UserTable.TABLE_NAME);
		profileColumnNames = getColumnsNames(DbCertificate.ProfileTable.TABLE_NAME);
	}
	
	public UserProfile getProfile(User user) {
		UserProfile profile = null;
		try {
			java.sql.Connection con = getConnection();

			String selectQuery = "SELECT * FROM " + DbCertificate.ProfileTable.TABLE_NAME 
					+ " WHERE "
					+ DbCertificate.ProfileTable.COLUMN_NAME_ID + " = " + user.getProfileID();

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());

			ResultSet rs = st.executeQuery();

			profile = getProfileFromResultSet(rs);
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profile;
	}

	private UserProfile getProfileFromResultSet(ResultSet rs) {
		try {
			while (rs.next()) {
				int id = rs.getInt(DbCertificate.ProfileTable.COLUMN_NAME_ID);
				String name = rs.getString(DbCertificate.ProfileTable.COLUMN_NAME_NAME);
				String gender = rs.getString(DbCertificate.ProfileTable.COLUMN_NAME_GENDER);
				String date = rs.getString(DbCertificate.ProfileTable.COLUMN_NAME_CREATE_DATE);
				String surname = rs.getString(DbCertificate.ProfileTable.COLUMN_NAME_SURNAME);
				return new UserProfile(id, name, gender, date, surname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addProfile(UserProfile profile) {
		try {
			java.sql.Connection con = getConnection();

			String insertQuery = generator.getInsertQuery(profileColumnNames, DbCertificate.ProfileTable.TABLE_NAME);
			java.sql.PreparedStatement st = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			
			setValues(getProfileValues(profile), st);
			st.executeUpdate();

			try (ResultSet generatedKeys = st.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					profile.setId(generatedKeys.getInt(1));
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

	public void addUser(User user) {
		try {
			java.sql.Connection con = getConnection();

			String insertQuery = generator.getInsertQuery(userColumnNames, DbCertificate.UserTable.TABLE_NAME);
			java.sql.PreparedStatement st = con.prepareStatement(insertQuery);

			setValues(getUserModelValues(user), st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User checkLoginValidation(AuthModel auth) {
		User user = null;
		try {
			java.sql.Connection con = getConnection();

			String selectQuery = "SELECT * FROM " + DbCertificate.UserTable.TABLE_NAME 
					+ " WHERE "
					+ DbCertificate.UserTable.COLUMN_NAME_USERNAME + " = ?"+
					" AND "
					+ DbCertificate.UserTable.COLUMN_NAME_PASSWORD + " = ?";

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(auth.getUsername(), auth.getPassword()), st);

			ResultSet rs = st.executeQuery();

			user = getUser(rs);
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	private User getUser(ResultSet rs) {
		try {
			while (rs.next()) {
				int id = rs.getInt(DbCertificate.UserTable.COLUMN_NAME_ID);
				String username = rs.getString(DbCertificate.UserTable.COLUMN_NAME_USERNAME);
				String password = rs.getString(DbCertificate.UserTable.COLUMN_NAME_PASSWORD);
				String email = rs.getString(DbCertificate.UserTable.COLUMN_NAME_EMAIL);
				String role = rs.getString(DbCertificate.UserTable.COLUMN_NAME_ROLE);
				String gmailID = rs.getString(DbCertificate.UserTable.COLUMN_NAME_GMAIL_ID);
				String facebookID = rs.getString(DbCertificate.UserTable.COLUMN_NAME_FACEBOOK_ID);
				int profileID = rs.getInt(DbCertificate.UserTable.COLUMN_NAME_PROFILE_ID);
				return new User(id, username, password, email, role, gmailID, facebookID, profileID, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public ResponseModel checkRegistrationValidity(RegisterModel register) {
		ResponseModel queryResult = null;
		try {
			java.sql.Connection con = getConnection();

			String selectQuery = "SELECT * FROM " + DbCertificate.UserTable.TABLE_NAME 
					+ " WHERE "
					+ DbCertificate.UserTable.COLUMN_NAME_USERNAME + " = ?"+
					" or "
					+ DbCertificate.UserTable.COLUMN_NAME_EMAIL + " = ?";

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(register.getUsername(), register.getEmail()), st);

			ResultSet rs = st.executeQuery();

			con.close();

			if (rs.next()) {
				queryResult = new ResponseModel(CommonConstants.UNSUCCESSFUL_REGISTRATION, false);
			} else {
				queryResult = new ResponseModel(CommonConstants.SUCCESSFUL_MESSAGE, true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return queryResult;
	}

	public void updateUser(User user) {
		try {
			java.sql.Connection con = getConnection();
			String updateStatement = generator.getUpdateByIdQuery(userColumnNames, DbCertificate.UserTable.TABLE_NAME,
					user.getId());

			java.sql.PreparedStatement st = con.prepareStatement(updateStatement);
			st.executeQuery();
			
			setValues(getUserModelValues(user), st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateProfile(UserProfile profile) {
		try {
			java.sql.Connection con = getConnection();
			String updateStatement = generator.getUpdateByIdQuery(userColumnNames,
					DbCertificate.ProfileTable.TABLE_NAME, profile.getId());

			java.sql.PreparedStatement st = con.prepareStatement(updateStatement);

			setValues(getProfileValues(profile), st);
			st.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<String> getUserModelValues(User user) {
		return Arrays.asList(user.getUsername(), user.getPassword(), user.getEmail(), user.getRole(), user.getGmailID(),
				user.getFacebookID(), user.getProfileID().toString());
	}

	private List<String> getProfileValues(UserProfile profile) {
		return Arrays.asList(profile.getName(), profile.getSurname(), profile.getGender(), profile.getCreateDate());
	}

	@Override
	public List<Subject> getUserSubjects(User user) {
		List<Subject> subjects = null;
		
		try {
			java.sql.Connection con = getConnection();
			String selectQuery = "SELECT * FROM " + DbCertificate.UserSubjectTable.TABLE_NAME 
					+ " WHERE "
					+ DbCertificate.UserSubjectTable.COLUMN_NAME_USER_ID + " = " + user.getId();
					
			
			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());
			
			ResultSet rs = st.executeQuery();
			subjects = getSubjects(rs);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return subjects;
	}

	private List<Subject> getSubjects(ResultSet rs) {
		List<Subject> result = new ArrayList<>();
		
		try {
			while (rs.next()) {
				int subjectId = rs.getInt(DbCertificate.UserSubjectTable.COLUMN_NAME_SUBJECT_ID);
				result.add(getSubjectById(subjectId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

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
			
			Subject temp = new Subject(id, name, termId, year, subjectInfoID);
			result.add(temp);
		}

		return result;
	}

	
}
