package com.system.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.framework.common.ALDAdminActionSupport;

public class ChangeServer extends ALDAdminActionSupport {

	private static final long serialVersionUID = -3776617067584148808L;

	public void executeChange() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String serverNum = request.getParameter("serverNum") == null ? "" : request.getParameter("serverNum").toString();
		this.getAdminUser().setExp1(serverNum);
//		DataSourceManager.getInstatnce().changeConfigDateSource(serverNum);
//		return SUCCESS;
	}
}
