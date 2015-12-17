package com.fantingame.game.gamecenter.controller.login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.fantingame.game.gamecenter.bo.UserQueneInfo;
import com.fantingame.game.gamecenter.bo.UserToken;
import com.fantingame.game.gamecenter.config.Config;
import com.fantingame.game.gamecenter.constant.BlackListType;
import com.fantingame.game.gamecenter.constant.WebApiCode;
import com.fantingame.game.gamecenter.dao.ActiveCodeDao;
import com.fantingame.game.gamecenter.dao.NoticeDao;
import com.fantingame.game.gamecenter.dao.ServerListDao;
import com.fantingame.game.gamecenter.dao.UserInfoDao;
import com.fantingame.game.gamecenter.dao.VersionServerDao;
import com.fantingame.game.gamecenter.exception.ServiceException;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.handler.QueneManager;
import com.fantingame.game.gamecenter.model.ActiveCode;
import com.fantingame.game.gamecenter.model.AmendNotice;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.model.GameServerStatus;
import com.fantingame.game.gamecenter.model.Notice;
import com.fantingame.game.gamecenter.model.SpecialNotice;
import com.fantingame.game.gamecenter.model.UserInfo;
import com.fantingame.game.gamecenter.model.VersionServer;
import com.fantingame.game.gamecenter.partener.anzhi.AnZhiAdSdk;
import com.fantingame.game.gamecenter.partener.anzhi.AnZhiSdk;
import com.fantingame.game.gamecenter.partener.appchina.AppChinaSdk;
import com.fantingame.game.gamecenter.partener.apple.AppleSdk;
import com.fantingame.game.gamecenter.partener.baidu.BaiduSdk;
import com.fantingame.game.gamecenter.partener.baidu.BaiduZsSdk;
import com.fantingame.game.gamecenter.partener.changwan.ChangWanSdk;
import com.fantingame.game.gamecenter.partener.chinamobile.ChinaMobileSdk;
import com.fantingame.game.gamecenter.partener.dangle.DangleSdk;
import com.fantingame.game.gamecenter.partener.dangle.NewDangleSdk;
import com.fantingame.game.gamecenter.partener.dianjin.DianJinSdk;
import com.fantingame.game.gamecenter.partener.duoku.DuoKuSdk;
import com.fantingame.game.gamecenter.partener.fantin.FanDinSdk;
import com.fantingame.game.gamecenter.partener.hucn.HuCnSdk;
import com.fantingame.game.gamecenter.partener.kuaibo.KuaiBoSdk;
import com.fantingame.game.gamecenter.partener.kuwo.KuWoSdk;
import com.fantingame.game.gamecenter.partener.lenovo.LenovoSdk;
import com.fantingame.game.gamecenter.partener.oppo.OppoSdk;
import com.fantingame.game.gamecenter.partener.pps.PPSSdk;
import com.fantingame.game.gamecenter.partener.qihoo.QiHooSdk;
import com.fantingame.game.gamecenter.partener.shijia.ShiJiaSdk;
import com.fantingame.game.gamecenter.partener.sqw.SanqiWanwanSdk;
import com.fantingame.game.gamecenter.partener.uc.UcSdk;
import com.fantingame.game.gamecenter.partener.wandoujia.WanDouJiaSdk;
import com.fantingame.game.gamecenter.partener.xiaomi.XiaomiSdk;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.EncryptUtil;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.json.Json;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
public class LoginController {

	private static Logger LOG = Logger.getLogger(LoginController.class);

	/**
	 * 成功
	 */
	public static final int SUCCESS = 1000;
	/**
	 * 未知错误
	 */
	public static final int UNKNOWN_ERROR = 2000;
	/**
	 * 参数错误
	 */
	public static final int PARAM_ERROR = 2001;
	/**
	 * 校验签名错误
	 */
	public static final int SIGN_CHECK_ERROR = 2002;
	/**
	 * 登录验证失败
	 */
	public static final int LOGIN_VALID_FAILD = 2003;
	/**
	 * 游戏服务器登录失败
	 */
	public static final int LOGIN_GAME_FAILD = 2004;

	/**
	 * 版本失效，无法升级以及登陆
	 */
	public static final int VERSION_EXPIRE = 2005;

	/**
	 * 登录服务器关闭
	 */
	public static final int LOGIN_SERVER_CLOSE = 2006;
	/**
	 * 游戏服务器关闭
	 */
	public static final int GAME_SERVER_CLOSE = 2007;

	/**
	 * 激活码无效
	 */
	public static final int ACTIVE_FAILD = 2005;
	
	@Autowired
	private ActiveCodeDao activeCodeDao;

	@Autowired
	private VersionServerDao versionServerDao;
	@Autowired
	private PartnerServiceFactory serviceFactory;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private UserInfoDao userInfoDao;

//	private Map<String, List<GameServer>> serverMap;
	@Autowired
	private ServerListDao serverListDao;
	
	@RequestMapping("/webApi/login.do")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) {
		String token = req.getParameter("token");
		String partnerId = req.getParameter("partnerId");
		String serverId = req.getParameter("serverId");
		String timestamp = req.getParameter("timestamp");
		String sign = req.getParameter("sign");
		String imei = req.getParameter("fr");
		String mac = req.getParameter("mac");
		String idfa = req.getParameter("idfa");
		String mobile = req.getParameter("ua");
		String ip = req.getRemoteAddr();
		String queneToken = req.getParameter("queneToken");
		Date now = new Date();
		
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		
		// TODO 排队
		UserQueneInfo userQueneInfo = QueneManager.instance().getUserQueneInfo(serverId, queneToken);
		LOG.debug("---------------waitingTime:" + userQueneInfo.getWaitingTime());
		if (userQueneInfo.getWaitingTime() > 0 
				&& (now.getTime() < userQueneInfo.getLoginTime().getTime() + userQueneInfo.getWaitingTime())) {
			map.put("rc", 2001);
			map.put("queneToken", userQueneInfo.getQueneToken());
			map.put("wt", userQueneInfo.getWaitingTime());
			map.put("pn", userQueneInfo.getPerson());
			
			view.setAttributesMap(map);
			modelView.setView(view);
			return modelView;
		}		
		
		// ios设备Token
		String deviceToken = req.getParameter("dv");
		if (StringUtils.isBlank(deviceToken)) {
			deviceToken = "";
		}
		
		if(StringUtils.isBlank(imei)){
			imei="xx_imei";
		}
		if(StringUtils.isBlank(mac)){
			mac="xx_mac";
		}
		if(StringUtils.isBlank(ip)){
			ip="xx_ip";
		}
		if(StringUtils.isBlank(mobile)){
			mobile = "nomobile";
		}
		
		PartnerService ps = serviceFactory.getBean(partnerId);		
		//查询出登录到的服务器状态
		GameServerStatus gameServerStatus = activeCodeDao.getGameServerBean(serverId);
		//如果关闭则返回维护通知
		if(gameServerStatus!=null&&gameServerStatus.getStatus()==0){
			if(!activeCodeDao.isInBlackList(imei, mac, ip, BlackListType.ENTER_GAME_SERVER_LIST_BLACK_LIST)){
				throw new ServiceException(WebApiCode.GAME_SERVER_CLOSE, gameServerStatus.getNotice());
			}
		}
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("imei", imei);
			params.put("mac", mac);
			params.put("idfa", idfa);
			params.put("ip", ip);
			params.put("mobile", mobile);			
			UserToken userToken = ps.login(token, partnerId, serverId, Long.parseLong(timestamp), sign, params);
			
			if (userToken != null) {
				// 保存用户相关信息
				UserInfo userInfo = this.userInfoDao.getUserInfo(userToken.getPartnerUserId(), userToken.getServerId());
				if (userInfo == null) {
					userInfo = new UserInfo();
					
					userInfo.setUserId(userToken.getUserId());
					userInfo.setPartnerId(userToken.getPartnerId());
					userInfo.setPartnerUserId(userToken.getPartnerUserId());
					userInfo.setServerId(userToken.getServerId());
					userInfo.setUpdatedTime(now);
					userInfo.setCreatedTime(now);
					userInfoDao.save(userInfo);
				} else {
					userInfoDao.updateUserInfo(userInfo);
				}
				
				// IOS 设备Token
				if (!deviceToken.equals("")) {
					GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
					GameApiSdk.getInstance().checkDeviceToken(userToken.getUserId(), deviceToken, gameServer);
				}
				
				map.put("rc", WebApiCode.SUCCESS);
				Map<String, String> data = new HashMap<String, String>();
				data.put("tk", userToken.getToken());
				data.put("uid", userToken.getUserId());
				data.put("puid", userToken.getPartnerUserId());
				data.put("ptk", userToken.getPartnerToken());
				data.put("exti", userToken.getExtInfo());
				// UC会员信息
				String extra1 = userToken.getExtInfo();
				if (extra1 == null || extra1.equals("")) {
					extra1 = "0"; // 非会员，避免其它渠道为空的时候报错
				}
				data.put("extra1", extra1);
				
				AmendNotice admendNotice = noticeDao.getAmendNotice(serverId, partnerId);
				if (admendNotice != null && admendNotice.getIsEnable() == 1) {
					data.put("title", admendNotice.getTitle());
					data.put("notice", admendNotice.getContent());
					LOG.info("修正公告：" + admendNotice.getTitle());
				} else {
					Notice notice = noticeDao.getNotice(serverId);
					if (notice != null && notice.getIsEnable() == 1) {
						data.put("title", notice.getTitle());
						data.put("notice", notice.getContent());
					}
				}			
				
				map.put("dt", data);
			} else {
				map.put("rc", WebApiCode.UNKNOWN_ERROR);
			}
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			map.put("rc", e.getCode());
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			map.put("rc", WebApiCode.UNKNOWN_ERROR);
		}
		
		// 设置排队移出时间
		userQueneInfo.setRemoveTime(new Date(now.getTime() + 5 * 1000));

		map.put("rc", 1000);
		view.setAttributesMap(map);
		modelView.setView(view);
		// LOG.debug("parnterId:" + partnerId + "," + Json.toJson(map));
		return modelView;
	}

	@SuppressWarnings("unused")
	@RequestMapping("/webApi/isActive.do")
	public ModelAndView isActive(HttpServletRequest req, HttpServletResponse res) {
		String uuid = req.getParameter("uuid");
		String partnerId = req.getParameter("partnerId");
		Map<String, Object> map = new HashMap<String, Object>();
		PartnerService ps = serviceFactory.getBean(partnerId);
		try {
//			boolean isActive = ps.isActive(uuid, partnerId);
//			LOG.info("uuid:" + uuid + " partnerId:" + partnerId + " isActive:" + isActive);
//			map.put("rc", isActive ? WebApiCode.SUCCESS : WebApiCode.ACTIVE_FAILD);
			map.put("rc", WebApiCode.SUCCESS);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			map.put("rc", e.getCode());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			map.put("rc", WebApiCode.UNKNOWN_ERROR);
		}
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		LOG.debug(Json.toJson(map));
		return modelView;
	}

	@SuppressWarnings("unused")
	@RequestMapping("/webApi/active.do")
	public ModelAndView active(HttpServletRequest req, HttpServletResponse res) {
		String uuid = req.getParameter("uuid");
		String partnerId = req.getParameter("partnerId");
		String code = req.getParameter("code");
		PartnerService ps = serviceFactory.getBean(partnerId);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
//			boolean success = true;
//			if (Integer.parseInt(partnerId) == 1003 || Integer.parseInt(partnerId) == 1015) {
//				success = ps.addActive(uuid, code, partnerId);
//			} else {
//				success = ps.active(uuid, code, partnerId);
//			}
//			
//			LOG.info("uuid:" + uuid + " code:" + code + " partnerId:" + partnerId + " success:" + success);
//			map.put("rc", success ? WebApiCode.SUCCESS : WebApiCode.ACTIVE_FAILD);
			map.put("rc", WebApiCode.SUCCESS);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			map.put("rc", e.getCode());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			map.put("rc", WebApiCode.UNKNOWN_ERROR);
		}
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		LOG.debug(Json.toJson(map));
		return modelView;
	}

	/**
	 * 获取服务器列表
	 * 
	 * @return
	 */
	// TODO 实现获取服务器列表
	@RequestMapping("/webApi/getServerList.do")
	public ModelAndView getServerList(HttpServletRequest req) {
		String partnerId = StringUtils.isBlank(req.getParameter("partnerId")) ? "1001" : req.getParameter("partnerId");
		List<GameServer> serverList = null;
		String version = req.getParameter("version");
		VersionServer versionServer = null;
		String imei = req.getParameter("fr");
		String mac = req.getParameter("mac");
		String ip = req.getRemoteAddr();
		String partnerUserId = req.getParameter("partnerUserId");
		if(StringUtils.isBlank(imei)){
			imei="xx_imei";
		}
		if(StringUtils.isBlank(mac)){
			mac="xx_mac";
		}
		if(StringUtils.isBlank(ip)){
			mac="xx_ip";
		}
		if (!StringUtils.isBlank(version)) {
			versionServer = versionServerDao.getVersionServer(version);
		}
		
		Map<String, String> loginServerMap = Maps.newConcurrentMap();
		if (StringUtils.isNotBlank(partnerUserId)) {
			List<UserInfo> infoList = this.userInfoDao.getUserInfoList(partnerUserId);
			if (infoList != null && infoList.size() > 0) {
				for (UserInfo userInfo : infoList) {
					loginServerMap.put(userInfo.getServerId(), userInfo.getServerId());
				}
			}
		}
		
//		if (serverMap != null && serverMap.size() > 0) {
//			serverList = serverMap.get(partnerId);
//		} else {
//			serverMap = new HashMap<String, List<GameServer>>();
//		}
		serverList = serverListDao.getServerListByPartenerId(partnerId);//AdminApiSdk.getInstance().loadServers(partnerId);

//		if (serverList == null || serverList.size() == 0) {
//			serverList = serverListDao.getServerListByPartenerId(partnerId);//AdminApiSdk.getInstance().loadServers(partnerId);
//
//			LOG.debug("serverList size:" + serverList.size());
//			serverMap.put(partnerId, serverList);
//		}

		// 登陆过的服务器列表
		List<GameServer> loginServerList = Lists.newArrayList();
		if (serverList != null && serverList.size() > 0) {
			boolean isSpceImei = false;
			if (activeCodeDao.isInBlackList(imei,mac,ip, BlackListType.GET_SERVER_LIST_BLACK_LIST)) {
				isSpceImei = true;
			}
			// 处理IMEI白名单
			List<GameServer> serverListForSpecImei = new ArrayList<GameServer>(serverList.size());
			for (GameServer gs : serverList) {
				// IMEI白名单中的GameServer状态都需要为1
				int status = gs.getStatus();
				if (status == 100) {
					if (isSpceImei) {
						status = 1;
					} else {
						continue;
					}
				}
				if(status==4){
					if (isSpceImei) {
						status = 1;
					}
				}
//				String ind = gs.getServerId().replaceAll("[a-zA-Z]+", "");
				//服务器能兼容的版本号  
				if (versionServer != null) {
					if (versionServer.getServerId().indexOf(gs.getRealServerId()) < 0) {
						continue;
					}
				}
				GameServer gameServerForSpecImei = new GameServer();
				gameServerForSpecImei.setServerId(gs.getServerId());
//				gameServerForSpecImei.setServerName(ind+"-"+gs.getServerName());
				
				gameServerForSpecImei.setServerName(gs.getServerName());
				gameServerForSpecImei.setServerUrl(gs.getServerUrl());
				gameServerForSpecImei.setServerPort(gs.getServerPort());
				gameServerForSpecImei.setStatus(status);
				gameServerForSpecImei.setOpenTime(gs.getOpenTime());
				serverListForSpecImei.add(gameServerForSpecImei);
				
				if (loginServerMap.containsKey(gs.getServerId())) {
					loginServerList.add(gameServerForSpecImei);
				}
			}

			Collections.sort(serverListForSpecImei, new Comparator<GameServer>() {
				@Override
				public int compare(GameServer o1, GameServer o2) {
					if (o1.getOpenTime().getTime() > o2.getOpenTime().getTime()) {
						return -1;
					} else if (o1.getOpenTime().getTime() < o2.getOpenTime().getTime()) {
						return 1;
					}

					return 0;
				}

			});

			// 使用为IMEI白名单定制的ServerList
			serverList = serverListForSpecImei;
		} 
//		else {
//			serverList = new ArrayList<GameServer>();
//			GameServer gs = new GameServer();
//			gs.setServerId("d1");
//			gs.setServerName("测试服务器");
//			gs.setStatus(1);
//			serverList.add(gs);
//		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sl", serverList);
		map.put("loginServer", loginServerList);
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}
	
	/**
	 * 获取特殊的通告
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/webApi/getNotice.do")
	public ModelAndView getNotice(HttpServletRequest req) {
		String partnerId = req.getParameter("partnerId");
		
		Map<String, Object> map = new HashMap<String, Object>();
		SpecialNotice specialNotice = noticeDao.getSpecialNotice(partnerId);
		if (specialNotice != null && specialNotice.getIsEnable() == 1) {
			map.put("title", specialNotice.getTitle());
			map.put("notice", specialNotice.getContent());
		}	
		
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}
	
	@RequestMapping("/webApi/getSysTime.do")
	public ModelAndView getSysTime() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Long> tMap = new HashMap<String, Long>();
		tMap.put("t", System.currentTimeMillis());
		map.put("rc", 1000);
		map.put("dt", tMap);
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}

	@RequestMapping("/webApi/updateConfigs.do")
	public ModelAndView updateServers() {
		Map<String, Object> map = new HashMap<String, Object>();
//		PackageConfig.instance().reload();
//		QiHooSdk.instance().reload();
//		BaiduSdk.instance().reload();
//		UcSdk.instance().reload();
//		LenovoSdk.instance().reload();
//		EasouSdk.instance().reload();
//		PartnerConfig.ins().reload();
//		BaiduZsSdk.instance().reload();
//		AnZhiSdk.instance().reload();
//		AppChinaSdk.instance().reload();
//		AppleSdk.instance().reload();
//		ChangWanSdk.instance().reload();
//		ChinaMobileSdk.instance().reload();
//		DangleSdk.instance().reload();
//		DuoKuSdk.instance().reload();
//		HuCnSdk.instance().reload();
//		SanqiWanwanSdk.instance().reload();
//		ShiJiaSdk.instance().reload();
//		XiaomiSdk.instance().reload();
//		KuaiBoSdk.instance().reload();
//		AnZhiAdSdk.instance().reload();
//		PPSSdk.instance().reload();
//		KuWoSdk.instance().reload();
		try {
			QiHooSdk.instance().reload();
			BaiduSdk.instance().reload();
			UcSdk.instance().reload();
			LenovoSdk.instance().reload();
			FanDinSdk.instance().reload();
			BaiduZsSdk.instance().reload();
			AnZhiSdk.instance().reload();
			AppChinaSdk.instance().reload();
			AppleSdk.instance().reload();
			ChangWanSdk.instance().reload();
			ChinaMobileSdk.instance().reload();
			DangleSdk.instance().reload();
			NewDangleSdk.instance().reload();
			DuoKuSdk.instance().reload();
			HuCnSdk.instance().reload();
			SanqiWanwanSdk.instance().reload();
			ShiJiaSdk.instance().reload();
			XiaomiSdk.instance().reload();
			KuaiBoSdk.instance().reload();
			AnZhiAdSdk.instance().reload();
			PPSSdk.instance().reload();
			KuWoSdk.instance().reload();
			WanDouJiaSdk.instance().reload();
			OppoSdk.instance().reload();
			DianJinSdk.instance().reload();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("rc", 1000);
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}
    /**
     * 刷新服务器列表
     * @return
     */
	@RequestMapping("/webApi/refreshServerList.do")
	public ModelAndView refreshServerList() {
		Map<String, Object> map = new HashMap<String, Object>();
		serverListDao.reload();
		map.put("rc", 1000);
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}
	
	/**
	 * 电信获取授权码
	 * @return
	 */
	@RequestMapping(value = "/webApi/getDianxinToken.do", method = RequestMethod.POST)
	public ModelAndView getDianxinToken() {
		LOG.info("/webApi/getDianxinToken.do");
		return new ModelAndView();
	}
	
	/**
	 * 验证用户角色是否存在
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/webApi/verifyUser.do")
	public ModelAndView verifyUser(HttpServletRequest req) {
		String partnerUserId = req.getParameter("partnerUserId");
		String partnerId = req.getParameter("partnerId");
		String serverId = req.getParameter("serverId");
		String timestamp = req.getParameter("timestamp");
		String sign = req.getParameter("sign");
		
		if (StringUtils.isBlank(partnerUserId) || StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId)
				|| StringUtils.isBlank(timestamp) || StringUtils.isBlank(sign)) {
			throw new ServiceException(WebApiCode.PARAM_ERROR, "partnerUserId=" + partnerUserId + ",partnerId=" + partnerId 
					+ ", serverId=" + serverId + ", timestamp=" + timestamp + ", sign=" + sign);
		}
		
		String serverSign = EncryptUtil.getMD5((partnerUserId + serverId + timestamp + Config.ins().getSignKey()));
		if (!serverSign.toLowerCase().equals(sign.toLowerCase())) {
			throw new ServiceException(WebApiCode.SIGN_CHECK_ERROR, "签名校验失败");
		}
		
		// 网页支付Apple正版
		if (partnerId.equals("10001"))
			partnerId = "3001";
		
		GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
		String url = "http://" + gameServer.getServerUrl()+":"+gameServer.getApiPort()+ "/gameApi/verifyUser.do";

		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("partnerUserId", partnerUserId);
		paraMap.put("partnerId", partnerId);
		paraMap.put("serverId", serverId);
		
		String jsonStr = UrlRequestUtils.execute(url, paraMap, UrlRequestUtils.Mode.GET);
		Map<String, Object> ret = Json.toObject(jsonStr, Map.class);
		
		ret.put("rc", 1000);
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(ret);
		modelView.setView(view);
		return modelView;
	}
	
	// @RequestMapping("/webApi/addActive.do")
	public ModelAndView addActive() {
		List<ActiveCode> list = this.activeCodeDao.getAllActiveList();
		Map<String, String> map = new HashMap<String, String>();
		
		for (ActiveCode code : list) {
			map.put(code.getCode(), code.getCode());
		}
		
		Map<String, String> addMap = new HashMap<String, String>();
		while (true) {
			String code = getRandomString(6);
			if (!map.containsKey(code)) {
				addMap.put(code, code);
			}
			
			if (addMap.size() >= 100000) {
				break;
			}
		}
		
		Date now = new Date();
		List<ActiveCode> activeList = new ArrayList<ActiveCode>();
		for (String code : addMap.keySet()) {
			ActiveCode active = new ActiveCode();
			active.setCode(code);
			active.setUpdatedTime(now);
			
			activeList.add(active);
			if (activeList.size() >= 1000) {
				this.activeCodeDao.addActive(activeList);
				activeList.clear();
			}
		}
		
		if (activeList.size() != 0) {
			this.activeCodeDao.addActive(activeList);
		}		
		
		Map<String, Integer> viewMap = new HashMap<String, Integer>();
		viewMap.put("rc", 1000);
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}
	
	/**
	 * 生成指定长度的字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijkmnpqrstuvwxyz23456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
}
