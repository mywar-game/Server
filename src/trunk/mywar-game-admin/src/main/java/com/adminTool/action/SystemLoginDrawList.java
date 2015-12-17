package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemLoginDraw;
import com.adminTool.service.SystemLoginDrawService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class SystemLoginDrawList extends ALDAdminPageActionSupport {


	private static final long serialVersionUID = -43219995700816600L;
	private String isCommit = "F";
	private List<SystemLoginDraw> systemLoginDrawList = new ArrayList<SystemLoginDraw>();
	private Integer[] posArr;
	private String[] rewardsArr;
	private String[] drawRationArr;
	private Integer[] activityIdArr;
	private Integer[] posXerArr;
	private Integer[] poxYerArr;
	private Integer[] imgIdArr;
	
	@Override
	public String execute() {
		SystemLoginDrawService service = ServiceCacheFactory.getServiceCache().getService(SystemLoginDrawService.class);
		if (isCommit.equalsIgnoreCase("T") || isCommit == "T") {
			for (int i = 0; i < posArr.length; i++) {
				SystemLoginDraw draw = new SystemLoginDraw();
				draw.setPos(posArr[i]);
				if (rewardsArr.length >= i) {
					draw.setRewards(rewardsArr[i]);
				}
				if (drawRationArr.length >= i) {
					draw.setDrawRation(drawRationArr[i]);
				}
				if (activityIdArr.length >= i) {
					draw.setActivityId(activityIdArr[i]);
				}
				if (posXerArr.length >= i) {
					draw.setPosXer(posXerArr[i]);
				}
				if (poxYerArr.length >= i) {
					draw.setPoxYer(poxYerArr[i]);
				}
				if (imgIdArr.length >= i) {
					draw.setImgId(imgIdArr[i]);
				}
				service.update(draw);
			}
		} else {
			
			systemLoginDrawList = service.findAll();
		}
		return SUCCESS;
	}
	
	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
	
	public List<SystemLoginDraw> getSystemLoginDrawList() {
		return systemLoginDrawList;
	}

	public void setSystemLoginDrawList(List<SystemLoginDraw> systemLoginDrawList) {
		this.systemLoginDrawList = systemLoginDrawList;
	}
	
	public Integer[] getPosArr() {
		return posArr;
	}

	public void setPosArr(Integer[] posArr) {
		this.posArr = posArr;
	}

	public String[] getRewardsArr() {
		return rewardsArr;
	}

	public void setRewardsArr(String[] rewardsArr) {
		this.rewardsArr = rewardsArr;
	}

	public String[] getDrawRationArr() {
		return drawRationArr;
	}

	public void setDrawRationArr(String[] drawRationArr) {
		this.drawRationArr = drawRationArr;
	}

	public Integer[] getActivityIdArr() {
		return activityIdArr;
	}

	public void setActivityIdArr(Integer[] activityIdArr) {
		this.activityIdArr = activityIdArr;
	}

	public Integer[] getPosXerArr() {
		return posXerArr;
	}

	public void setPosXerArr(Integer[] posXerArr) {
		this.posXerArr = posXerArr;
	}

	public Integer[] getPoxYerArr() {
		return poxYerArr;
	}

	public void setPoxYerArr(Integer[] poxYerArr) {
		this.poxYerArr = poxYerArr;
	}

	public Integer[] getImgIdArr() {
		return imgIdArr;
	}

	public void setImgIdArr(Integer[] imgIdArr) {
		this.imgIdArr = imgIdArr;
	}

}
