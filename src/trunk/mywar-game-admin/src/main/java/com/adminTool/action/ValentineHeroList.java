package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.ValentineHero;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.ValentineHeroService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class ValentineHeroList extends ALDAdminPageActionSupport{
	private static final long serialVersionUID = 6530894885296718425L;
	private String isCommit = "F";
//	private String activityId;
	
//	public String getActivityId() {
//		return activityId;
//	}
//
//	public void setActivityId(String activityId) {
//		this.activityId = activityId;
//	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	private List<ValentineHero>list;
	public List<ValentineHero> getList() {
		return list;
	}
	
	public void setList(List<ValentineHero> list) {
		this.list = list;
	}
	
	public String execute(){
	ValentineHeroService service=ServiceCacheFactory.getServiceCache()
			.getService(ValentineHeroService.class);
//	int activityIdInt=0;
//	if(activityId!=null&&!activityId.equals("")){
//		activityIdInt=Integer.parseInt(activityId);
//	}
	IPage<ValentineHero>iPage=service.findValentineHeroPageList(super.getToPage(),ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
	ToolTypeConstant typeConstant = new ToolTypeConstant();
	
	if(iPage==null){
		list=new ArrayList<ValentineHero>();
		return SUCCESS;
	}
	list=(List<ValentineHero>)iPage.getData();
	for(ValentineHero i:list){
		int toolType=3001;
		int tool_id1=i.getHero_id1();
		int tool_id2=i.getHero_id2();
		i.setName1(typeConstant.getToolName(toolType, tool_id1));
		i.setName2(typeConstant.getToolName(toolType, tool_id2));
	}
	super.setTotalPage(iPage.getTotalPage());
	super.setTotalSize(iPage.getTotalSize());
	
	return SUCCESS;
}
}
