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
import Database.MyDBInfo;
import Database.QueryGenerator;

public abstract class DaoController  {

	public QueryGenerator generator;
	private DataSource pool;

	public DaoController(DataSource pool) {
		this.pool = pool;
		generator = new QueryGenerator();
	}

	public Connection getConnection() throws SQLException {
		return (Connection) pool.getConnection();
	}

	

}
