package com.fantingame.game.gamecenter.partener.fantin.example;

import java.util.Scanner;

import com.fantingame.game.gamecenter.partener.fantin.AuthAPI;
import com.fantingame.game.gamecenter.partener.fantin.UserAPI;
import com.fantingame.game.gamecenter.partener.fantin.service.AuthBean;
import com.fantingame.game.gamecenter.partener.fantin.service.CodeConstant;
import com.fantingame.game.gamecenter.partener.fantin.service.EucAPIException;
import com.fantingame.game.gamecenter.partener.fantin.service.EucApiResult;
import com.fantingame.game.gamecenter.partener.fantin.service.EucToken;
import com.fantingame.game.gamecenter.partener.fantin.service.JUser;

public class UserExample {

	public static void main(String[] args) throws EucAPIException {
		EucApiResult<AuthBean> result2 = AuthAPI.login("test", "111111", false, null);
		EucToken eucToken = result2.getResult().getToken();
		String token = eucToken.getToken();
		EucApiResult<JUser> result = UserAPI.getUserById(result2.getResult().getUser().getId(), null);
		if(CodeConstant.OK.equals(result.getResultCode())) {
			JUser juser=result.getResult();
			System.out.println("登录成功: id " + juser.getId() + " 用户名 "  + juser.getName());
			System.out.println("密码：" + juser.getPasswd());
			System.out.println("token: " + eucToken.getToken());
		}
//		System.out.println("请输入你的旧密码(id 1000) :");
//		Scanner in = new Scanner(System.in);
//		String oldPass = in.nextLine();
//		System.out.println("请输入你的新密码(id 1000) :");
//		in=new Scanner(System.in);
//		String newPass = in.nextLine();
//		in.close();
//		EucApiResult<String> result3 = UserAPI.updatePasswd(token, oldPass, newPass, null);
//		System.out.println(result3.getResultCode());
	}
}