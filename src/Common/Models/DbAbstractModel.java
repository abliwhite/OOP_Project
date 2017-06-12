package Common.Models;

public abstract class DbAbstractModel {

	private String tableName;
	private Integer id;
	
	public String getTableName(){
		return tableName;
	}
	
	public Integer getId(){
		return id;
	}
	
	public DbAbstractModel(String tableName,Integer id){
		this.id = id;
		this.tableName = tableName;
	}
	
	public String getInsertValuesString(){
		return null;
	}
	
	
}
