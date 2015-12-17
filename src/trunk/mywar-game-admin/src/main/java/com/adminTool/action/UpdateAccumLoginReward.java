package com.adminTool.action;
/**
 * 累计登录奖励
 */
import java.util.List;

import com.adminTool.bo.SystemAccumLoginReward;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.SystemAccumLoginRewardService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateAccumLoginReward extends ALDAdminPageActionSupport{
	
	private static final long serialVersionUID = 6530894355196718225L;
	private List<SystemAccumLoginReward> SystemAccumLoginRewardList;
	private Integer[] id;
	private Integer[] day;
	private String[] tools;
	private String[] toolsDesc;
	private Integer activityId;
	private String isCommit = "F";
	private String isDelete = "F";
	private Integer did;
	
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public void setTools(String[] tools) {
		this.tools = tools;
	}

	public UpdateAccumLoginReward() {
		super();
	}

	public List<SystemAccumLoginReward> getSystemAccumLoginRewardList() {
		return SystemAccumLoginRewardList;
	}

	public void setSystemAccumLoginRewardList(
			List<SystemAccumLoginReward> systemAccumLoginRewardList) {
		SystemAccumLoginRewardList = systemAccumLoginRewardList;
	}

	public Integer[] getId() {
		return id;
	}

	public void setId(Integer[] id) {
		this.id = id;
	}
	public Integer[] getDay() {
		return day;
	}
	public void setDay(Integer[] day) {
		this.day= day;
	}
	public String[] getTools() {
		return tools;
	}
	public void setToolsId(String[] tools) {
		this.tools = tools;
	}
	public String[] getToolsDesc() {
		return toolsDesc;
	}

	public void setToolsDesc(String[] toolsDesc) {
		this.toolsDesc = toolsDesc;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	@Override
	public String execute() throws Exception {
		SystemAccumLoginRewardService service= ServiceCacheFactory.getServiceCache().getService(SystemAccumLoginRewardService.class);
		if(isCommit.equalsIgnoreCase("T")){
			if (isDelete.equalsIgnoreCase("T")) {
				service.deleteById(did);
			} else {
				service.deleteAll(activityId);
				for(int i=0;i<day.length;i++){
					SystemAccumLoginReward reward=new SystemAccumLoginReward();
					reward.setDay(day[i]);
					if(tools.length>i){
						reward.setTools(tools[i]);
					}
					reward.setActivityId(activityId);
					service.save(reward);
				}
			}
		}
		SystemAccumLoginRewardList=service.findAll(activityId);
		for(SystemAccumLoginReward reward:SystemAccumLoginRewardList){
			ToolTypeConstant typeConstant=new ToolTypeConstant();
			if(reward.getTools()!=null){
				String show=typeConstant.getToolTypeAndName(reward.getTools());
				reward.setToolsDesc(show);
			}
		}
		return SUCCESS;
	}
	
}