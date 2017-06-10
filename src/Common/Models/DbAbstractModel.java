package Common.Models;

public abstract class DbAbstractModel {

	private String tableName;
	
	public String getTableName(){
		return tableName;
	}
	
	public DbAbstractModel(String tableName){
		this.tableName = tableName;
	}
	
	public String getInsertValuesString(){
		return null;
	}
	
}
