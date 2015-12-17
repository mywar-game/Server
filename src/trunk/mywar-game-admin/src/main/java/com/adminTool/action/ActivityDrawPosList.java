package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.ActivityDrawPos;
import com.adminTool.service.ActivityDrawPosService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 抽奖轮盘位置列表
 * 
 * @author yezp
 */
public class ActivityDrawPosList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 768385117037205202L;

	private List<ActivityDrawPos> drawPosList;
	private String activityId;
	private String isCommit = "F";

	public String execute() {
		ActivityDrawPosService service = ServiceCacheFactory.getServiceCache()
				.getService(ActivityDrawPosService.class);

		int activityIdInt = 0;
		if (activityId != null && !activityId.equals("")) {
			activityIdInt = Integer.parseInt(activityId);
		}

		IPage<ActivityDrawPos> iPage = service.findDrawPosPageList(
				activityIdInt, super.getToPage(),
				ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (iPage == null) {
			return SUCCESS;
		}

		drawPosList = (List<ActivityDrawPos>) iPage.getData();
		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}

	public List<ActivityDrawPos> getDrawPosList() {
		return drawPosList;
	}

	public void setDrawPosList(List<ActivityDrawPos> drawPosList) {
		this.drawPosList = drawPosList;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
