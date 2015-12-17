package com.stats.action;

import java.util.List;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.ErrorCode;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserConsumeStats;
import com.stats.service.UserConsumeStatsService;

/**
 * 玩家消费统计（按日期）
 * @author hzy
 * 2012-8-8
 */
public class UserConsumeStatsList extends ALDAdminStatsDatePageActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;

	private List<UserConsumeStats> statsList;
	
	@Override
	public String execute() throws Exception {
		UserConsumeStatsService userConsumeStatsService = ServiceCacheFactory.getServiceCache().getService(UserConsumeStatsService.class);
		ErrorCode errorCode = new ErrorCode();
		IPage<UserConsumeStats> list;
		if (getStartDate() != null && getEndDate() != null) {
			list = userConsumeStatsService.findPageListInDate(super.getPageSize(), super.getToPage(), getStartDate(), getEndDate());
		} else {
			list = userConsumeStatsService.findPageList(super.getPageSize(), super.getToPage());
		}
		if (list != null) {
			statsList = (List<UserConsumeStats>)list.getData();
			
		}
		super.setErroCodeNum(errorCode.getErrorCode());
		super.setErroDescrip(errorCode.getErrorDisc());
		return SUCCESS;
	}

	public void setStatsList(List<UserConsumeStats> statsList) {
		this.statsList = statsList;
	}

	public List<UserConsumeStats> getStatsList() {
		return statsList;
	}

}
