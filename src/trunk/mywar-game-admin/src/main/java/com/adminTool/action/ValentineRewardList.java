package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.ValentineReward;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.ValentineRewardService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

public class ValentineRewardList  extends ALDAdminPageActionSupport{
	private static final long serialVersionUID = 6530894885296718115L;
	private String isCommit = "F";

	private List<ValentineReward>list;
	

	public String execute(){
		ValentineRewardService service=ServiceCacheFactory.getServiceCache()
				.getService(ValentineRewardService.class);
		IPage<ValentineReward>iPage=service.findValentineRewardPageList(super.getToPage(),ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if(iPage==null){
			list=new ArrayList<ValentineReward>();
			return SUCCESS;
		}
		ToolTypeConstant typeConstant = new ToolTypeConstant();
		list=(List<ValentineReward>)iPage.getData();
		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		if(list!=null){
			for(ValentineReward s:list){
				String show =typeConstant.getToolTypeAndName(s.getRewards());
				s.setRewardss(show);
			}
		}
		return SUCCESS;
	}
	public List<ValentineReward> getList() {
		return list;
	}

	public void setList(List<ValentineReward> list) {
		this.list = list;
	}
	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
