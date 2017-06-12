package Common.AppCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import Common.Models.DbAbstractModel;
import Common.Models.ResponseMessage;
import Database.MyDBInfo;
import Database.QueryGenerator;

public abstract class DaoController implements DaoInterface {

	public QueryGenerator generator;
	private DataSource pool;

	public List<DbAbstractModel> dbFake;

	public ResponseMessage addProperty(DbAbstractModel object) {
		ResponseMessage response = null;

		try {
			java.sql.Connection con = pool.getConnection();
			PreparedStatement st = (PreparedStatement) con.createStatement();
			ResultSet rs = st.executeQuery(QueryGenerator.DB_SELECT_ALL_STATEMENT + " " + object.getTableName() + " "
					+ QueryGenerator.DB_ROW_FETCHING_PREVENTER);

			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
			String insertQuery = QueryGenerator.DB_INSERT_INTO + " " + object.getTableName() + " "
					+ generator.getQueryInsertColumns(getColumnNames(meta)) + " " + QueryGenerator.DB_VALUES_STRING
					+ " " + object.getInsertValuesString();

			st.executeQuery(insertQuery);

			response = new ResponseMessage(CommonConstants.SUCCESSFULL_MESSAGE, true);

			con.close();
		} catch (SQLException e) {
			response = new ResponseMessage(e.getMessage(), false);
			e.printStackTrace();
		}

		return response;
	}

	public ResponseMessage updateProperty(DbAbstractModel object) {
		return null;
	}

	public ResponseMessage deleteProperty(DbAbstractModel object) {
		ResponseMessage response = null;

		try {
			java.sql.Connection con = pool.getConnection();
			PreparedStatement st = (PreparedStatement) con.createStatement();
			st.executeQuery(QueryGenerator.DB_DELETE_STATEMENT + " " + object.getTableName() + " "
					+ QueryGenerator.DB_WHERE_ID_CONDITION + object.getId());

			response = new ResponseMessage(CommonConstants.SUCCESSFULL_MESSAGE, true);

			con.close();
		} catch (SQLException e) {
			response = new ResponseMessage(e.getMessage(), false);
			e.printStackTrace();
		}

		return response;
	}

	public DaoController(DataSource pool) {
		this.pool = pool;
		generator = new QueryGenerator();

	}

	public Connection getConnection() throws SQLException {
		return (Connection) pool.getConnection();
	}

	private List<String> getColumnNames(ResultSetMetaData meta) throws SQLException {
		List<String> columnNames = new ArrayList<String>();

		for (int i = 1; i <= meta.getColumnCount(); i++) {
			columnNames.add(meta.getColumnName(i));
		}
		return columnNames;
	}

}
