package com.fantingame.game.mywar.logic.user.util;

import org.apache.commons.lang.StringUtils;

import com.fantingame.game.common.utils.Constant;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;

public class RoleUtils {
	/**
	 * 是不是合法的用户名
	 * 
	 * @param username
	 */
	public static void isValidUsername(String username) {

		if (StringUtils.isBlank(username)) {
			throw new ServiceException(UserService.CREATE_USERNAME_INVAILD, "用户名不能为空");
		}

		if (Constant.getBytes(username) > 12) {
			throw new ServiceException(UserService.CREATE_USERNAME_INVAILD, "用户名不长度不对.username[" + username + "]");
		}
	}
	
	
	
	public static String getIdSaveKey(String serverId) {
		return "ft_id_" + serverId;
	}

	public static long getFtId(String serverId, long id) {
		String ind = serverId.replaceAll("[a-zA-Z]+", "");
		String sid = String.valueOf(id);
		String s = ind + "000000".substring(0, 6 - sid.length()) + sid;
		return Long.valueOf(s);
	}
}
