package com.fantingame.game.gamecenter.partener.fantin;

import com.fantingame.game.gamecenter.partener.fantin.para.AuthParametric;
import com.fantingame.game.gamecenter.partener.fantin.para.OAuthParametric;
import com.fantingame.game.gamecenter.partener.fantin.service.AuthBean;
import com.fantingame.game.gamecenter.partener.fantin.service.CodeConstant;
import com.fantingame.game.gamecenter.partener.fantin.service.EucAPIException;
import com.fantingame.game.gamecenter.partener.fantin.service.EucApiResult;
import com.fantingame.game.gamecenter.partener.fantin.service.EucService;
import com.fantingame.game.gamecenter.partener.fantin.service.EucToken;
import com.fantingame.game.gamecenter.partener.fantin.service.EucUCookie;
import com.fantingame.game.gamecenter.partener.fantin.service.JBean;
import com.fantingame.game.gamecenter.partener.fantin.service.JBody;
import com.fantingame.game.gamecenter.partener.fantin.service.JUser;
import com.fantingame.game.gamecenter.partener.fantin.service.RequestInfo;
import com.fantingame.game.gamecenter.partener.fantin.util.CookieUtil;

public class AuthAPI {

	protected static EucService eucService = EucService.getInstance();

	private static AuthParametric<RequestInfo> oAuthPara = new OAuthParametric();

	/**
	 * 用户名密码登录
	 * 
	 * @param userName
	 * @param password
	 * @param remember
	 * @param info
	 * @return
	 * @throws EucAPIException
	 */
	public static EucApiResult<AuthBean> login(String username,
			String password, boolean remember, RequestInfo info)
			throws EucAPIException {
		JBody jbody = new JBody();
		jbody.putContent("username", username);
		jbody.putContent("password", password);
		jbody.putContent("remember", remember);
		JBean jbean = eucService.getResult("/api2/login.json", jbody,
				oAuthPara, info);
		return buildAuthResult(jbean);
	}

	/**
	 * TOKEN验证接口
	 * 
	 * @param token	访问令牌
	 * @param u			加密cookie信息
	 * @param info		统计参数
	 * @return
	 * @throws EucAPIException
	 */
	public static EucApiResult<AuthBean> validate(String token, String u, RequestInfo info)
			throws EucAPIException {
		JBody jbody = new JBody();
		jbody.putContent(CookieUtil.COOKIE_TOKEN, token);
		jbody.putContent(CookieUtil.COOKIE_U, u);
		JBean jbean = eucService.getResult("/api2/validate.json", jbody,
				oAuthPara, info);
		return buildAuthResult(jbean);
	}

	/**
	 * 验证SERVICE TICKET
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws EucAPIException
	 */
	public static EucApiResult<AuthBean> validateServiceTicket(String ticket,
			RequestInfo info) throws EucAPIException {
		JBody jbody = new JBody();
		jbody.put("ticket", ticket);
		JBean jbean = eucService.getResult("/api2/validateServiceTicket.json",
				jbody, oAuthPara, info);
		return buildAuthResult(jbean);
	}

	/**
	 * 忘记密码请求重设密码
	 * 
	 * @param mobile
	 *            要重设密码的手机号
	 * @param info
	 * @return 没有返回结果，通过result.getResultCode()判断请求是否成功
	 * @throws EucAPIException
	 */
	public static EucApiResult<String> requestResetPass(String mobile,
			RequestInfo info) throws EucAPIException {
		JBody jbody = new JBody();
		jbody.put("mobile", mobile);
		JBean jbean = eucService.getResult("/api2/requestResetPass.json",
				jbody, oAuthPara, info);
		EucApiResult<String> result = new EucApiResult<String>(jbean);
		return result;
	}

	/**
	 * 忘记密码重设密码，通过手机号判断重设哪个用户
	 * 
	 * @param mobile
	 *            手机号
	 * @param info
	 * @return
	 * @throws EucAPIException
	 */
	public static EucApiResult<AuthBean> applyResetPass(String mobile,
			String newpwd, String veriCode, RequestInfo info)
			throws EucAPIException {
		JBody jbody = new JBody();
		jbody.put("mobile", mobile);
		jbody.put("newpwd", newpwd);
		jbody.put("veriCode", veriCode);
		JBean jbean = eucService.getResult("/api2/applyResetPass.json", jbody,
				oAuthPara, info);
		return  buildAuthResult(jbean);
	}

	private static EucApiResult<AuthBean> buildAuthResult(JBean jbean)
			throws EucAPIException {
		EucApiResult<AuthBean> result = new EucApiResult<AuthBean>(jbean);
		if (result.getResultCode().equals(CodeConstant.OK)) {
			JUser juser = jbean.getBody().getObject("user", JUser.class);
			juser.setQn(jbean.getHead().getQn());
			
			EucToken token = jbean.getBody().getObject("token", EucToken.class);
			EucUCookie u = jbean.getBody().getObject("U", EucUCookie.class);
			AuthBean eucAuthResult = new AuthBean(token, juser, u, String.valueOf(juser.getId()), false);
			result.setResult(eucAuthResult);
		}
		return result;
	}
	
	public static void main(String[] args) throws EucAPIException {
//		EucApiResult<AuthBean> result=AuthAPI.validate("TGT-425-pzkq1WaR9lK4qlwrfHC7jM0JQKZCYaG3HU4cwS11v4fVNGvRU5-sso", null);
//		JUser user= result.getResult().getUser();
//		System.out.println(user.getId() + " " + user.getNickName());
		EucApiResult<AuthBean> result=AuthAPI.validateServiceTicket("ST-7-CUDs6BCxZNwEr2EgU0Eo-sso", null);
		System.out.println(result.getResult().getToken().getToken());
		System.out.println(result.getResult().getUser().getId() + " " + result.getResult().getUser().getNickName());
	}
}
