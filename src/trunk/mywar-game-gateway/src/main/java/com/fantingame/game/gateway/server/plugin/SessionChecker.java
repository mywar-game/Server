package com.fantingame.game.gateway.server.plugin;

import java.util.List;
import java.util.Map;
import java.util.Timer;




import java.util.TimerTask;

import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.plugin.IAppPlugin;
import com.fantingame.game.gateway.server.manager.Session;
import com.fantingame.game.gateway.server.manager.SessionManager;
import com.google.common.collect.Lists;

public class SessionChecker implements IAppPlugin{
    private Timer timer;
	@Override
	public void shutdown() throws Exception {
		timer.cancel();
	}

	@Override
	public void startup() throws Exception {
		timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				try {
					checkSerssion();
				} catch (Exception e) {
					LogSystem.error(e, "检测死链接schdule出错！！");
				}
				
			}
		 }
		, 1000l, 60000l);
		//TODO  测试重连
//		Timer timer2 = new Timer();
//		timer2.schedule(new TimerTask(){
//			@Override
//			public void run() {
//				try {
//					ServerChannelManager.getInstance().getChannel(ChannelType.logicChannel).close();
//					LogSystem.info("关闭一条连接");
//				} catch (Exception e) {
//					LogSystem.error(e, "检测死链接schdule出错！！");
//				}
//
//			}
//		 }
//		, 10000l, 10000l);
		
	}

	private void checkSerssion(){
		Map<String, Session> map = SessionManager.getInstance().getSessionList();
		long now = System.currentTimeMillis();
		LogSystem.debug("开始检测死连接,time="+now+",");
		List<Session> list = Lists.newArrayList(); 
		for(Session session:map.values()){
			if(now-session.getLastRecordTime()>70000){
				LogSystem.debug("死链接--"+session.getSessionId()+",userId="+session.getUserId()+",ip="+session.getIp()+",token="+session.getToken());
				list.add(session);
			}
		}
		if(list.size()>0){
			for(Session session:list){
				session.closeChannel();
			}
		}
	}

	@Override
	public int cpOrder() {
		return 0;
	}

}
