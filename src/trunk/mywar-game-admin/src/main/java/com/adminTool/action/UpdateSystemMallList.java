package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemMall;
import com.adminTool.service.SystemMallService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class UpdateSystemMallList extends ALDAdminActionSupport{

	private List<SystemMall> systemMallList = new ArrayList<SystemMall>();
	private int mallId;
	private String isUpdate;
	
	private int mallIdArr;
	private String tagArr;
	private String nameArr;
	private String descriptionArr;
	private int toolTypeArr;
	private int toolIdArr;
	private int toolNumArr;
	private int moneyTypeArr;
	private int amountArr;
	private int imgIdArr;
	private int dailyMaxNumArr;
	private int maxNumArr;
	private int needVipLevelArr;
	private int seeVipLevelArr;
	
	@Override
	public String execute() {
		
		if (tagArr == null ) {
			SystemMallService systemMallService = ServiceCacheFactory.getServiceCache().getService(SystemMallService.class);
			systemMallList = systemMallService.getSystemMallByMallId(mallId);
			return INPUT;
		} else {
			isUpdate = "T";
			
			SystemMallService systemMallService = ServiceCacheFactory.getServiceCache().getService(SystemMallService.class);
			SystemMall systemMall = new SystemMall(mallIdArr, tagArr, nameArr, descriptionArr,
							toolTypeArr, toolIdArr, toolNumArr, moneyTypeArr, amountArr,
							imgIdArr, dailyMaxNumArr, maxNumArr, needVipLevelArr,
							seeVipLevelArr);
			systemMallService.update(systemMall);
			systemMallList = systemMallService.getSystemMallList();
			return SUCCESS;
		}
	}
	public String getIsUpdate() {
		return isUpdate;
	}
	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
	}

	public List<SystemMall> getSystemMallList() {
		return systemMallList;
	}

	public void setSystemMallList(List<SystemMall> systemMallList) {
		this.systemMallList = systemMallList;
	}
	

	public int getMallIdArr() {
		return mallIdArr;
	}

	public void setMallIdArr(int mallIdArr) {
		this.mallIdArr = mallIdArr;
	}

	public String getTagArr() {
		return tagArr;
	}

	public void setTagArr(String tagArr) {
		this.tagArr = tagArr;
	}

	public String getNameArr() {
		return nameArr;
	}

	public void setNameArr(String nameArr) {
		this.nameArr = nameArr;
	}

	public String getDescriptionArr() {
		return descriptionArr;
	}

	public void setDescriptionArr(String descriptionArr) {
		this.descriptionArr = descriptionArr;
	}

	public int getToolTypeArr() {
		return toolTypeArr;
	}

	public void setToolTypeArr(int toolTypeArr) {
		this.toolTypeArr = toolTypeArr;
	}

	public int getToolIdArr() {
		return toolIdArr;
	}

	public void setToolIdArr(int toolIdArr) {
		this.toolIdArr = toolIdArr;
	}

	public int getToolNumArr() {
		return toolNumArr;
	}

	public void setToolNumArr(int toolNumArr) {
		this.toolNumArr = toolNumArr;
	}

	public int getMoneyTypeArr() {
		return moneyTypeArr;
	}

	public void setMoneyTypeArr(int moneyTypeArr) {
		this.moneyTypeArr = moneyTypeArr;
	}

	public int getAmountArr() {
		return amountArr;
	}

	public void setAmountArr(int amountArr) {
		this.amountArr = amountArr;
	}

	public int getImgIdArr() {
		return imgIdArr;
	}

	public void setImgIdArr(int imgIdArr) {
		this.imgIdArr = imgIdArr;
	}

	public int getDailyMaxNumArr() {
		return dailyMaxNumArr;
	}

	public void setDailyMaxNumArr(int dailyMaxNumArr) {
		this.dailyMaxNumArr = dailyMaxNumArr;
	}

	public int getMaxNumArr() {
		return maxNumArr;
	}

	public void setMaxNumArr(int maxNumArr) {
		this.maxNumArr = maxNumArr;
	}

	public int getNeedVipLevelArr() {
		return needVipLevelArr;
	}

	public void setNeedVipLevelArr(int needVipLevelArr) {
		this.needVipLevelArr = needVipLevelArr;
	}

	public int getSeeVipLevelArr() {
		return seeVipLevelArr;
	}

	public void setSeeVipLevelArr(int seeVipLevelArr) {
		this.seeVipLevelArr = seeVipLevelArr;
	}

}
