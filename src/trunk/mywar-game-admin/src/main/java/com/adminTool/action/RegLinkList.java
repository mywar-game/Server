package com.adminTool.action;

import java.sql.Timestamp;
import java.util.Date;

import com.admin.util.DTools;
import com.adminTool.constant.AdminToolCMDCode;
import com.dataconfig.bo.RegLink;
import com.dataconfig.constant.ReflashCacheConstant;
import com.dataconfig.msgbody.ResReflashCache;
import com.dataconfig.service.RegLinkService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.MD5;
import com.framework.constant.SystemConstant;
import com.framework.manager.SequenseManager;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.manager.DataSourceManager;

public class RegLinkList extends ALDAdminActionSupport {

	/** **/
	private static final long serialVersionUID = 4857761719691658292L;
	
	private String isCommit = "F";
	
	/** 选中的要生成注册链接的服务器id **/
	private String serverIds;

	/** 有效次数 **/
	private Integer numLimit;
	
	/** 不限次数 **/
	private Boolean noLimit;
	
	/** 有效时间 **/
	private Date effectiveTime;
	
	/** 奖励 **/
	private String rewardInfo = "";
	
	public String execute() {
		if ("F".equals(isCommit)) {
			return SUCCESS;
		}
		if (DTools.isEmpty(serverIds)) {
			super.setErroDescrip("请选择服务器！");
			return SUCCESS;
		}
		RegLinkService regLinkService = ServiceCacheFactory.getServiceCache().getService(RegLinkService.class);
		
		RegLink regLink = new RegLink();
		String regLinkId = MD5.md5Of32(""+SequenseManager.getInstance().generateStaticseq()).toUpperCase();
		regLink.setRegLinkId(regLinkId);
		regLink.setCreateTime(new Timestamp(System.currentTimeMillis()));
		regLink.setEffectiveTime(new Timestamp(effectiveTime.getTime()));
		regLink.setEffectiveNum(numLimit);
		regLink.setReward(rewardInfo);
		
		for (String serverId : serverIds.split(",")) {
			CustomerContextHolder.setSystemNum(Integer.valueOf(serverId.trim()));
			regLinkService.save(regLink);
		}
		super.setErroDescrip("生成注册链接成功！");
		
		for (String serverId : serverIds.split(",")) {
			CustomerContextHolder.setSystemNum(Integer.valueOf(serverId.trim()));
			ResReflashCache resReflashCache = new ResReflashCache(); 
			resReflashCache.setCacheType(ReflashCacheConstant.REFLASH_TYPE_REG_LINK); 
			Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.REFLASH_CACHE, resReflashCache, CommomMsgBody.class);
			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
				CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
				super.setErroCodeNum(commomMsgBody.getErrorCode());
				super.setErroDescrip(super.getErroDescrip()+ "但是" +DataSourceManager.getInstatnce().getGameServerMap().get(Integer.valueOf(serverId)).getServerAlias() + commomMsgBody.getErrorDescription()+" 刷新缓存失败！");
			}
		}
		return SUCCESS;
	}

	public void setNumLimit(Integer numLimit) {
		this.numLimit = numLimit;
	}

	public Integer getNumLimit() {
		return numLimit;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}

	public String getServerIds() {
		return serverIds;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setNoLimit(Boolean noLimit) {
		this.noLimit = noLimit;
	}

	public Boolean getNoLimit() {
		return noLimit;
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
