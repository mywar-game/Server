package com.fantingame.game.gamecenter.partener.fantin.example;

import java.net.URI;
import java.util.Scanner;

import com.fantingame.game.gamecenter.partener.fantin.AuthAPI;
import com.fantingame.game.gamecenter.partener.fantin.service.AuthBean;
import com.fantingame.game.gamecenter.partener.fantin.service.CodeConstant;
import com.fantingame.game.gamecenter.partener.fantin.service.EucAPIException;
import com.fantingame.game.gamecenter.partener.fantin.service.EucApiResult;
import com.fantingame.game.gamecenter.partener.fantin.service.JUser;
import com.fantingame.game.gamecenter.partener.fantin.service.RequestInfo;
import com.fantingame.game.gamecenter.partener.fantin.util.CommonUtils;

public class AuthExample {

	/** 授权登录地址 */
	private static String authUrl = "http://testsso.easou.com/quickLogin";

	/**
	 * 本示例
	 * 
	 * @param args
	 * @throws EucAPIException 
	 */
	public static void main(String[] args) throws EucAPIException {
		validate();
//		openBrowser();

		System.out.println("请输入你在浏览器上地址栏上看到的授权成功后的验证票(ticket参数值) :");
		Scanner in = new Scanner(System.in);
		String responseData = in.nextLine();
		in.close();

		String[] ticketArray = responseData.split("&");
		String ticket = responseData;
		if (ticketArray.length > 0) {
			for (int i = 0; i < ticketArray.length; i++) {
				if (ticketArray[i].startsWith("service=")) {
					ticket = ticketArray[i].substring(
							ticketArray[i].indexOf('=') + 1,
							ticketArray[i].length());
					break;
				}
			}
			RequestInfo info=new RequestInfo();
			info.setQn("qn");
			EucApiResult<AuthBean> result = AuthAPI.validateServiceTicket(
					ticket, info);
			if(CodeConstant.OK.equals(result.getResultCode())) {
				System.err.println("登录成功!!");
				String token = result.getResult().getToken().getToken();
				System.out.println("Token is: " + token);
				JUser juser = result.getResult().getUser();
				System.out.println("用户id: " + juser.getId());
			} else {
				System.err.println("授权失败，可能验证票已过期，该验证票生成后只保留60秒...");
			}
		} else {
			System.err.println("请填入正确的请求串");
		}
	}

	private static void openBrowser() {

		// 生成授权地址
		String authorizationUrl = CommonUtils.constructRedirectUrl(authUrl,
				"service", "http://www.easou.com", false, false);

		// 调用外部浏览器
		if (!java.awt.Desktop.isDesktopSupported()) {

			System.err.println("Desktop is not supported (fatal)");
			System.exit(1);
		}
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if (desktop == null
				|| !desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {

			System.err
					.println("Desktop doesn't support the browse action (fatal)");
			System.exit(1);
		}
		try {
			desktop.browse(new URI(authorizationUrl));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static void validate() {
		try {
			EucApiResult<AuthBean> result = AuthAPI.validate("GT-50-fZYQogydiDG3Qvw0wW7fSNRFMbcLD7EZLjbg7URICSQr9fe6GJ-sso", "7b92bf99c66d37c49065acaf760d975eddc8bad3766c8e0c15545de3e6bfca74480218b1a0cb4bdcc38e2c1b97c61018", null);
			System.out.println("validata=====");
			System.out.println("U=" + result.getResult().getU());
			System.out.println("TOKEN=" + result.getResult().getToken().getToken());
		} catch (EucAPIException e) {
			e.printStackTrace();
		}
	}
}
