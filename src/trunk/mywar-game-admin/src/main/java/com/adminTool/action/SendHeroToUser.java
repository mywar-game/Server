package com.adminTool.action;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.adminTool.bo.AdminIssueLog;
import com.adminTool.bo.User;
import com.adminTool.constant.AdminToolConstant;
import com.adminTool.service.AdminIssueLogService;
import com.adminTool.service.UserService;
import com.dataconfig.service.SystemHeroService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bean.MailAttach;

/**
 * 给玩家发送英雄
 * 
 * @author yezp
 */
public class SendHeroToUser extends ALDAdminActionSupport {

	private static final long serialVersionUID = 5323397514040823658L;
	private static final String REQ_URL = "addHeros.do";

	private String isCommit = "F";

	private Map<Integer, String> heroIdNameMap;

	private String nameStr;

	// 群发
	private int allUser = 2;
	private String addHeros;

	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public String execute() {
		SystemHeroService systemService = ServiceCacheFactory.getServiceCache()
				.getService(SystemHeroService.class);
		heroIdNameMap = systemService.getHeroNameMap();

		if ("F".equals(isCommit)) {
			super.setErroDescrip("");
			return INPUT;
		}

		int receiveUserType = 1;// 单人
		StringBuffer receiveUser = new StringBuffer();
		
		StringBuilder params = new StringBuilder();
		StringBuilder md5str = new StringBuilder();
		StringBuilder userIdStr = new StringBuilder();		
		UserService userService = ServiceCacheFactory.getServiceCache()
				.getService(UserService.class);
		
		// 全服
		if (allUser == AdminToolConstant.SERVER) {
			params.append("&isAllUser=").append(AdminToolConstant.SERVER);
			params.append("&userIdStr=").append(AdminToolConstant.SERVER);
			params.append("&content=").append(URLEncoder.encode(addHeros));
			
			md5str.append(AdminToolConstant.SERVER).append(AdminToolConstant.SERVER)
				.append(URLEncoder.encode(addHeros));
			receiveUser.append("全服");
			receiveUserType = 3;
		} else {
			String[] lodoIdArr = nameStr.split(",");
			for (String lodoId : lodoIdArr) {
				User user = userService.findUserByLodoId(Integer.parseInt(lodoId));
				if (user == null) {
					super.setErroDescrip("用户标识为" + lodoId + "的玩家不存在，发放失败！");
					return ERROR;
				}
				
				userIdStr.append(user.getUserId()).append(",");
				if (receiveUser.length() == 0) {
					receiveUser.append(user.getUserId() + "_" + user.getUserName());
				} else {
					receiveUser.append(",").append(user.getUserId() + "_" + user.getUserName());
				}				
			}
			
			String userIds = userIdStr.toString();
			params.append("&isAllUser=").append(AdminToolConstant.USER);
			params.append("&userIdStr=").append(userIds.substring(0, userIds.length() - 1));
			params.append("&content=").append(URLEncoder.encode(addHeros));
			md5str.append(AdminToolConstant.USER).append(userIds.substring(0, userIds.length() - 1))
				.append(URLEncoder.encode(addHeros));			
			
			if (lodoIdArr.length > 1) {
				receiveUserType = 2; // 群发 
			}			
		}
		
		StringBuilder errdesc = new StringBuilder();
		String response = HttpClientBridge.sendToGameServer(REQ_URL, 
				params.toString(), md5str.toString());
		
		if (response == null || response.equals("")) {
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("发送出现异常，请查看日志！");
			return ERROR;
		} else {
			JSONObject jsonObject = JSONObject.fromObject(response);
			if (jsonObject.containsKey(HttpClientBridge.RETURN_RC) 
					&& jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_SUCCESS_CODE) {
				super.setErroCodeNum(Integer.parseInt(jsonObject.get(
						HttpClientBridge.FAIL).toString()));
				super.setErroDescrip("发送失败！");
				return ERROR;
			} else {
				JSONObject object = (JSONObject) jsonObject.get(HttpClientBridge.SUCCESS);
				if (object == null || object.size() == 0) { // 全部发送成功
					super.setErroDescrip("发放成功！");
				} else {
					Iterator it = object.keys();
					while (it.hasNext()) {
						StringBuilder builder = new StringBuilder();
						String key = it.next().toString();
						builder.append("玩家" + key + "的"); 
						List<MailAttach> heroList = JSONArray.toList(
								(JSONArray) object.get(key), MailAttach.class);
						
						StringBuilder heros = new StringBuilder();
						for (MailAttach sendHero : heroList) {
							heros.append(heroIdNameMap.get(sendHero.getAttachId()) 
									+ "*" + sendHero.getAttachNum()).append(",");
						}
						
						if (heros.length() != 0) 
							builder.append("英雄:").append(heros);
						
						errdesc.append(builder.deleteCharAt(builder.length() - 1)).append("\\r\\n");
					}
					errdesc.append("没有发送成功");
					super.setErroCodeNum(SystemConstant.FAIL_CODE);
					super.setErroDescrip(errdesc.toString());
				}				
			}			
		}
		
		AdminIssueLogService adminIssueLogService = ServiceCacheFactory
				.getServiceCache().getService(AdminIssueLogService.class);
		AdminIssueLog adminIssueLog = new AdminIssueLog();
		adminIssueLog.setSysNum(CustomerContextHolder.getSystemNum());
		adminIssueLog.setAdminName(this.getAdminUser().getAdminName());
		adminIssueLog.setIssueTime(new Timestamp(System.currentTimeMillis()));
		adminIssueLog.setReceiveUser(receiveUser.toString());
		adminIssueLog.setFailUser(errdesc.toString());
		adminIssueLog.setReceiveUserType(receiveUserType);
		adminIssueLog.setMailAttach(addHeros);
		adminIssueLog.setTheme("");
		adminIssueLog.setContent("");
		adminIssueLog.setIssueReason("");
		adminIssueLogService.saveAdminIssueLog(adminIssueLog);
		return SUCCESS;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Map<Integer, String> getHeroIdNameMap() {
		return heroIdNameMap;
	}

	public void setHeroIdNameMap(Map<Integer, String> heroIdNameMap) {
		this.heroIdNameMap = heroIdNameMap;
	}

	public String getNameStr() {
		return nameStr;
	}

	public void setNameStr(String nameStr) {
		this.nameStr = nameStr;
	}

	public int getAllUser() {
		return allUser;
	}

	public void setAllUser(int allUser) {
		this.allUser = allUser;
	}

	public String getAddHeros() {
		return addHeros;
	}

	public void setAddHeros(String addHeros) {
		this.addHeros = addHeros;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
