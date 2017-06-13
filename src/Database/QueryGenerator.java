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
		String result = "";

		for (int i = CommonConstants.DB_ID_COLUMN_INDEX; i < columnNames.size(); i++) {
			result = result + columnNames.get(i) + ",";
		}

		result = "(" + result.substring(0, result.length() - 1) + ')';

		return result;
	}

	// Todo gadasasworebeli uketesadaaa dasaweri sashinlad weria chemi(ako)
	// dawerilia
	private String getQueryInsertValues(List<String> values) {
		String result = "";
		String separator = "','";

		for (int i = 0; i < values.size(); i++) {
			String value = values.get(i);

			if (value.equals(null)) {
				result = i == 0 ? "null" + "," : result.substring(0, result.length() - 1) + "null" + ",";
			} else {
				result = result + values.get(i) + separator;
			}

		}

		boolean edgeCaseOne = values.get(values.size() - 1).equals(null);
		boolean edgeCaseTwo = values.get(0).equals(null);

		if (edgeCaseOne || edgeCaseTwo) {
			result = edgeCaseOne ? "(" + result.substring(0, result.length() - separator.length()) + "')"
					: "('" + result.substring(0, result.length() - 1) + ")";
		} else {
			result = "('" + result.substring(0, result.length() - separator.length()) + "')";
		}

		return result;
	}

	public String getInsertQuery(List<String> columnNames, List<String> insertValues, String tableName) {
		return DB_INSERT_INTO + " " + tableName + " " + getQueryInsertColumns(columnNames) + " " + DB_VALUES_STRING
				+ " " + getQueryInsertValues(insertValues);
	}

}
