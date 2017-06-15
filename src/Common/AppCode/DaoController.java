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

import Common.Models.ResponseMessage;
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

	public ResultSetMetaData getTableMetaData(String tableName, java.sql.Connection con) throws SQLException {
		String hackQuery = generator.columnNameHackQuery(tableName);
		java.sql.Statement st = con.createStatement();

		st.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
		ResultSet rs = st.executeQuery(hackQuery);
		
		return (ResultSetMetaData) rs.getMetaData();
	}

}
