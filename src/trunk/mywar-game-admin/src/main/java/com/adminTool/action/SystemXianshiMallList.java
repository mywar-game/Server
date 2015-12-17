package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemXianshiMall;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.SystemXianshiMallService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class SystemXianshiMallList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 1L;
	private List<SystemXianshiMall> list;
	private int mallId = -1;
	private int activityId = -1;

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getMallId() {
		return mallId;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
	}

	@Override
	public String execute() {
		
		SystemXianshiMallService service = ServiceCacheFactory.getServiceCache().getService(SystemXianshiMallService.class);
		IPage<SystemXianshiMall>iPage=service.findSystemXianshiMallPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		
		
		if (mallId != -1) {
			list = service.getById(activityId, mallId);
			return INPUT;
		} 
//			else {
//			list = service.getList(activityId);
//		}
//		if(iPage==null){
//			list=new ArrayList<SystemXianshiMall>();
//			return INPUT;
//		}
		list=(List<SystemXianshiMall>)iPage.getData();

		ToolTypeConstant typeConstant = new ToolTypeConstant();
		
		
		if (list != null) {
			for (SystemXianshiMall s : list) {				
				switch(s.getType()){
				case 1:s.setDayType2("1-2天");
				break;
				case 2: s.setDayType2("3-4天");
				break;
				case 3:s.setDayType2("5-7天");
				break;
				}
				switch(s.getSubType()){
				case 1:s.setToolType2("尊贵宝库");
				break;
				case 2:s.setToolType2("华丽宝库");
				break;
				case 3:s.setToolType2("英雄宝库");
				break;
				}
				String show = typeConstant.getToolTypeAndName(s.getToolType() + "," + s.getToolId() + "," + 1);
				s.setDesc(show);
			}
		}
		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());		 
		return SUCCESS;
	}
	
	public List<SystemXianshiMall> getList() {
		return list;
	}

	public void setList(List<SystemXianshiMall> list) {
		this.list = list;
	}

}
