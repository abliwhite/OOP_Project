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
import Common.Models.ResponseMessage;
import Database.DbCertificate;
import Database.MyDBInfo;
import Database.QueryGenerator;

public class AccountManager extends DaoController {

	public static final String ACCOUNT_MANAGER_ATTRIBUTE = "Account Manager Attribure";

	public AccountManager(DataSource pool) {
		super(pool);
	}

	public void addProfile(UserProfile profile) throws SQLException {
		java.sql.Connection con = getConnection();
		ResultSetMetaData meta = getTableMetaData(DbCertificate.PROFILE_TABLE_NAME, con);

		String insertQuery = generator.getInsertQuery(getColumnNames(meta), DbCertificate.PROFILE_TABLE_NAME);
		java.sql.PreparedStatement st = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

		setInsertValues(getProfileValues(profile), st);
		st.executeUpdate();

		try (ResultSet generatedKeys = st.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				profile.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException();
			}
		}

		con.close();
	}

	public void addUser(User user) throws SQLException {
		java.sql.Connection con = getConnection();
		ResultSetMetaData meta = getTableMetaData(DbCertificate.USER_TABLE_NAME, con);

		String insertQuery = generator.getInsertQuery(getColumnNames(meta), DbCertificate.USER_TABLE_NAME);
		java.sql.PreparedStatement st = con.prepareStatement(insertQuery);

		setInsertValues(getUserModelValues(user), st);
		st.executeUpdate();

		con.close();
	}

	public User checkLoginValidation(AuthModel auth) {

		return null;
	}

	public ResponseMessage checkRegistrationValidity(RegisterModel register) throws SQLException {
		java.sql.Connection con = getConnection();

		String selectStatement = "SELECT * FROM " + DbCertificate.USER_TABLE_NAME + " " + "WHERE Username = "
				+ register.getUsername();

		java.sql.Statement st = con.createStatement();
		st.executeQuery(selectStatement);

		con.close();
		return new ResponseMessage(CommonConstants.SUCCESSFULL_MESSAGE, true);
	}

	public ResponseMessage updateUser(User user) {
		return null;
	}

	public ResponseMessage updateProfile(UserProfile profile) {

		return null;
	}

	private List<String> getUserModelValues(User user) {
		return Arrays.asList(user.getUsername(), user.getPassword(), user.getEmail(), user.getRole(), user.getGmailID(),
				user.getFacebookID(), user.getProfileID().toString());
	}

	private List<String> getProfileValues(UserProfile profile) {
		return Arrays.asList(profile.getName(), profile.getSurname(), profile.getGender(), profile.getCreateDate());
	}

}
