package com.fantingame.game.mywar.logic.chat.plugin;

import com.fandingame.game.framework.plugin.IAppPlugin;
import com.fantingame.game.mywar.logic.chat.bean.AllianceChatRoom;
import com.fantingame.game.mywar.logic.chat.bean.HordeChatRoom;
import com.fantingame.game.mywar.logic.chat.bean.WorldChatRoom;
import com.fantingame.game.server.room.RoomManager;

/**
 * 启动程序后创建聊天室
 * 
 * @author yezp
 */
public class ChatStartPlugin implements IAppPlugin {

	@Override
	public void startup() throws Exception {
		WorldChatRoom worldChatRoom = new WorldChatRoom();
		HordeChatRoom hordeChatRoom = new HordeChatRoom();
		AllianceChatRoom allianceChatRoom = new AllianceChatRoom();
		
		RoomManager.getInstatnce().addRoom(hordeChatRoom.getRoomId(), hordeChatRoom);
		RoomManager.getInstatnce().addRoom(allianceChatRoom.getRoomId(), allianceChatRoom);
		RoomManager.getInstatnce().addRoom(worldChatRoom.getRoomId(), worldChatRoom);
	}

	@Override
	public void shutdown() throws Exception {
	}
	
	@Override
	public int cpOrder() {
		return 0;
	}

}
