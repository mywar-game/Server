package com.adminTool.action;

import com.adminTool.bo.IntegralMall;
import com.adminTool.service.IntegralMallService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddIntegralMall extends ALDAdminPageActionSupport implements
		ModelDriven<IntegralMall> {
	private static final long serialVersionUID = 4368549591256367993L;
	private String isCommit = "F";
	private IntegralMall integralMall = new IntegralMall();
	
	private String toolTypeArr;
	private String toolIdArr;
	private String nameArr;
	private String numArr;

	public String getToolTypeArr() {
		return toolTypeArr;
	}

	public void setToolTypeArr(String toolTypeArr) {
		this.toolTypeArr = toolTypeArr;
	}

	public String getToolIdArr() {
		return toolIdArr;
	}

	public void setToolIdArr(String toolIdArr) {
		this.toolIdArr = toolIdArr;
	}

	public String getNameArr() {
		return nameArr;
	}

	public void setNameArr(String nameArr) {
		this.nameArr = nameArr;
	}

	public String getNumArr() {
		return numArr;
	}

	public void setNumArr(String numArr) {
		this.numArr = numArr;
	}

	public String execute() {
		IntegralMallService service = ServiceCacheFactory.getServiceCache()
				.getService(IntegralMallService.class);

		if (isCommit.equals("F")) {
			IntegralMall integralMall2=service.findLastIntegralMall();
			if(integralMall2==null){
				integralMall.setMall_id(1);
			}else{
				integralMall.setMall_id(integralMall2.getMall_id()+1);
			}
					return INPUT;
		}
			int num=Integer.parseInt(numArr);
			integralMall.setNum(num);
			int tool_type=Integer.parseInt(toolTypeArr);
			integralMall.setTool_type(tool_type);
			int tool_id=Integer.parseInt(toolIdArr);
			integralMall.setTool_id(tool_id);
//			int type=Integer.parseInt(nameArr);
//			integralMall.setType(type);
		service.addIntegralMall(integralMall);
		return SUCCESS;
	}
	
	@Override
	public IntegralMall getModel() {
		return integralMall;
	}
	
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
