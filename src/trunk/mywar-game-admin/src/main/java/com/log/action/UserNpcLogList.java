package com.log.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dataconfig.service.BBuildingConstantService;
import com.dataconfig.service.BNpcConstantService;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserNpcLog;
import com.log.service.UserNpcLogService;

public class UserNpcLogList extends ALDAdminLogPageActionSupport {

	private static final long serialVersionUID = -2680861807960864181L;

	private List<UserNpcLog> userNpcLogList = new ArrayList<UserNpcLog>();
	
	@Override
	public String execute() throws Exception {
		UserNpcLogService userNpcLogService = ServiceCacheFactory.getServiceCache().getService(UserNpcLogService.class);
		BBuildingConstantService buildingConstantService = ServiceCacheFactory.getServiceCache().getService(BBuildingConstantService.class);
		BNpcConstantService npcConstantService = ServiceCacheFactory.getServiceCache().getService(BNpcConstantService.class);
	
		
		String searchUserId = super.searchUser();
		//搜索玩家的时候出错了，返回
		if (super.getErroDescrip() != null) {
			return SUCCESS;
		}
		
		IPage<UserNpcLog> list = userNpcLogService.findUserNpcLogListByCondition(searchUserId, super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			Map<Integer, String> buildingIDNameMap = buildingConstantService.findBuildingIDNameMap();
			Map<Integer, String> npcIdAndNameMap = npcConstantService.findNpcIdAndNameMap();
			userNpcLogList = (List<UserNpcLog>) list.getData();
			for (UserNpcLog userNpcLog : userNpcLogList) {
				userNpcLog.setBuildingName(buildingIDNameMap.get(userNpcLog.getBuildingId()));
				userNpcLog.setNpcName(npcIdAndNameMap.get(userNpcLog.getNpcId()));
			}
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
		}
		
		return SUCCESS;
	}

	public List<UserNpcLog> getUserNpcLogList() {
		return userNpcLogList;
	}
	
	public void setUserNpcLogList(List<UserNpcLog> userNpcLogList) {
		this.userNpcLogList = userNpcLogList;
	}
	
}