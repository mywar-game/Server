package com.adminTool.action;

import java.util.List;
import net.sf.json.JSONObject;

import com.adminTool.bo.RefreshClass;
import com.adminTool.service.RefreshClassService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * reload 常量类
 * @author Administrator
 *
 */
public class RefreshClassList extends ALDAdminActionSupport {

	private static final long serialVersionUID = -3020943409640953866L;
	
	private List<RefreshClass> refreshClassList;
	
	@Override
	public String execute() {
		
		RefreshClassService service = ServiceCacheFactory.getServiceCache().getService(RefreshClassService.class); 
		refreshClassList = service.findAll();
		return SUCCESS;
	}
	
	public List<RefreshClass> getRefreshClassList() {
		return refreshClassList;
	}

	public void setRefreshClassList(List<RefreshClass> refreshClassList) {
		this.refreshClassList = refreshClassList;
	}
}
