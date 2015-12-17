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

public class RegisterAPI {

	protected static EucService eucService = EucService.getInstance();

	private static AuthParametric<RequestInfo> oAuthPara = new OAuthParametric();

	/**
	 * 自动注册
	 * 
	 * @param remember
	 *            是否需要回传记住密码cookie内容
	 * @return
	 * @throws EucAPIException
	 */
	public static EucApiResult<AuthBean> autoRegist(boolean remember,
			RequestInfo info) throws EucAPIException {
		JBody jbody = new JBody();
		if (remember) { // 为真传remember参数
			jbody.putContent("remember", remember);
		}
		JBean jbean = eucService.getResult("/api2/autoRegist.json", jbody, oAuthPara,
				info);
		return buildAuthResult(jbean);
	}

	/**
	 * 用户名注册
	 * 
	 * @param username
	 *            注册用户名(不能重复)
	 * @param password
	 *            注册密码
	 * @param remember
	 *            是否需要回传记住密码cookie内容
	 * @param info
	 * @return
	 * @throws EucAPIException
	 */
	public static EucApiResult<AuthBean> registByName(String username,
			String password, boolean remember, String bookNum, RequestInfo info)
			throws EucAPIException {
		JBody jbody = new JBody();
		jbody.putContent("username", username);
		jbody.putContent("password", password);
		if (remember) { // 为真传remember参数
			jbody.putContent("remember", remember);
		}
		jbody.putContent("bookNum", bookNum);
		JBean jbean = eucService.getResult("/api2/registByName.json", jbody, oAuthPara,
				info);
		return buildAuthResult(jbean);
	}

	/**
	 * 预约用户名，预约成功会返回一个预约名和预约号，用户名注册时可带上该预约号
	 * 
	 * @param username
	 * @param info
	 * @return	返回的字符串内容为{预约名},{预约号}
	 * @throws EucAPIException
	 */
	public static EucApiResult<String> bookingName(RequestInfo info) throws EucAPIException {
		JBody jbody = new JBody();
		JBean jbean = eucService.getResult("/api2/bookingName.json", jbody,oAuthPara,info);
		EucApiResult<String> result = new EucApiResult<String>(jbean);
		if (result.getResultCode().equals(CodeConstant.OK) && jbean.getBody()!=null) {
			result.setResult(jbean.getBody().getString("bookName") + "," + jbean.getBody().getString("bookNum"));
		}
		return result;
	}
	
	/**
	 * 检测用户名
	 * @param username
	 * @param info
	 * @return
	 * @throws EucAPIException
	 */
	public static EucApiResult<String> checkName(String username,
			RequestInfo info) throws EucAPIException {
		JBody jbody = new JBody();
		jbody.putContent("username", username);
		JBean jbean = eucService.getResult("/api2/checkName.json", jbody, oAuthPara,
				info);
		EucApiResult<String> result = new EucApiResult<String>(jbean);
		return result;
	}

	/**
	 * 手机验证码注册
	 * 
	 * @param mobile
	 *            注册手机号
	 * @param password
	 *            注册密码
	 * @param veriCode
	 *            注册验证码
	 * @param remember
	 *            是否需要回传记住密码cookie内容
	 * @return
	 * @throws EucAPIException
	 */
	public static EucApiResult<AuthBean> regByMobileCode(String mobile,
			String password, String veriCode, boolean remember, RequestInfo info)
			throws EucAPIException {
		JBody jbody = new JBody();
		jbody.putContent("mobile", mobile);
		jbody.putContent("password", password);
		jbody.putContent("veriCode", veriCode);
		if (remember) { // 为真传remember参数
			jbody.putContent("remember", remember);
		}
		JBean jbean = eucService.getResult("/api2/regByMobileCode.json", jbody,
				oAuthPara, info);
		return buildAuthResult(jbean);
	}

	/**
	 * 获得手机验证码，服务端会结该手机号发送一个验证码
	 * 
	 * @param mobile
	 *            手机号
	 * @return
	 * @throws EucAPIException
	 */
	public static EucApiResult<String> requestMobileCode(String mobile,
			RequestInfo info) throws EucAPIException {
		JBody jbody = new JBody();
		jbody.putContent("mobile", mobile);
		JBean jbean = eucService.getResult("/api2/requestMobileCode.json", jbody,
				oAuthPara, info);
		EucApiResult<String> result = new EucApiResult<String>(jbean);
		return result;
	}

	private static EucApiResult<AuthBean> buildAuthResult(JBean jbean)
			throws EucAPIException {
		EucApiResult<AuthBean> result = new EucApiResult<AuthBean>(
				jbean);
		if (result.getResultCode().equals(CodeConstant.OK)) {
			JUser juser = jbean.getBody().getObject("user", JUser.class);
			EucToken token=jbean.getBody().getObject("token", EucToken.class);
			EucUCookie u = jbean.getBody().getObject("U", EucUCookie.class);
			AuthBean eucAuthResult = new AuthBean(token, juser, u,
					String.valueOf(juser.getId()), false);
			result.setResult(eucAuthResult);
		}
		return result;
	}
}
