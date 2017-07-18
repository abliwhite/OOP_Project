package Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSetMetaData;

import Common.AppCode.CommonConstants;

public class QueryGenerator {
	private static final String DB_INSERT_INTO = "INSERT INTO";
	private static final String DB_UPDATE = "UPDATE";
	private static final String DB_SET = "SET";
	private static final String DB_VALUES = "VALUES";
	private static final String DB_SELECT_ALL = "SELECT * FROM";
	private static final String DB_DELETE = "DELETE FROM";
	private static final String DB_ROW_FETCHING_PREVENTER = "where 1=0";
	private static final String DB_WHERE_ID_CONDITION = "WHERE ID = ";
	private static final String DB_WHERE_ID_IN_CLAUSE = "WHERE ID IN ";

	public QueryGenerator() {

	}

	public String columnNameHackQuery(String tableName) {
		return DB_SELECT_ALL + " " + tableName + " " + DB_ROW_FETCHING_PREVENTER;
	}

	// (column1, column2, column3, ...)
	private String getQueryInsertColumns(List<String> columnNames) {
		StringBuilder str = new StringBuilder("(");
		for (int i = DbCertificate.DB_ID_COLUMN_INDEX; i < columnNames.size(); i++) {
			str.append(columnNames.get(i) + ",");
		}
		str.replace(str.length() - 1, str.length(), ")");

		return str.toString();
	}

	private String getInsertNonInjectiveQuery(int numValues) {
		StringBuilder str = new StringBuilder("(");
		for (int i = 0; i < numValues; i++) {
			str.append("?,");
		}
		str.replace(str.length() - 1, str.length(), ")");

		return str.toString();
	}

	private String getUpdateNonInjectiveQuery(List<String> columnNames) {
		StringBuilder result = new StringBuilder();
		for (int i = DbCertificate.DB_ID_COLUMN_INDEX; i < columnNames.size(); i++) {
			result.append(columnNames.get(i) + " = ?,");
		}

		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}

	// miigebs columnebis saxelebs chasainsertebel valuebs da tablis saxels da
	// abrunebs insert query-is
	public String getInsertQuery(List<String> columnNames, String tableName) {
		return DB_INSERT_INTO + " " + tableName + " " + getQueryInsertColumns(columnNames) + " " + DB_VALUES + " "
				+ getInsertNonInjectiveQuery(columnNames.size() - 1);
	}

	public String getUpdateByIdQuery(List<String> columnNames, String tableName, Integer id) {
		return DB_UPDATE + " " + tableName + " " + DB_SET + " " + getUpdateNonInjectiveQuery(columnNames) + " "
				+ DB_WHERE_ID_CONDITION + id;
	}

	public String getDeleteByIdQuery(String tableName) {
		return DB_DELETE + " " + tableName + " " + DB_WHERE_ID_CONDITION + "?";
	}

	public String getUseDatabaseQuery() {
		return "USE " + MyDBInfo.MYSQL_DATABASE_NAME;
	}

	public String getDeleteByAnyIDQuery(String tableName, String idName) {
		return DB_DELETE + " " + tableName + " WHERE " + idName + "= ?;";
	}

	public String getSelectByIDQuery(String tableName, String idName, int numInClauseArguments) {
		return DB_SELECT_ALL + " " + tableName + " WHERE " + idName + " IN "
				+ getInsertNonInjectiveQuery(numInClauseArguments);
	}

	public String getSelectTopLimitOrderByByIDQuery(String tableName, String idName, int numInClauseArguments,
			int limit, String orderColumnName) {
		return getSelectByIDQuery(tableName, idName, numInClauseArguments) + " " + " Order By " + orderColumnName
				+ " LIMIT " + limit;
	}

	public String getSelectAllQuery(String tableName) {
		return DB_SELECT_ALL + " " + tableName;
	}

	public String getDeleteByIdListQuery(String tableName, String idName, int numIds) {
		String result = "";
		for (int i = 0; i < numIds; i++) {
			result = result + getDeleteByAnyIDQuery(tableName, idName)+";";
		}

		return result;
	}

}
