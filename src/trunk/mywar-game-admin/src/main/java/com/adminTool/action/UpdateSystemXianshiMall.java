package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.SystemXianshiMall;
import com.adminTool.service.SystemXianshiMallService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateSystemXianshiMall extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 1L;
	private List<SystemXianshiMall> list;
	private int activityId;
	private int mallId;
	private SystemXianshiMall mall;
	
	private int id;
	private int toolType;
	private int toolId;
	private int type;
	private int subType;
	private int diamond;
	private int toolNum;
	private int buyTimes;
	private String isCommit;
	
	@Override
	public String execute() {
		
		SystemXianshiMallService service = ServiceCacheFactory.getServiceCache().getService(SystemXianshiMallService.class);
		if (id != 0) {
			SystemXianshiMall mal = new SystemXianshiMall();
			mal.setId(id);
			mal.setToolType(toolType);
			mal.setToolId(toolId);
			mal.setType(type);
			mal.setSubType(subType);
			mal.setDiamond(diamond);
			mal.setToolNum(toolNum);
			mal.setActivityId(45);
			mal.setBuyTimes(buyTimes);
			service.update(mal);
		} else {
			SystemXianshiMall mal = new SystemXianshiMall();
			mal.setToolType(toolType);
			mal.setToolId(toolId);
			mal.setType(type);
			mal.setActivityId(45);
			mal.setSubType(subType);
			mal.setDiamond(diamond);
			mal.setToolNum(toolNum);
			mal.setBuyTimes(buyTimes);
			service.insert(mal);
		}
		isCommit = "true";
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getToolType() {
		return toolType;
	}

	public void setToolType(int toolType) {
		this.toolType = toolType;
	}

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public int getDiamond() {
		return diamond;
	}

	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}

	public int getToolNum() {
		return toolNum;
	}

	public void setToolNum(int toolNum) {
		this.toolNum = toolNum;
	}

	public int getBuyTimes() {
		return buyTimes;
	}

	public void setBuyTimes(int buyTimes) {
		this.buyTimes = buyTimes;
	}

	public List<SystemXianshiMall> getList() {
		return list;
	}

	public void setList(List<SystemXianshiMall> list) {
		this.list = list;
	}

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

	public SystemXianshiMall getMall() {
		return mall;
	}

	public void setMall(SystemXianshiMall mall) {
		this.mall = mall;
	}
	
	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
}
