package com.fantingame.game.mywar.logic.user.schduler;

import java.util.Set;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.scheduler.SchedulerEntry;
import com.fantingame.game.msgbody.notify.explore.User_midNightPushNotify;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.msg.MsgDispatchCenter;

/**
 * 午夜推送
 * 
 * @author yezp
 */
public class MidnightPushScheduler extends SchedulerEntry {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		Set<String> userIds = userService.getAllOnlineUserId();
		for (String userId : userIds) {
			try {
				User_midNightPushNotify push = userService.midnightPushNotify(userId);
				if (push == null)
					continue;
				
				MsgDispatchCenter.disPatchUserMsg("User_midNightPush", userId, push);
				LogSystem.info("午夜推送成功，userId[" + userId + "]~~~~~~~~~~~~~~~~~~~~~~~~~~");
			} catch (Exception e) {
				LogSystem.error(e, "午夜推送出错，userId[" + userId);
			}
		}
	}

}
