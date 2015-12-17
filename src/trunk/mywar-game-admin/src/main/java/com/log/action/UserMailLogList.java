package com.log.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.dataconfig.service.EEquipmentConstantService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserMailLog;
import com.log.service.UserMailChangeLogService;
import com.log.service.UserMailLogService;

public class UserMailLogList extends ALDAdminLogPageActionSupport {

	/** **/
	private static final long serialVersionUID = 5136927014968089739L;
	
	/** **/
	private List<UserMailLog> userMailLogList = new ArrayList<UserMailLog>();
	
	private Map<Integer, String> treasureIDNameMap;
	
	private Map<Integer, String> equipmentIdNameMap;
	
	private boolean systemSend = false;
	
	private String searchSenderName;
	
	private String searchReceiveName;
	
	@Override
	public String execute() throws Exception {
		UserMailLogService userMailLogService = ServiceCacheFactory.getServiceCache().getService(UserMailLogService.class);
		UserMailChangeLogService userMailChangeLogService = ServiceCacheFactory.getServiceCache().getService(UserMailChangeLogService.class);
		
		String searchUserId = "";
		int searchType = 0;
		if (systemSend) {
			searchType = 1;
			searchUserId = "-1";
		} else {
			if (!Tools.isEmpty(searchSenderName)) {
				searchType = 2;
				super.setName(searchSenderName);
				searchUserId = super.searchUser();
			}
			if (!Tools.isEmpty(searchReceiveName)) {
				searchType = 3;
				super.setName(searchReceiveName);
				searchUserId = super.searchUser();
			}
		}
		//搜索玩家的时候出错了，返回
		if (super.getErroDescrip() != null) {
			return SUCCESS;
		}
		
		IPage<UserMailLog> list = userMailLogService.getUserMailLogList(searchType, searchUserId, super.getToPage(), super.getPageSize());
		if (list != null) {
			userMailLogList = (List<UserMailLog>)list.getData();
			//附件可能会用到的常量
			TTreasureConstantService treasureConstantService = ServiceCacheFactory.getServiceCache().getService(TTreasureConstantService.class);
			treasureIDNameMap = treasureConstantService.findTreasureIdNameMap();
			EEquipmentConstantService equipmentConstantService = ServiceCacheFactory.getServiceCache().getService(EEquipmentConstantService.class);
			equipmentIdNameMap = equipmentConstantService.findEquipmentIDNameMap();
			//邮件变动用
			StringBuffer userMailIds = new StringBuffer();
			if (userMailLogList != null && userMailLogList.size() > 0) {
				for (int i = 0; i < userMailLogList.size(); i++) {
					if (i == 0) {
						userMailIds.append(userMailLogList.get(i).getUserMailId());
					} else {
						userMailIds.append(",").append(userMailLogList.get(i).getUserMailId());
					}
					//解析附件
					if (!Tools.isEmpty(userMailLogList.get(i).getMailAttach())) {
						UserMailLog userMailLog = userMailLogList.get(i);
						if (userMailLog.getMailType() != 2) {
							userMailLog.setMailAttachList(userMailLogService.parseMailAttach(userMailLog.getMailAttach()));
						}
					}
				}
				//查找对应邮件的变动，并将领取奖励时间、删除时间设置好
				Map<Long, Map<Integer, String>> changeMap = userMailChangeLogService.findMapByUserMailIds(userMailIds.toString());
				if (changeMap != null && changeMap.size() > 0) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
					for (int i = 0; i < userMailLogList.size(); i++) {
						UserMailLog userMailLog = userMailLogList.get(i);
						Map<Integer, String> typeAndTimeMap = changeMap.get(userMailLog.getUserMailId());
						if (typeAndTimeMap == null) {
							continue;
						}
						if (typeAndTimeMap.get(4) != null) {
							userMailLog.setGetAttachTime(sdf.parse(typeAndTimeMap.get(4)));
						}
						if (typeAndTimeMap.get(5) != null) {
							userMailLog.setDelTime(sdf.parse(typeAndTimeMap.get(5)));
						}
						if (typeAndTimeMap.get(6) != null) {
							userMailLog.setDelTime(sdf.parse(typeAndTimeMap.get(6)));
						}
					}
				}
			}
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		return SUCCESS;
	}

	public List<UserMailLog> getUserMailLogList() {
		return userMailLogList;
	}

	public void setUserMailLogList(List<UserMailLog> userMailLogList) {
		this.userMailLogList = userMailLogList;
	}

	public void setTreasureIDNameMap(Map<Integer, String> treasureIDNameMap) {
		this.treasureIDNameMap = treasureIDNameMap;
	}

	public Map<Integer, String> getTreasureIDNameMap() {
		return treasureIDNameMap;
	}

	public void setEquipmentIdNameMap(Map<Integer, String> equipmentIdNameMap) {
		this.equipmentIdNameMap = equipmentIdNameMap;
	}

	public Map<Integer, String> getEquipmentIdNameMap() {
		return equipmentIdNameMap;
	}

	public void setSystemSend(boolean systemSend) {
		this.systemSend = systemSend;
	}

	public boolean isSystemSend() {
		return systemSend;
	}

	public void setSearchSenderName(String searchSenderName) {
		this.searchSenderName = searchSenderName;
	}

	public String getSearchSenderName() {
		return searchSenderName;
	}

	public void setSearchReceiveName(String searchReceiveName) {
		this.searchReceiveName = searchReceiveName;
	}

	public String getSearchReceiveName() {
		return searchReceiveName;
	}

}
