package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.IntegralMall;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.IntegralMallService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class IntegralMallList extends ALDAdminPageActionSupport {
	private static final long serialVersionUID = -3931277220435901304L;
	private List<IntegralMall> integralMallList;
	
	public List<IntegralMall> getIntegralMallList() {
		return integralMallList;
	}

	public void setIntegralMalllist(List<IntegralMall> integralMallList) {
		this.integralMallList = integralMallList;
	}

	
	public String execute(){
		IntegralMallService integralMallService=ServiceCacheFactory.getServiceCache()
				.getService(IntegralMallService.class);
		IPage<IntegralMall>iPage=integralMallService.findIntegralMallPageList(
	               super.getToPage(),ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		ToolTypeConstant typeConstant = new ToolTypeConstant();
		
//			int toolId=i.getTool_id();
//			int toolType=i.getTool_type();
//			if(toolType==9001){
//				i.setToolTypeName("英雄");
//			}else
//			if(toolType==3001){
//				i.setToolTypeName("神器");
//			}else{
//				i.setToolTypeName("道具");
//			}
//			i.setToolTypeName(typeConstant.getToolTypeNameMap().get(
//					toolType));
//		    i.setToolName(typeConstant.getToolName(toolType, toolId));

		
		if(iPage==null){
			integralMallList=new ArrayList<IntegralMall>();
			return SUCCESS;
		}
		integralMallList=(List<IntegralMall>)iPage.getData();
		
//		integralMall.setToolTypeName(typeConstant.getToolTypeNameMap().get(toolType));
//		integralMall.setToolName(typeConstant.getToolName(toolType, toolId));
		for(IntegralMall i:integralMallList){
			 int toolType=i.getTool_type();
	           int toolId=i.getTool_id();
		
			i.setToolName(typeConstant.getToolName(toolType, toolId));
			if(i.getType()==0){
				i.setToolTypeName("英雄");
			}else if(i.getType()==1){
				i.setToolTypeName("神器");
			}else{
				i.setToolTypeName("道具");
			}
		}
		
		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}
	
}
