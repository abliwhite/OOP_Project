package Chat.AppCode.ChatCore;

public class ActionMakerFactory {

	public static ActionMakerInterface getActionMaker(String type) {

		switch (type) {
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
