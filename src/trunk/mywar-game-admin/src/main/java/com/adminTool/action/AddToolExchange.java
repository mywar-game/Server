package com.adminTool.action;

import com.adminTool.bo.ToolExchange;
import com.adminTool.service.ToolExchangeService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加道具兑换活动配置
 * 
 * @author yezp
 */
public class AddToolExchange extends ALDAdminActionSupport implements
		ModelDriven<ToolExchange> {

	private static final long serialVersionUID = -2515171446352298355L;

	private ToolExchange toolExchange = new ToolExchange();
	private String isCommit = "F";
	private String activityId;

	private String[] preExchangeItemsArr;
	private String[] postExchangeItemsArr;
	private String[] timesArr;
	private String[] descriptionArr;

	public String execute() {
		if (preExchangeItemsArr == null) {
			isCommit = "F";
			return SUCCESS;
		}

		ToolExchangeService service = ServiceCacheFactory.getServiceCache()
				.getService(ToolExchangeService.class);
		service.delToolExchangeByActivityId(toolExchange.getActivityId());

		for (int i = 0; i < preExchangeItemsArr.length; i++) {
			boolean mark = false;
			if (preExchangeItemsArr[i] != null && !preExchangeItemsArr[i].equals("")) {
				toolExchange.setPreExchangeItems(preExchangeItemsArr[i]);
				mark = true;
			} else {
				mark = false;
			}
			
			if (mark && postExchangeItemsArr[i] != null && !postExchangeItemsArr[i].equals("")) {
				toolExchange.setPostExchangeItems(postExchangeItemsArr[i]);
			} else {
				mark = false;
			}
			
			if (mark && timesArr[i] != null && !timesArr[i].equals("")) {
				try {
					int times = Integer.parseInt(timesArr[i]);
					toolExchange.setTimes(times);
				} catch (NumberFormatException e) {
					mark = false;
				}
			}
			
			if (mark && descriptionArr[i] != null) {
				toolExchange.setDescription(descriptionArr[i]);
			} else {
				mark = false;
			}	
			
			if (mark) {
				service.addToolExchange(toolExchange);
			}
		}
		
		return SUCCESS;
	}

	public ToolExchange getToolExchange() {
		return toolExchange;
	}

	public void setToolExchange(ToolExchange toolExchange) {
		this.toolExchange = toolExchange;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String[] getPreExchangeItemsArr() {
		return preExchangeItemsArr;
	}

	public void setPreExchangeItemsArr(String[] preExchangeItemsArr) {
		this.preExchangeItemsArr = preExchangeItemsArr;
	}

	public String[] getPostExchangeItemsArr() {
		return postExchangeItemsArr;
	}

	public void setPostExchangeItemsArr(String[] postExchangeItemsArr) {
		this.postExchangeItemsArr = postExchangeItemsArr;
	}

	public String[] getTimesArr() {
		return timesArr;
	}

	public void setTimesArr(String[] timesArr) {
		this.timesArr = timesArr;
	}

	public String[] getDescriptionArr() {
		return descriptionArr;
	}

	public void setDescriptionArr(String[] descriptionArr) {
		this.descriptionArr = descriptionArr;
	}

	@Override
	public ToolExchange getModel() {
		// TODO Auto-generated method stub
		return toolExchange;
	}

}
