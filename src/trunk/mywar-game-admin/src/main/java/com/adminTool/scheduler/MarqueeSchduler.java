package com.adminTool.scheduler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.adminTool.bo.AdminMarquee;
import com.adminTool.constant.AdminToolConstant;
import com.adminTool.service.AdminMarqueeService;
import com.framework.client.http.HttpClientBridge;
import com.framework.log.LogSystem;
import com.framework.scheduler.SchedulerEntry;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;

/**
 * 跑马灯定时
 * 
 * @author yezp
 */
public class MarqueeSchduler extends SchedulerEntry {

	private static final String REQ_URL = "sendSystemMsg.do";

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		AdminMarqueeService service = ServiceCacheFactory.getServiceCache()
				.getService(AdminMarqueeService.class);
		TGameServerService tServerService = ServiceCacheFactory
				.getServiceCache().getService(TGameServerService.class);

		List<AdminMarquee> list = service.getAdminMarqueeList();
		for (AdminMarquee marquee : list) {
			try {
				sendMsgToGame(marquee, tServerService, service);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送跑马灯
	 * 
	 * @param marquee
	 * @param serverService
	 * @throws UnsupportedEncodingException
	 */
	public void sendMsgToGame(AdminMarquee marquee,
			TGameServerService serverService, AdminMarqueeService service)
			throws UnsupportedEncodingException {
		// 判断是否有效
		if (marquee.getIsUse() == AdminToolConstant.MARQUEE_USELESS) {
			return;
		}

		// 是否在有效期内
		Date now = new Date();
		if (now.before(marquee.getStartTime())
				|| now.after(marquee.getEndTime())) {
			return;
		}

		// 播放次数
//		if (marquee.getHasLoopTime() >= marquee.getLoopTime()) {
//			return;
//		}
		
		// 播放间隔
//		if (marquee.getPlayTime() != null) {
//			long interval = now.getTime() - marquee.getPlayTime().getTime();
////			if (interval < (marquee.getPlayInterval() * 1000)) {				
////				return;
////			}
//			// 播放间隔
//			
//		}
		if (marquee.getPlayTime() != null) {
			long interval = now.getTime() - marquee.getPlayTime().getTime();
			if (interval < (marquee.getPlayInterval() * 1000)) {
				if (marquee.getHasLoopTime() >= marquee.getLoopTime()) {
					return;
				}
			} else {
				marquee.setHasLoopTime(0);
			}
		}

		String serverIds = marquee.getServerIds();
		String[] sidArr = serverIds.split(",");

		for (String str : sidArr) {
			String[] strArr = str.split("_");

			if (strArr.length != 2) {
				return;
			}
			String serverId = strArr[1];

			Integer serverIdInt = Integer.parseInt(serverId);
			TGameServer server = serverService.findOneTGameServer(serverIdInt);
			if (server == null)
				continue;
			
			CustomerContextHolder.setSystemNum(server.getServerId());

			StringBuilder sb = new StringBuilder();
			StringBuilder md5Sb = new StringBuilder();
			sb.append("&content="
					+ URLEncoder.encode(marquee.getContent(), "utf-8"));
			md5Sb.append(URLEncoder.encode(marquee.getContent(), "utf-8"));
			if (marquee.getPartnerIds().equals("0")) {
				sb.append("&partnerId=all");
				md5Sb.append("all");
			} else {
				sb.append("&partnerId=" + marquee.getPartnerIds());
				md5Sb.append(marquee.getPartnerIds());
			}			
			
			String response = HttpClientBridge.sendToGameServer(REQ_URL,
					sb.toString(), md5Sb.toString(), server);

			if (response == null) {
				LogSystem.debug("发送跑马灯: response = null");
				return;
			} else {
				JSONObject jsonObject = JSONObject.fromObject(response);
				if (jsonObject.containsKey(HttpClientBridge.RETURN_RC)
						&& jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_CODE) {
					LogSystem.debug("发送跑马灯: rc"
							+ jsonObject.getInt(HttpClientBridge.RETURN_RC));
				}
			}

			Date playTime = new Date();
			int hasLoopTime = marquee.getHasLoopTime();
			System.out.println("hasLoopTime1:" + hasLoopTime + marquee.getContent());
			marquee.setHasLoopTime(hasLoopTime + 1);
			System.out.println("hasLoopTime2:" + marquee.getHasLoopTime() + marquee.getContent());
			marquee.setPlayTime(playTime);
			service.updateAdminMarquee(marquee);
		}
	}

}