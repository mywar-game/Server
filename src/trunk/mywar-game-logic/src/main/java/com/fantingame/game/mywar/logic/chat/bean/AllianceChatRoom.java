package com.fantingame.game.mywar.logic.chat.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fantingame.game.mywar.logic.chat.constant.ChatConstant;
import com.fantingame.game.mywar.logic.user.bean.UserObject;
import com.fantingame.game.mywar.logic.user.constant.UserConstant;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.room.Room;

/**
 * 联盟
 * 
 * @author yezp
 */
public class AllianceChatRoom extends Room  {

	@Override
	public List<String> getAllUser() {
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		Set<String> userIds = userService.getAllOnlineUserId();
		List<String> hordeUserIds = new ArrayList<String>();
		for (String userId : userIds) {
			UserObject userObject = userService.getAllOnlineUserObject(userId);
			if (UserConstant.HORDE == userObject.getCamp())
				hordeUserIds.add(userId);
		}
		
		return hordeUserIds;
	}

	public String getRoomId() {
		return "cr_" + ChatConstant.ALLIANCE_CHAT_ROOMID;
	}	
	
}
