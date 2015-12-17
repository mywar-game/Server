package com.fantingame.game.gamecenter.partener.fantin.example;

import com.fantingame.game.gamecenter.partener.fantin.UserAPI;
import com.fantingame.game.gamecenter.partener.fantin.service.CodeConstant;
import com.fantingame.game.gamecenter.partener.fantin.service.EucAPIException;
import com.fantingame.game.gamecenter.partener.fantin.service.EucApiResult;
import com.fantingame.game.gamecenter.partener.fantin.service.JUser;

public class GetUserExample {

	public static void main(String[] args) throws EucAPIException {
		EucApiResult<JUser> result = UserAPI.getUserById(22689818l, null);
		if(CodeConstant.OK.equals(result.getResultCode())) {
			JUser juser=result.getResult();
			System.out.println("登录成功: id " + juser.getId() + " 用户名 "  + juser.getName());
		}
	}
}