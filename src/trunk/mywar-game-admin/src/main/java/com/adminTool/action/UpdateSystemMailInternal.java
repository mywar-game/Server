package com.adminTool.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.bo.SystemMailInternal;
import com.adminTool.service.AdminChangeConstantLogService;
import com.adminTool.service.SystemMailInternalService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateSystemMailInternal extends ALDAdminActionSupport {

	private static final long serialVersionUID = -670712373919049268L;

	private Integer internalMailId;
	private String mailTitle;
	private String mailContent;
	private Integer logicType;
	private Integer showType;
	private String tools;
	private String param;
	private String description;
	private List<SystemMailInternal> systemMailInternalList = new ArrayList<SystemMailInternal>();
	private String isUpdate = "F";
	
	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public List<SystemMailInternal> getSystemMailInternalList() {
		return systemMailInternalList;
	}

	public void setSystemMailInternalList(
			List<SystemMailInternal> systemMailInternalList) {
		this.systemMailInternalList = systemMailInternalList;
	}

	public Integer getInternalMailId() {
		return internalMailId;
	}

	public void setInternalMailId(Integer internalMailId) {
		this.internalMailId = internalMailId;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public Integer getLogicType() {
		return logicType;
	}

	public void setLogicType(Integer logicType) {
		this.logicType = logicType;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String execute() {
		
		SystemMailInternal mail = new SystemMailInternal();
		mail.setInternalMailId(internalMailId);
		mail.setMailTitle(mailTitle);
		mail.setMailContent(mailContent);
		mail.setLogicType(logicType);
		mail.setShowType(showType);
		mail.setTools(tools);
		mail.setParam(param);
		mail.setDescription(description);
		isUpdate = "T";
		SystemMailInternalService service = ServiceCacheFactory.getServiceCache().getService(SystemMailInternalService.class);
		service.update(mail);
		systemMailInternalList = service.findByMailId(internalMailId);
		
		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(Integer.valueOf(super.getAdminUser().getExp1()));
		adminChangeConstantLog.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("SystemMailInternal");
		adminChangeConstantLog.setChangeType(3); 
		adminChangeConstantLog.setChangeDetail("修改 " + super.getText("系统内部邮件id:" + internalMailId + " 邮件标题:" + mailTitle + " 邮件内容:" + mailContent + " 逻辑类型:" + logicType + " 显示类型:" + showType + " 附件道具:" + tools + " 扩展配置参数:" + param + " 描述:" + description + " 记录<br/>"));
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory.getServiceCache().getService(AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		
		return SUCCESS;
	}
}
