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

import Account.Models.AuthModel;
import Account.Models.RegisterModel;
import Account.Models.User;
import Account.Models.UserProfile;
import Common.AppCode.CommonConstants;
import Common.AppCode.DaoController;
import Common.Models.ResponseMessage;
import Database.DbCertificate;
import Database.MyDBInfo;
import Database.QueryGenerator;

public class AccountManager extends DaoController {

	public static final String ACCOUNT_MANAGER_ATTRIBUTE = "Account Manager Attribure";

	public AccountManager(DataSource pool) {
		super(pool);
	}

	public ResponseMessage addProfile(UserProfile profile) {
		ResponseMessage response = null;

		try {
			java.sql.Connection con = getConnection();
			String hackQuery = generator.columnNameHackQuery(DbCertificate.PROFILE_TABLE_NAME);
			java.sql.PreparedStatement st = con.prepareStatement(hackQuery);

			st.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

			ResultSet rs = st.executeQuery(hackQuery);

			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();

			String insertQuery = generator.getInsertQuery(getColumnNames(meta), getProfileValues(profile),
					DbCertificate.PROFILE_TABLE_NAME);

			st.executeUpdate(insertQuery);

			response = new ResponseMessage(CommonConstants.SUCCESSFULL_MESSAGE, true);

			con.close();
		} catch (SQLException e) {
			response = new ResponseMessage(e.getMessage(), false);
			e.printStackTrace();
		}
		return response;
	}

	public ResponseMessage addUser(User user) {
		ResponseMessage response = null;

		try {
			java.sql.Connection con = getConnection();
			PreparedStatement st = (PreparedStatement) con.createStatement();
			String hackQuery = generator.columnNameHackQuery(DbCertificate.USER_TABLE_NAME);
			ResultSet rs = st.executeQuery(hackQuery);

			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();

			String insertQuery = generator.getInsertQuery(getColumnNames(meta), getUserModelValues(user),
					DbCertificate.USER_TABLE_NAME);

			st.executeQuery(insertQuery);

			response = new ResponseMessage(CommonConstants.SUCCESSFULL_MESSAGE, true);

			con.close();
		} catch (SQLException e) {
			response = new ResponseMessage(e.getMessage(), false);
			e.printStackTrace();
		}

		return response;

	}

	public UserProfile getProfileByUsernameAndPassword() {
		return null;
	}

	public User checkLoginValidation(AuthModel auth) {
		return null;
	}

	public ResponseMessage checkRegistrationValidation(RegisterModel register) {
		return new ResponseMessage(CommonConstants.SUCCESSFULL_MESSAGE, true);
	}

	public ResponseMessage updateUser(User user) {
		return null;
	}

	public ResponseMessage updateProfile(UserProfile profile) {

		return null;
	}

	private List<String> getUserModelValues(User user) {
		List<String> result = Arrays.asList(user.getUsername(), user.getPassword(),
				user.getEmail(), user.getRole(), user.getGmailID(), user.getFacebookID(),
				user.getProfileID().toString());

		return result;
	}

	private List<String> getProfileValues(UserProfile profile) {
		List<String> result = Arrays.asList(profile.getName(), profile.getSurname(),
				profile.getGender(), profile.getCreateDate());
		
		return result;
	}

	private List<String> getColumnNames(ResultSetMetaData meta) throws SQLException {
		List<String> columnNames = new ArrayList<String>();

		for (int i = 1; i <= meta.getColumnCount(); i++) {
			columnNames.add(meta.getColumnName(i));
		}
		return columnNames;
	}

}
