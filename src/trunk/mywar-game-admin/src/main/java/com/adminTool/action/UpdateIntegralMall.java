package com.adminTool.action;

import com.adminTool.bo.IntegralMall;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.IntegralMallService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateIntegralMall extends ALDAdminPageActionSupport implements
		ModelDriven<IntegralMall> {
	private static final long serialVersionUID = 4398549591256367993L;
	private IntegralMall integralMall = new IntegralMall();
//	private String tools;

	private String isCommit = "F";

	public String execute() {
		IntegralMallService service = ServiceCacheFactory.getServiceCache()
				.getService(IntegralMallService.class);

		if (isCommit.equals("F")) {
			integralMall = service
					.getOneIntegralMall(integralMall.getMall_id());
           int toolType=integralMall.getTool_type();
           int toolId=integralMall.getTool_id();
			
			ToolTypeConstant typeConstant=new ToolTypeConstant();
			integralMall.setToolTypeName(typeConstant.getToolTypeNameMap().get(toolType));
            integralMall.setToolName(typeConstant.getToolName(toolType, toolId));
			return INPUT;
		} else {

			service.updateIntegralMall(integralMall);
			return SUCCESS;
		}
	}

	@Override
	public IntegralMall getModel() {
		return integralMall;
	}

//	public String getTools() {
//		return tools;
//	}
//
//	public void setTools(String tools) {
//		this.tools = tools;
//	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public IntegralMall getIntegralMall() {
		return integralMall;
	}

	public void setIntegralMall(IntegralMall integralMall) {
		this.integralMall = integralMall;
	}
}
