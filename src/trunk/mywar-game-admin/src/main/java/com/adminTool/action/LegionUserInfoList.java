package com.adminTool.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.service.UserService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class LegionUserInfoList extends ALDAdminPageActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private int legionId;
	@Override
	public String execute() {
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		List<Object> legionList = userService.findUserLegionInfoList(legionId);
		if(legionList!=null && legionList.size()>0){
			for(int i=0;i<legionList.size();i++){
				Map<String, String> map = new HashMap<String, String>();
				Object[] arr = (Object[])legionList.get(i);
				map.put("lodoId", arr[0].toString());
				map.put("name", arr[1].toString());
				map.put("identity", arr[2].toString());
				map.put("contribution", arr[3].toString());
				list.add(map);
			}
		}
		return SUCCESS;
	}
	public List<Map<String, String>> getList() {
		return list;
	}
	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}
	public int getLegionId() {
		return legionId;
	}
	public void setLegionId(int legionId) {
		this.legionId = legionId;
	}

}
