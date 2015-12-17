package com.fantingame.game.mywar.logic.chat.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fantingame.game.mywar.logic.chat.constant.ChatConstant;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.room.Room;

/**
 * 世界聊天室
 * 
 * @author yezp
 */
public class WorldChatRoom extends Room {

	@Override
	public List<String> getAllUser() {
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		Set<String> userIds = userService.getAllOnlineUserId();
		List<String> list = new ArrayList<String>();
		list.addAll(userIds);
		
		return list;
	}

	public String getRoomId() {
		return "cr_" + ChatConstant.WORLD_CHAT_ROOMID;
	}
	
}
