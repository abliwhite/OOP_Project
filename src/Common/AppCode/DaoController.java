package Common.AppCode;

import java.util.ArrayList;
import java.util.List;

import Common.Models.DbAbstractModel;
import Common.Models.ResponseMessage;

public abstract class DaoController implements DaoInterface {

	private List<DbAbstractModel> dbFake;

	@Override
	public ResponseMessage addProperty(DbAbstractModel object) {
		try {
			dbFake.add(object);
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public ResponseMessage deleteProperty(DbAbstractModel object) {
		try {
			dbFake.remove(object);
		} catch (Exception e) {

		}
		return null;
	}

	public DaoController() {
		dbFake = new ArrayList<DbAbstractModel>();
	}

}
