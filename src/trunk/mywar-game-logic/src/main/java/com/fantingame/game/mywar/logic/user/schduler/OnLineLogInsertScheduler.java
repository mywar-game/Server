package com.fantingame.game.mywar.logic.user.schduler;

import java.util.Date;
import java.util.Set;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.scheduler.SchedulerEntry;
import com.fantingame.game.mywar.logic.user.service.UserService;
/**
 * 在线日志插入
 * @author Administrator
 *
 */
public class OnLineLogInsertScheduler extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext arg0)
			throws JobExecutionException {
        UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
        Set<String> userIds = userService.getAllOnlineUserId();
		StringBuffer userIdStr = new StringBuffer();
		int num = 0;
		for (String userId : userIds) {
			userIdStr.append(userId).append(",");
			num++;
		}
		userService.userOnlineLog(new Date(), num,
				userIdStr.toString());
	}

}
