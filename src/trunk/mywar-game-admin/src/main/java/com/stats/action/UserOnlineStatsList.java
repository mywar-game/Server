package com.stats.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.UserOnlineLog;
import com.log.service.UserOnlineLogService;
import com.stats.bo.UserOnlineStats;
import com.stats.bo.WeekUserOnlineStats;
import com.stats.service.UserOnlineStatsService;
import com.stats.service.WeekUserOnlineStatsService;
import com.system.manager.DataSourceManager;

/**
 * 在线人数统计列表
 * @author hzy
 * 2012-4-16
 */
public class UserOnlineStatsList extends ALDAdminStatsDatePageActionSupport {
	
	/**  */
	private static final long serialVersionUID = -5848689650294992086L;
	
	/**  */
	private List<Object> statsList = new ArrayList<Object>();
	
	/** 前三天 */
	private static final int THREE_DAYS_BEFORE = 3;
	
	/** 三天后 */
	private static final int THREE_DAYS_AFTER = 21;
	
	/** 前三天 */
	private static final int TYPE_THREE_DAYS_BEFORE = 1;
	
	/** 三天后 */
	private static final int TYPE_THREE_DAYS_AFTER = 2;
	
	/** 三周后 */
	private static final int TYPE_THREE_WEEKS_AFTER = 3;
	
	/** 所处时间段类型 */
	private int type;
	
	public String execute() throws Exception {
		UserOnlineLogService userOnlineLogService = ServiceCacheFactory.getServiceCache().getService(UserOnlineLogService.class);
		UserOnlineStatsService userOnlineStatsService = ServiceCacheFactory.getServiceCache().getService(UserOnlineStatsService.class);
		WeekUserOnlineStatsService weekUserOnlineStatsService = ServiceCacheFactory.getServiceCache().getService(WeekUserOnlineStatsService.class);
		
		//看是三天前、三天后、三周后的哪一种
		int openDays = DateUtil.getOpenDays();
//		System.out.println("openDays " + openDays);
		
		if (openDays < THREE_DAYS_BEFORE) {
			type = TYPE_THREE_DAYS_BEFORE;
//			System.out.println("前三天");
			List<Object> list = userOnlineLogService.findThreeDaysBeforOpenServer(DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum()).getServerOpernTime());
			for (int j = 0; j < list.size(); j++) {
				UserOnlineLog userOnlineLog = new UserOnlineLog();
				String str = ((Object[]) list.get(j))[1].toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date time = sdf.parse(str);
				int onlineAmount = Integer.parseInt(((Object[]) list.get(j))[2].toString());
				userOnlineLog.setTime(new Timestamp(time.getTime()));
				userOnlineLog.setOnlineAmount(onlineAmount);
				statsList.add(userOnlineLog);
			}
		} else if (openDays < THREE_DAYS_AFTER) {
			type = TYPE_THREE_DAYS_AFTER;
//			System.out.println("三天后");
			//从日统计表中拿数据
			//判断是否是条件查询，按周显示 每页大小
			IPage<UserOnlineStats> page = null;
			if (getStartDate() == null && getEndDate() == null) {
				page = userOnlineStatsService.findPageList(DEFAULT_PAGESIZE, super.getToPage());
			} else {
				page = userOnlineStatsService.findPageListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
			}
			if (page != null) {
				List<UserOnlineStats> list = (List<UserOnlineStats>) page.getData();
				statsList.addAll(list);
				super.setTotalPage(page.getTotalPage());
				super.setTotalSize(page.getTotalSize());
			}
		} else {
			type = TYPE_THREE_WEEKS_AFTER;
//			System.out.println("三周后");
			//从周统计表中拿数据
			IPage<WeekUserOnlineStats> page = null;
			if (getStartDate() == null && getEndDate() == null) {
				page = weekUserOnlineStatsService.findPageList(DEFAULT_PAGESIZE, super.getToPage());
			} else {
				page = weekUserOnlineStatsService.findPageListInDate(DEFAULT_PAGESIZE , super.getToPage(), getStartDate(), getEndDate());
			}
			if (page != null) {
				List<WeekUserOnlineStats> list = (List<WeekUserOnlineStats>) page.getData();
				statsList.addAll(list);
				super.setTotalPage(page.getTotalPage());
				super.setTotalSize(page.getTotalSize());
			}
		}
		return SUCCESS;
	}

	public void setStatsList(List<Object> statsList) {
		this.statsList = statsList;
	}

	public List<Object> getStatsList() {
		return statsList;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

}
