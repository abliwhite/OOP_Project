package Chat.AppCode.ChatCore;

import Chat.Models.ADTModels.Message;

public interface ActionMakerInterface {

	void processMessage(Message m);
}
