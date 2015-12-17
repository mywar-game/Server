package com.fantingame.game.gamecenter.partener.fantin.example;

import com.fantingame.game.gamecenter.partener.fantin.RegisterAPI;
import com.fantingame.game.gamecenter.partener.fantin.service.AuthBean;
import com.fantingame.game.gamecenter.partener.fantin.service.CodeConstant;
import com.fantingame.game.gamecenter.partener.fantin.service.EucAPIException;
import com.fantingame.game.gamecenter.partener.fantin.service.EucApiResult;

public class AutoRegisterExample {
	public static void main(String[] args) throws EucAPIException {
		// 自动注册一个新帐号，
		EucApiResult<AuthBean> result=RegisterAPI.autoRegist(true, null);
		if(CodeConstant.OK.equals(result.getResultCode())) {
			AuthBean bean = result.getResult();
			System.out.println(bean.getToken().getToken());
			System.out.println(bean.getU().getU());
			// 一键注册可以返回密码
			System.out.println(bean.getUser().getName() + " " + bean.getUser().getPasswd());
		}
	}
}