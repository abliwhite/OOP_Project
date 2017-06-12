package Database;

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

	// (column1, column2, column3, ...)
	public String getQueryInsertColumns(List<String> columnNames) {
		String result = "";

		for (int i = CommonConstants.DB_ID_COLUMN_INDEX; i < columnNames.size(); i++) {
			result = result + columnNames.get(i) + ",";
		}
		
		result = "("+result.substring(0, result.length() - 1) + ')';
		
		return result;
	}
}
