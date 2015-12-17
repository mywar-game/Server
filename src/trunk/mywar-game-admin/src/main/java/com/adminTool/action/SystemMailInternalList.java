package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemMailInternal;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.SystemMailInternalService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class SystemMailInternalList extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1820201747663786030L;
	private List<SystemMailInternal> systemMailInternalList = new ArrayList<SystemMailInternal>();
	private Boolean isCommit = false;
	private Integer mailId;
	
	public Boolean getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(Boolean isCommit) {
		this.isCommit = isCommit;
	}

	public Integer getMailId() {
		return mailId;
	}

	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	public List<SystemMailInternal> getSystemMailInternalList() {
		return systemMailInternalList;
	}

	public void setSystemMailInternalList(
			List<SystemMailInternal> systemMailInternalList) {
		this.systemMailInternalList = systemMailInternalList;
	}

	@Override
	public String execute() {
		SystemMailInternalService service = ServiceCacheFactory.getServiceCache().getService(SystemMailInternalService.class);
		if (isCommit) {
			systemMailInternalList = service.findByMailId(mailId);
			ToolTypeConstant typeConstant = new ToolTypeConstant();
			if (systemMailInternalList != null) {
				for (SystemMailInternal s : systemMailInternalList) {
					if (s.getTools() != null) {
						String show = typeConstant.getToolTypeAndName(s.getTools());
						s.setToolsDesc(show);
					}
				}
			}
			return INPUT;
		} else {
			systemMailInternalList = service.findAll();
			ToolTypeConstant typeConstant = new ToolTypeConstant();
			if (systemMailInternalList != null) {
				for (SystemMailInternal s : systemMailInternalList) {
					if (s.getTools() != null) {
						String show = typeConstant.getToolTypeAndName(s.getTools());
						s.setToolsDesc(show);
					}
				}
			}
			return SUCCESS;
		}
	}
}
