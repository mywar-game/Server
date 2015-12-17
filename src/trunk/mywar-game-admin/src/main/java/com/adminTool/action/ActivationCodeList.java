package com.adminTool.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.admin.util.DTools;
import com.adminTool.bo.PlatformConstant;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.service.PlatformConstantService;
import com.dataconfig.bo.AActivationCode;
import com.dataconfig.constant.ReflashCacheConstant;
import com.dataconfig.msgbody.ResReflashCache;
import com.dataconfig.service.ActivationCodeService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.MD5;
import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.manager.SequenseManager;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.manager.DataSourceManager;

public class ActivationCodeList extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = 9139610073963854371L;
	
	private String isCommit = "F";
	
	/** 选中的要生成激活码的服务器id **/
	private String serverIds;
	
	/** 激活码的有效时间 **/
	private Date effectiveTime;
	
	/** 生成激活码的个数 **/
	private Integer generateNum;
	
	/** 渠道 **/
	private String platform = "";
	
	/** 奖励 **/
	private String rewardInfo = "";
	
	private Map<Integer, String> platformMap;
	
	public String execute() {
		if ("F".equals(isCommit)) {
			return SUCCESS;
		}
		if (DTools.isEmpty(serverIds)) {
			super.setErroDescrip("请选择服务器！");
			return SUCCESS;
		}
		ActivationCodeService activationCodeService = ServiceCacheFactory.getServiceCache().getService(ActivationCodeService.class);
		Date now = new Date();
//		BASE64Encoder encoder = new BASE64Encoder();
		for (String serverId : serverIds.split(",")) {
			CustomerContextHolder.setSystemNum(Integer.valueOf(serverId.trim()));
			List<AActivationCode> list = new ArrayList<AActivationCode>();
			for (int i = 0; i < generateNum; i++) {
				AActivationCode activationCode = new AActivationCode();
				long id = SequenseManager.getInstance().generateStaticseq();
				String str = MD5.md5Of32(id+"").toUpperCase();
//				System.out.println(id + "----" + str);
//				String base64 = encoder.encodeBuffer((str+"&"+md5Str).getBytes());
				activationCode.setActivationCode(str);
				activationCode.setCreateTime(new Timestamp(now.getTime()));
				activationCode.setEffectiveTime(new Timestamp(effectiveTime.getTime()));
				activationCode.setPlatform(platform.trim());
				activationCode.setReward(rewardInfo.trim());
				list.add(activationCode);
			}
			activationCodeService.save(list);
		}
		super.setErroDescrip("生成邀请码成功！");
		
		for (String serverId : serverIds.split(",")) {
			CustomerContextHolder.setSystemNum(Integer.valueOf(serverId.trim()));
			ResReflashCache resReflashCache = new ResReflashCache(); 
			resReflashCache.setCacheType(ReflashCacheConstant.REFLASH_TYPE_ACTIVATIONCODE); 
			Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.REFLASH_CACHE, resReflashCache, CommomMsgBody.class);
			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
				CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
				super.setErroCodeNum(commomMsgBody.getErrorCode());
				super.setErroDescrip(super.getErroDescrip()+ "但是" +DataSourceManager.getInstatnce().getGameServerMap().get(Integer.valueOf(serverId)).getServerAlias() + commomMsgBody.getErrorDescription()+" 刷新缓存失败！");
			}
		}
		
		this.savePlatform();
		
		return SUCCESS;
	}

	/** 如果此渠道不存在，保存之 */
	private void savePlatform(){
		try {
			PlatformConstantService platformConstantService = ServiceCacheFactory.getServiceCache().getService(PlatformConstantService.class);
			platformMap = platformConstantService.findPlatformMapByCondition(null, URLDecoder.decode(platform, "UTF-8").trim());
			if (platformMap == null || platformMap.size() == 0) {
				platformConstantService.save(new PlatformConstant(platform.trim()));
			}
		} catch (Exception e) {
			LogSystem.error(e, "savePlatform");
		}
	}
	
	public String findPlatformMapByCondition() throws Exception {
		PlatformConstantService platformConstantService = ServiceCacheFactory.getServiceCache().getService(PlatformConstantService.class);
		platformMap = platformConstantService.findPlatformMapByCondition(null, URLDecoder.decode(platform, "UTF-8").trim());
		return "find";
	}
	
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}

	public String getServerIds() {
		return serverIds;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setGenerateNum(Integer generateNum) {
		this.generateNum = generateNum;
	}

	public Integer getGenerateNum() {
		return generateNum;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPlatform() {
		return platform;
	}

	/**
	 * 获取 platformMap 
	 */
	public Map<Integer, String> getPlatformMap() {
		return platformMap;
	}

	/**
	 * 设置 platformMap 
	 */
	public void setPlatformMap(Map<Integer, String> platformMap) {
		this.platformMap = platformMap;
	}

	/**
	 * 获取 奖励 
	 */
	public String getRewardInfo() {
		return rewardInfo;
	}

	/**
	 * 设置 奖励 
	 */
	public void setRewardInfo(String rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

}
