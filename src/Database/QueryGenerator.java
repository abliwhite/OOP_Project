package Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSetMetaData;

import Common.AppCode.CommonConstants;

public class QueryGenerator {
	public static final String DB_INSERT_INTO = "INSERT INTO";
	public static final String DB_VALUES_STRING = "VALUES";
	public static final String DB_SELECT_ALL_STATEMENT = "SELECT * FROM";
	public static final String DB_DELETE_STATEMENT = "DELETE FROM";
	public static final String DB_ROW_FETCHING_PREVENTER = "where 1=0";
	public static final String DB_WHERE_ID_CONDITION = "WHERE id = ";

	public QueryGenerator() {

	}

	public String columnNameHackQuery(String tableName) {
		return DB_SELECT_ALL_STATEMENT + " " + tableName + " " + DB_ROW_FETCHING_PREVENTER;
	}

	// (column1, column2, column3, ...)
	private String getQueryInsertColumns(List<String> columnNames) {
		StringBuilder str = new StringBuilder("(");
		for (int i = DbCertificate.DB_ID_COLUMN_INDEX; i < columnNames.size(); i++) {
			str.append(columnNames.get(i) + ",");
		}
		str.replace(str.length()-1, str.length(), ")");

		return str.toString();
	}

	private String getInsertNonInjectiveQuery(int numValues) {
		StringBuilder str = new StringBuilder("(");
		for (int i = 0; i < numValues; i++) {
			str.append("?,");
		}
		str.replace(str.length() - 1, str.length(), ");");
		
		return str.toString();
	}

	// miigebs columnebis saxelebs chasainsertebel valuebs da tablis saxels da
	// abrunebs insert query-is
	public String getInsertQuery(List<String> columnNames, String tableName) {
		return DB_INSERT_INTO + " " + tableName + " " + getQueryInsertColumns(columnNames) + " " + DB_VALUES_STRING
				+ " " + getInsertNonInjectiveQuery(columnNames.size() - 1);
	}

	public String getSelectQuery(List<String> columnNames, List<String> selectValues, String tableName) {
		return null;
	}

}
