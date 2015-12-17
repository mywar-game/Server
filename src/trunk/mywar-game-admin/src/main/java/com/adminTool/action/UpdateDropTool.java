package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.GiftbagDropTool;
import com.adminTool.service.UpdateDropToolService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 修改在线礼包
 * @author Administrator
 *
 */
public class UpdateDropTool extends ALDAdminActionSupport {

	private static final long serialVersionUID = -1940148255670796184L;
	List<GiftbagDropTool> giftbagDropToolList = new ArrayList<GiftbagDropTool>();
	private Boolean isCommit = false;
	private Integer[] giftbagDropToolIdArr;
	private Integer[] giftbagIdArr;
	private Integer[] toolTypeArr;
	private Integer[] toolIdArr;
	private String[] nameArr;
	private Integer[] toolNumArr;
	private Integer[] lowerNumArr;
	private Integer[] upperNumArr;
	
	@Override
	public String execute() {
		UpdateDropToolService service = ServiceCacheFactory.getServiceCache()
				.getService(UpdateDropToolService.class);
		if (isCommit) {
			GiftbagDropTool tool;
			if (giftbagDropToolIdArr != null) {
				int i = 0;
				for (Integer id : giftbagDropToolIdArr) {
					tool = new GiftbagDropTool();
					tool.setGiftbagDropToolId(id);
					tool.setGiftbagId(giftbagIdArr[i]);
					tool.setToolType(toolTypeArr[i]);
					tool.setToolId(toolIdArr[i]);
					tool.setName(nameArr[i]);
					tool.setToolNum(toolNumArr[i]);
					tool.setLowerNum(lowerNumArr[i]);
					tool.setUpperNum(upperNumArr[i]);
					i ++;
					service.update(tool);
				}
			}
		}
		giftbagDropToolList = service.getList();
		return SUCCESS;
	}

	public Integer[] getGiftbagDropToolIdArr() {
		return giftbagDropToolIdArr;
	}

	public void setGiftbagDropToolIdArr(Integer[] giftbagDropToolIdArr) {
		this.giftbagDropToolIdArr = giftbagDropToolIdArr;
	}

	public Integer[] getGiftbagIdArr() {
		return giftbagIdArr;
	}

	public void setGiftbagIdArr(Integer[] giftbagIdArr) {
		this.giftbagIdArr = giftbagIdArr;
	}

	public Integer[] getToolTypeArr() {
		return toolTypeArr;
	}

	public void setToolTypeArr(Integer[] toolTypeArr) {
		this.toolTypeArr = toolTypeArr;
	}

	public Integer[] getToolIdArr() {
		return toolIdArr;
	}

	public void setToolIdArr(Integer[] toolIdArr) {
		this.toolIdArr = toolIdArr;
	}

	public String[] getNameArr() {
		return nameArr;
	}

	public void setNameArr(String[] nameArr) {
		this.nameArr = nameArr;
	}

	public Integer[] getToolNumArr() {
		return toolNumArr;
	}

	public void setToolNumArr(Integer[] toolNumArr) {
		this.toolNumArr = toolNumArr;
	}

	public Integer[] getLowerNumArr() {
		return lowerNumArr;
	}

	public void setLowerNumArr(Integer[] lowerNumArr) {
		this.lowerNumArr = lowerNumArr;
	}

	public Integer[] getUpperNumArr() {
		return upperNumArr;
	}

	public void setUpperNumArr(Integer[] upperNumArr) {
		this.upperNumArr = upperNumArr;
	}

	public Boolean getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(Boolean isCommit) {
		this.isCommit = isCommit;
	}

	public List<GiftbagDropTool> getGiftbagDropToolList() {
		return giftbagDropToolList;
	}

	public void setGiftbagDropToolList(List<GiftbagDropTool> giftbagDropToolList) {
		this.giftbagDropToolList = giftbagDropToolList;
	}

}
