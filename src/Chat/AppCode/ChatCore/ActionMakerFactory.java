package Chat.AppCode.ChatCore;

public class ActionMakerFactory {

	public static ActionMakerInterface getActionMaker(String type) {

		switch (type) {
		case "ResponseMessage":
			return new ActionMakerResponse();
		case "RequestMessage":
			return new ActionMakerRequest();
		case "ExternalMessage":
			return new ActionMakerExternalMessage();
		case "InternalMessage":
			return new ActionMakerInternalMessage();

		default:
			return null;
		}
	}

}
