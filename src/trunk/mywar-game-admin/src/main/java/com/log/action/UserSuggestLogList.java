package com.log.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.admin.util.DTools;
import com.adminTool.bean.UserMail;
import com.adminTool.bo.AdminDealSuggestLog;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqSendSystemMail;
import com.adminTool.service.AdminDealSuggestLogService;
import com.adminTool.service.UserService;
import com.dataconfig.bo.SParamConfig;
import com.dataconfig.service.SParamConfigService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.IPage;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bean.UserSuggestLogInfo;
import com.log.bo.UserSuggestLog;
import com.log.constant.UserSuggestLogConstant;
import com.log.service.UserSuggestLogService;

public class UserSuggestLogList extends ALDAdminPageActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1726511782449477946L;
	
	private List<UserSuggestLogInfo> userSuggestlist = new ArrayList<UserSuggestLogInfo>();
	
	@Override
	public String execute() throws Exception {
		this.findList();
		return SUCCESS;
	}
	
	public String dealSuggest() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer suggestType = Integer.valueOf(request.getParameter("suggestType"));
		Integer dealType = Integer.valueOf(request.getParameter("dealType"));
		//采纳有奖
		if((suggestType == UserSuggestLogConstant.SUGGEST_TYPE_BUG && dealType != UserSuggestLogConstant.DEAL_BUG_TYPE_IGNORE) 
				|| (suggestType == UserSuggestLogConstant.SUGGEST_TYPE_FEEDBACK && dealType != UserSuggestLogConstant.DEAL_FEEDBACK_TYPE_IGNORE)) {
			Long userId = Long.valueOf(request.getParameter("userId"));
			
			SParamConfigService sParamConfigService = ServiceCacheFactory.getServiceCache().getService(SParamConfigService.class);
			SParamConfig config;
			if (suggestType == UserSuggestLogConstant.SUGGEST_TYPE_BUG) {
				config = sParamConfigService.findOneSParamConfig("BUG_REPORT_REWARDS");
			} else {
				config = sParamConfigService.findOneSParamConfig("FEEDBACK_REWARDS");
			}
			if (config == null) {
				super.setErroDescrip("没有玩家建议处理相关配置！");
				return SUCCESS;
			}
			String theme = config.getValue1();
			if (DTools.isEmpty(theme)) {
				super.setErroDescrip("没有配置奖励玩家建议的邮件主题！");
				return SUCCESS;
			}
			String content = config.getValue2();
			if (DTools.isEmpty(content)) {
				super.setErroDescrip("没有配置奖励玩家建议的邮件内容！");
				return SUCCESS;
			}
			//2:3061:5,1:2:500;2:3061:3,1:2:300;2:3061:1,1:2:100
			//2:3061:3,1:2:300
			String allRewardInfo = config.getValue3();
			if (DTools.isEmpty(allRewardInfo)) {
				super.setErroDescrip("没有配置奖励玩家建议的邮件附件，即具体奖励！");
				return SUCCESS;
			}
			//这种处理类型对应的奖励
			String[] dealTypeRewardArr = allRewardInfo.split(";")[dealType-1].split(","); 
			
			//奖励通知邮件
			ReqSendSystemMail req = new ReqSendSystemMail();
			//邮件附件
			JSONArray attachArray = new JSONArray();
			for (String reward : dealTypeRewardArr) {
				JSONObject attach = new JSONObject();
				attach.put("attachType", Integer.valueOf(reward.split(":")[0]));
				attach.put("attachId", Integer.valueOf(reward.split(":")[1]));
				attach.put("attachNum", Integer.valueOf(reward.split(":")[2]));
				attachArray.put(attach);
			}
			//奖励邮件内容
			UserMail userMail = new UserMail();
			userMail.setSenderUserId(-1);
			userMail.setSenderName("");
			userMail.setUserId(userId);
			userMail.setTheme(MessageFormat.format(theme, URLDecoder.decode(request.getParameter("name"), "UTF-8").trim()));
			userMail.setContent(MessageFormat.format(content, URLDecoder.decode(request.getParameter("name"), "UTF-8").trim()));
			userMail.setSendTime(new Date());
			userMail.setMailAttach(attachArray.toString());
			userMail.setGetAttach(false);
			
			req.addUserMailList(userMail);
			req.setMailAttach(attachArray.toString());
//			System.out.println("mailAttach == "+ attachArray.toString());
			Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.SEND_SYSTEM_MAIL, req, CommomMsgBody.class);
			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
				CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
				super.setErroCodeNum(commomMsgBody.getErrorCode());
				super.setErroDescrip(commomMsgBody.getErrorDescription()+" 发送邮件失败！");
				return SUCCESS;
			}
		}
		//保存处理日志
		AdminDealSuggestLog dealLog = new AdminDealSuggestLog();
		dealLog.setSysNum(CustomerContextHolder.getSystemNum());
		dealLog.setAdminName(this.getAdminUser().getAdminName());
		dealLog.setDealTime(new Timestamp(System.currentTimeMillis()));
		dealLog.setSuggestId(Integer.valueOf(request.getParameter("suggestId")));
		dealLog.setSuggestType(suggestType);
		dealLog.setDealType(dealType);
		AdminDealSuggestLogService adminDealSuggestLogService = ServiceCacheFactory.getServiceCache().getService(AdminDealSuggestLogService.class);
		adminDealSuggestLogService.save(dealLog);
		//重新查询
		this.findList();
		return SUCCESS;
	}
	
	private void findList(){
		UserSuggestLogService userSuggestLogService = ServiceCacheFactory.getServiceCache().getService(UserSuggestLogService.class);
		IPage<UserSuggestLog> list = userSuggestLogService.findSuggestLogPageListByCondition(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			if (list.getData() != null && list.getData().size() > 0) {
				StringBuffer userIds = new StringBuffer();
				StringBuffer suggestIds = new StringBuffer();
				List<UserSuggestLog> userSuggestLogList= (List<UserSuggestLog>)list.getData();
				for (UserSuggestLog userSuggestLog : userSuggestLogList) {
					userIds.append(userSuggestLog.getUserId()).append(",");
					suggestIds.append(userSuggestLog.getId()).append(",");
				}
				
				UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
				Map<String, String> userIdNameMap = userService.findUserIdNameMapByUserIds(userIds.substring(0, userIds.length()-1));
				
				AdminDealSuggestLogService adminDealSuggestLogService = ServiceCacheFactory.getServiceCache().getService(AdminDealSuggestLogService.class);
				Map<Integer, AdminDealSuggestLog> suggestIdAndDAdminDealMap = adminDealSuggestLogService.findUserIdNameMapBySuggestIds(suggestIds.substring(0, suggestIds.length()-1));
				
				for (UserSuggestLog userSuggestLog : userSuggestLogList) {
					UserSuggestLogInfo userSuggestLogInfo = new UserSuggestLogInfo();
					userSuggestLogInfo.setId(userSuggestLog.getId());
					userSuggestLogInfo.setContent(userSuggestLog.getContent());
					userSuggestLogInfo.setSuggestType(userSuggestLog.getSuggestType());
					userSuggestLogInfo.setUserId(userSuggestLog.getUserId());
					userSuggestLogInfo.setCommitTime(userSuggestLog.getCommitTime());
					userSuggestLogInfo.setName(userIdNameMap.get(userSuggestLog.getUserId()));
					if (suggestIdAndDAdminDealMap.containsKey(userSuggestLog.getId())) {
						userSuggestLogInfo.setDealType(suggestIdAndDAdminDealMap.get(userSuggestLog.getId()).getDealType());
						userSuggestLogInfo.setAdminName(suggestIdAndDAdminDealMap.get(userSuggestLog.getId()).getAdminName());
						userSuggestLogInfo.setDealTime(suggestIdAndDAdminDealMap.get(userSuggestLog.getId()).getDealTime());
					}
					userSuggestlist.add(userSuggestLogInfo);
				}
			}
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
	}

	public List<UserSuggestLogInfo> getUserSuggestlist() {
		return userSuggestlist;
	}

	public void setUserSuggestlist(List<UserSuggestLogInfo> userSuggestlist) {
		this.userSuggestlist = userSuggestlist;
	}
	
}
