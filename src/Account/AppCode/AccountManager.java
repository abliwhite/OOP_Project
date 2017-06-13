package Account.AppCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

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
			PreparedStatement st = (PreparedStatement) con.createStatement();
			ResultSet rs = st.executeQuery(generator.columnNameHackQuery());

			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();

			String insertQuery = generator.getInsertQuery(getColumnNames(meta), getProfileInsertValues(profile),
					DbCertificate.PROFILE_TABLE_NAME);

			st.executeQuery(insertQuery);

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
			ResultSet rs = st.executeQuery(generator.columnNameHackQuery());

			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();

			String insertQuery = generator.getInsertQuery(getColumnNames(meta), getUserInsertValues(user),
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

	private String getUserInsertValues(User user) {
		return "(" + user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "," + user.getRole() + ","
				+ user.getGmailID() + "," + user.getFacebookID() + "," + user.getProfileID() + ");";
	}

	private String getProfileInsertValues(UserProfile profile) {
		return "(" + profile.getName() + "," + profile.getSurname() + "," + profile.getGender() + ","
				+ profile.getCreateDate() + ");";
	}

	private List<String> getColumnNames(ResultSetMetaData meta) throws SQLException {
		List<String> columnNames = new ArrayList<String>();

		for (int i = 1; i <= meta.getColumnCount(); i++) {
			columnNames.add(meta.getColumnName(i));
		}
		return columnNames;
	}

}
