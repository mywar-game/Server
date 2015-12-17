package com.fantingame.game.gateway.user.util;


import com.fantingame.game.gateway.server.manager.Session;
import com.fantingame.game.gateway.server.manager.SessionManager;
import com.fantingame.game.gateway.server.manager.UserInfo;
import com.fantingame.game.gateway.server.manager.UserManager;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.server.config.LocalTools;
import com.fantingame.game.server.msg.MsgBuilder;
import com.fantingame.game.server.msg.ServerType;
import com.fantingame.game.server.util.MD5;

public class UserUtil {
	/**
	 * 踢出其他在线用户
	 * @param userId
	 */
	public static void kickOutOtherUser(String userId){
		UserManager userManager = UserManager.getInstance();
		 if(userManager.isExsitUser(userId)){
			UserInfo userInfo = userManager.getUserInfoByUserId(userId+"");
			   //发强踢消息
			Session session = SessionManager.getInstance().getSession(userInfo.getSessionId());
			if (session != null) {
				Msg msg = MsgBuilder.buildUserMsg(ServerType.GATEWAY_SERVER, userId, "user_logout", MsgBuilder.EMPTY_BODY);
				session.sendMsgAndCloseChannel(msg);
			}
		  }
	}
	
	/**
	 * 校验url登录参数MD5值是否正确
	 * @param userName
	 * @param time
	 * @param MD5Str
	 * @return
	 */
	public static boolean checkMD5Key(String paterneUserId,String serverId, long loginTime, String MD5Str) {
		StringBuffer buffer = new StringBuffer();
		String md5Key = LocalTools.getLocalConfig().getMd5Key();
		buffer.append(paterneUserId).append(serverId).append(loginTime+"").append(md5Key);
		String newMD5Str = MD5.md5Of32(buffer.toString());
		if (newMD5Str != null && !newMD5Str.equals("") && newMD5Str.equals(MD5Str)) {
			return true;
		} else {
			return false;
		}
	}
}
