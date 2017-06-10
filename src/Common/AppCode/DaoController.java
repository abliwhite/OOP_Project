package Common.AppCode;

import java.util.ArrayList;
import java.util.List;

import Common.Models.DbAbstractModel;
import Common.Models.ResponseMessage;

public abstract class DaoController implements DaoInterface {

	public List<DbAbstractModel> dbFake;

	public ResponseMessage addProperty(DbAbstractModel object) {
		dbFake.add(object);
		return null;
	}
	
	public ResponseMessage updateProperty(DbAbstractModel object) {
		return null;
	}

	public ResponseMessage deleteProperty(DbAbstractModel object) {
		dbFake.remove(object);
		return null;
	}

	public DaoController() {
		dbFake = new ArrayList<DbAbstractModel>();
	
	}

}
