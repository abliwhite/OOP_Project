package Common.AppCode;

import Common.Models.DbAbstractModel;
import Common.Models.ResponseMessage;

public interface DaoInterface {

	ResponseMessage addProperty(DbAbstractModel object);
	//sheileba int id argumentad
	ResponseMessage deleteProperty(DbAbstractModel object);
	
	
}