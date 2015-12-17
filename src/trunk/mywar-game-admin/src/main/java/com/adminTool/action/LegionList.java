package com.adminTool.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.service.UserService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class LegionList extends ALDAdminPageActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	@Override
	public String execute() {
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		List<Object> legionList = userService.findLegionList();
		if(legionList!=null && legionList.size()>0){
			for(int i=0;i<legionList.size();i++){
				Map<String, String> map = new HashMap<String, String>();
				Object[] arr = (Object[])legionList.get(i);
				map.put("id", arr[0].toString());
				map.put("name", arr[1].toString());
				map.put("notice", arr[2].toString());
				map.put("level", arr[3].toString());
				map.put("power", arr[4].toString());
				map.put("contribution", arr[5].toString());
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

}
