package com.adminTool.action;

import com.adminTool.bo.ActivityDrawPos;
import com.adminTool.service.ActivityDrawPosService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加轮盘位置
 * 
 * @author yezp
 */
public class AddActivityDrawPos extends ALDAdminActionSupport implements
		ModelDriven<ActivityDrawPos> {

	private static final long serialVersionUID = -2977762628170552603L;

	private ActivityDrawPos activityDrawPos = new ActivityDrawPos();
	private String isCommit = "F";
	private String activityId;

	private String[] posArr;
	private String[] lowerNumArr;
	private String[] upperNumArr;

	public String execute() {
		if (posArr == null) {
			isCommit = "F";
			return SUCCESS;
		}

		ActivityDrawPosService service = ServiceCacheFactory.getServiceCache()
				.getService(ActivityDrawPosService.class);
		service.delDrawPosByActivityId(activityDrawPos.getActivityId());

		for (int i = 0; i < posArr.length; i++) {
			boolean mark = false;
			if (posArr[i] != null && !posArr[i].equals("")) {
				try {
					int pos = Integer.parseInt(posArr[i]);
					activityDrawPos.setPos(pos);
					mark = true;
				} catch (NumberFormatException e) {
					mark = false;
				}
			}

			if (mark && lowerNumArr[i] != null && !lowerNumArr[i].equals("")) {
				try {
					int lower = Integer.parseInt(lowerNumArr[i]);
					activityDrawPos.setDrawLowerNum(lower);
					mark = true;
				} catch (NumberFormatException e) {
					mark = false;
				}
			}

			if (mark && upperNumArr[i] != null && !upperNumArr[i].equals("")) {
				try {
					int upper = Integer.parseInt(upperNumArr[i]);
					activityDrawPos.setDrawUpperNum(upper);
					mark = true;
				} catch (NumberFormatException e) {
					mark = false;
				}
			}

			if (mark) {
				service.addDrawPos(activityDrawPos);
			}

		}

		return SUCCESS;
	}

	public ActivityDrawPos getActivityDrawPos() {
		return activityDrawPos;
	}

	public void setActivityDrawPos(ActivityDrawPos activityDrawPos) {
		this.activityDrawPos = activityDrawPos;
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

	public String[] getPosArr() {
		return posArr;
	}

	public void setPosArr(String[] posArr) {
		this.posArr = posArr;
	}

	public String[] getLowerNumArr() {
		return lowerNumArr;
	}

	public void setLowerNumArr(String[] lowerNumArr) {
		this.lowerNumArr = lowerNumArr;
	}

	public String[] getUpperNumArr() {
		return upperNumArr;
	}

	public void setUpperNumArr(String[] upperNumArr) {
		this.upperNumArr = upperNumArr;
	}

	@Override
	public ActivityDrawPos getModel() {
		// TODO Auto-generated method stub
		return activityDrawPos;
	}

}
