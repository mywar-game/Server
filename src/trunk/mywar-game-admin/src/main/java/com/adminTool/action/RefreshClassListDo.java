package com.adminTool.action;

import java.util.List;

import net.sf.json.JSONObject;

import com.adminTool.bo.RefreshClass;
import com.adminTool.service.RefreshClassService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;

public class RefreshClassListDo extends ALDAdminActionSupport {

	private static final long serialVersionUID = 2188820694127810561L;
	private Boolean isCommit = false;
	private Integer classId = -1;
	private List<RefreshClass> refreshClassList;
	private static final String REQ_URL = "reflashActivity.do";
	
	@Override
	public String execute() {
		
		RefreshClassService service = ServiceCacheFactory.getServiceCache().getService(RefreshClassService.class);
		StringBuilder sb = new StringBuilder();
		sb.append("&className=");
		if (isCommit) {
			refreshClassList = service.findById(classId);
			if (refreshClassList != null && refreshClassList.size() != 0) {
				String className = refreshClassList.get(0).getClassName();
				sb.append(className);
				String response = HttpClientBridge.sendToGameServer(REQ_URL,
						sb.toString(), className);
				
				if (response == null) {
					super.setErroCodeNum(SystemConstant.FAIL_CODE);
					super.setErroDescrip("刷新出现异常, 请查看日志！");
				} else {
					JSONObject jsonObject = JSONObject.fromObject(response);
					if (jsonObject.containsKey(HttpClientBridge.RETURN_RC)
					&& jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_CODE) {
						super.setErroCodeNum(Integer.parseInt(jsonObject.get(
						HttpClientBridge.FAIL).toString()));
						super.setErroDescrip("查询失败！");
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public Boolean getIsCommit() {
		return isCommit;
	}
	public void setIsCommit(Boolean isCommit) {
		this.isCommit = isCommit;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public List<RefreshClass> getRefreshClassList() {
		return refreshClassList;
	}
	public void setRefreshClassList(List<RefreshClass> refreshClassList) {
		this.refreshClassList = refreshClassList;
	}
}
