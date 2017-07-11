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

import Database.DbCertificate;
import Database.MyDBInfo;
import Database.QueryGenerator;

public abstract class DaoController {

	public QueryGenerator generator;
	private DataSource pool;

	public DaoController(DataSource pool) {
		this.pool = pool;
		generator = new QueryGenerator();
	}

	public java.sql.Connection getConnection() throws SQLException {
		return pool.getConnection();
	}

	private java.sql.ResultSetMetaData getTableMetaData(String tableName, java.sql.Connection con) throws SQLException {
		String hackQuery = generator.columnNameHackQuery(tableName);
		java.sql.Statement st = con.createStatement();

		st.executeQuery(generator.getUseDatabaseQuery());
		ResultSet rs = st.executeQuery(hackQuery);

		return rs.getMetaData();
	}

	public void setValues(List<String> values, java.sql.PreparedStatement st) throws SQLException {
		for (int i = 0; i < values.size(); i++) {
			st.setString(i + 1, values.get(i));
		}
	}

	private List<String> getTableColumnNames(java.sql.ResultSetMetaData meta) throws SQLException {
		List<String> columnNames = new ArrayList<String>();

		for (int i = 1; i <= meta.getColumnCount(); i++) {
			columnNames.add(meta.getColumnName(i));
		}
		
		return columnNames;
	}

	public List<String> getColumnsNames(String tableName) {
		List<String> result = new ArrayList<String>();

		try {
			java.sql.Connection con = getConnection();
			java.sql.ResultSetMetaData meta;
			meta = getTableMetaData(tableName, con);

			result = getTableColumnNames(meta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//ar gamaiyenot ;dd
	public void ExecuteUseDataBaseQuery(java.sql.Connection con) throws SQLException{
		con.createStatement().executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
	}
}
