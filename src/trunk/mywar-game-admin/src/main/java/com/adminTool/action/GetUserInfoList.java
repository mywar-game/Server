package com.adminTool.action; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.bo.User;
import com.adminTool.bo.UserSomeInfo;
import com.adminTool.service.PartnerService;
import com.adminTool.service.UserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserRegLog;
import com.log.service.UserRegLogService;

public class GetUserInfoList extends ALDAdminActionSupport {

	/**  **/
	private static final long serialVersionUID = 909657051721473295L; 
	
	/** 玩家信息列表 **/
	private List<UserSomeInfo> infoList = new ArrayList<UserSomeInfo>(); 
	
	private String failUserIds = "";

	/**玩家标识**/
	private String lodoId; 
	
	private Map<String, Partner> partnerMap;
	
	private int success;
	
	private int fail;
	
	private int all;

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getFail() {
		return fail;
	}

	public void setFail(int fail) {
		this.fail = fail;
	}
	
	public String getFailUserIds() {
		return failUserIds;
	}

	public void setFailUserIds(String failUserIds) {
		this.failUserIds = failUserIds;
	}

	public String execute() {
		
		if ((lodoId == null || lodoId.equals(""))) {
			return SUCCESS;
		}
		
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class); 
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class); 
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		partnerMap = partnerService.findAllPartnerMap();
		
		String[] userIdArr = lodoId.split(",");
		for (String userId : userIdArr) {
			List<User> list = userService.findUserByLodoIdAndUserName(Integer.valueOf(userId), "");
			if (list == null || list.size() == 0) {
				// 玩家不存在
				UserSomeInfo userSomeInfo = new UserSomeInfo();
				userSomeInfo.setUserId(Integer.valueOf(userId));
				fail ++;
				all ++;
				failUserIds += userId;
				failUserIds += ",";
				continue;
			}
			for (User user : list) {
				UserRegLog userRegLog = userRegLogService.findUserRegLogByUserId(user.getUserId());
				if (userRegLog != null) {
					// 
					String userRegName = userRegLog.getUserName(); // 1 注册账号
					String userName = user.getUserName(); // 2 游戏昵称
					String channel = userRegLog.getChannel(); // 3 渠道
					Integer lodoId = user.getLodoId(); // 4 lodoId
					UserSomeInfo userSomeInfo = new UserSomeInfo();
					userSomeInfo.setUserId(lodoId);
					userSomeInfo.setRegZhanghao(userRegName);
					userSomeInfo.setUserName(userName);
					Partner partner = partnerMap.get(channel);
					if (partner != null) {
						userSomeInfo.setChannel(partnerMap.get(channel).getPName());
					} else {
						userSomeInfo.setChannel("未命名渠道号:" + channel);
					}				
					infoList.add(userSomeInfo);
					success ++;
					all ++;
				}
			}
		}
		
		return SUCCESS; 
	}
	
	public List<UserSomeInfo> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<UserSomeInfo> infoList) {
		this.infoList = infoList;
	}

	public String getLodoId() {
		return lodoId;
	}

	public void setLodoId(String lodoId) {
		this.lodoId = lodoId;
	}
}