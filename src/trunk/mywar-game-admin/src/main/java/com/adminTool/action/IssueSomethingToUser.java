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
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bean.MailAttach;

public class IssueSomethingToUser extends ALDAdminActionSupport {

	private static final long serialVersionUID = 6719875948365378672L;
	private static final String REQ_URL = "addEquipmentOrTools.do";

	/** 邮件标题 **/
	private String theme;

	/** 邮件内容 **/
	private String content;

	/** 角色名字符串 **/
	private String nameStr;

	private int allUser = 2;// 群发

	private String mailAttach;

	private String issueReason;

	private Map<Integer, String> equipmentIDNameMap;

	private Map<Integer, String> treasureIDNameMap;

	private String isCommit = "F";

	@SuppressWarnings({ "deprecation", "unchecked" })
	public String execute() {
		EEquipmentConstantService equipmentConstantService = ServiceCacheFactory
				.getServiceCache().getService(EEquipmentConstantService.class);
		TTreasureConstantService treasureConstantService = ServiceCacheFactory
				.getServiceCache().getService(TTreasureConstantService.class);
		AdminIssueLogService adminIssueLogService = ServiceCacheFactory
				.getServiceCache().getService(AdminIssueLogService.class);

		StringBuffer receiveUser = new StringBuffer();
		int receiveUserType = 1;// 单人
		equipmentIDNameMap = equipmentConstantService.findEquipmentIDNameMap();
		treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();

		if ("F".equals(isCommit)) {
			super.setErroDescrip("");
			return INPUT;
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		StringBuilder userIdStr = new StringBuilder();
		UserService userService = ServiceCacheFactory.getServiceCache()
				.getService(UserService.class);

		// 发全服
		if (allUser == AdminToolConstant.SERVER) {// 全服
			sb.append("&isAllUser=").append(AdminToolConstant.SERVER);
			sb.append("&userIdStr=").append(AdminToolConstant.SERVER);
			sb.append("&content=").append(URLEncoder.encode(mailAttach));
			sb1.append(AdminToolConstant.SERVER).append(AdminToolConstant.SERVER)
					.append(URLEncoder.encode(mailAttach));
			receiveUser.append("全服");
			receiveUserType = 3;// 全服
		} else {
			String[] names = nameStr.split(",");
			for (String name : names) {
				User user = userService
						.findUserByLodoId(Integer.parseInt(name));
				if (user == null) {
					super.setErroDescrip("用户标识为" + name + "的玩家不存在，发放失败！");
					return ERROR;
				}
				userIdStr.append(user.getUserId()).append(",");
				if (receiveUser.length() == 0) {
					receiveUser.append(user.getUserId() + "_"
							+ user.getUserName());
				} else {
					receiveUser.append(",").append(
							user.getUserId() + "_" + user.getUserName());
				}
			}
			String userIds = userIdStr.toString();
			sb.append("&isAllUser=").append(AdminToolConstant.USER);
			sb.append("&userIdStr=").append(
					userIds.substring(0, userIds.length() - 1));
			sb.append("&content=").append(URLEncoder.encode(mailAttach));
			sb1.append(AdminToolConstant.USER)
					.append(userIds.substring(0, userIds.length() - 1))
					.append(URLEncoder.encode(mailAttach));
			if (names.length > 1) {
				receiveUserType = 2;// 群发
			}
		}
		// System.out.println("mailAttach == "+ mailAttach);
		StringBuilder errdesc = new StringBuilder();
		String response = HttpClientBridge.sendToGameServer(REQ_URL,
				sb.toString(), sb1.toString());
		if (response == null || response.equals("")) {
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("发送出现异常,请查看日志！");
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
				if (object == null || object.size() == 0) {// 全部发送成功
					super.setErroDescrip("发放成功！");
				} else {
					@SuppressWarnings("rawtypes")
					Iterator it = object.keys();
					while (it.hasNext()) {
						StringBuilder builder = new StringBuilder();
						String key = it.next().toString();
						builder.append("玩家" + key + "的");
						List<MailAttach> attachList = JSONArray.toList(
								(JSONArray) object.get(key), MailAttach.class);
						StringBuilder equip = new StringBuilder();
						StringBuilder trea = new StringBuilder();
						for (MailAttach attach : attachList) {
							if (attach.getAttachType() == 1) {// 道具
								trea.append(treasureIDNameMap.get(attach.getAttachId()) + "*"
												+ attach.getAttachNum()).append(",");
							} else if (attach.getAttachType() == 2) {// 装备
								equip.append(equipmentIDNameMap.get(attach.getAttachId())+ "*"
												+ attach.getAttachNum()).append(",");
							}
						}
						if (equip.length() != 0) {
							builder.append("装备:").append(equip);
						}
						if (trea.length() != 0) {
							builder.append("道具:").append(trea);
						}
						errdesc.append(builder.deleteCharAt(builder.length() - 1)).append("\\r\\n");
					}
					errdesc.append("没有发送成功");
					super.setErroCodeNum(SystemConstant.FAIL_CODE);
					super.setErroDescrip(errdesc.toString());
				}
			}
		}
		AdminIssueLog adminIssueLog = new AdminIssueLog();
		adminIssueLog.setSysNum(CustomerContextHolder.getSystemNum());
		adminIssueLog.setAdminName(this.getAdminUser().getAdminName());
		adminIssueLog.setIssueTime(new Timestamp(System.currentTimeMillis()));
		adminIssueLog.setReceiveUser(receiveUser.toString());
		adminIssueLog.setFailUser(errdesc.toString());
		adminIssueLog.setReceiveUserType(receiveUserType);
		adminIssueLog.setMailAttach(mailAttach);
		// adminIssueLog.setTheme(theme);
		// adminIssueLog.setContent(content);
		adminIssueLog.setTheme("");
		adminIssueLog.setContent("");
		adminIssueLog.setIssueReason(issueReason);
		adminIssueLogService.saveAdminIssueLog(adminIssueLog);
		return SUCCESS;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNameStr() {
		return nameStr;
	}

	public void setNameStr(String nameStr) {
		this.nameStr = nameStr;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setMailAttach(String mailAttach) {
		this.mailAttach = mailAttach;
	}

	public String getMailAttach() {
		return mailAttach;
	}

	public void setEquipmentIDNameMap(Map<Integer, String> equipmentIDNameMap) {
		this.equipmentIDNameMap = equipmentIDNameMap;
	}

	public Map<Integer, String> getEquipmentIDNameMap() {
		return equipmentIDNameMap;
	}

	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}

	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	public void setAllUser(int allUser) {
		this.allUser = allUser;
	}

	public int getAllUser() {
		return allUser;
	}

	public void setIssueReason(String issueReason) {
		this.issueReason = issueReason;
	}

	public String getIssueReason() {
		return issueReason;
	}
}