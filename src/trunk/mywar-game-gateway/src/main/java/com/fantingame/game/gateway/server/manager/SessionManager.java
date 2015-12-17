package com.fantingame.game.gateway.server.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;






public class SessionManager {

	// 会话容器 < 会话序列号, Session 会话对象>
	private final Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();

	// 单例对象
	private static SessionManager sessionManager;
//	//高优先级消息队列  （及时发送 不能被丢弃）
//	private RingBufferWrapper<SendUserMsgEvent> hightUserMsgRingBufferWrapper = new RingBufferWrapper<>(SendUserMsgEvent.SendUserMsgEventFactory, 1024*8, false);
//	//中优先级消息队列  （可延迟发送  但不能被丢弃）
//	private RingBufferWrapper<SendUserMsgEvent> commonMsgRingBufferWrapper = new RingBufferWrapper<>(SendUserMsgEvent.SendUserMsgEventFactory, 1024*8, false);
//	//低优先级消息队列 (消息太多的时候能  系统能选择性的丢弃)
//	private RingBufferWrapper<SendUserMsgEvent> lowerMsgRingBufferWrapper = new RingBufferWrapper<>(SendUserMsgEvent.SendUserMsgEventFactory, 1024*8, false);

	// 单例实现 私有构造方法
	private SessionManager() {

	}

	// 单例实例取得方法
	public static SessionManager getInstance() {
		if (sessionManager == null) {
			sessionManager = new SessionManager();
		}
		return sessionManager;
	}

	/**
	 * 检查有无此会话对象
	 * 
	 * @param sequense
	 *            会话序列号
	 * @return
	 */
	public boolean isExist(String sequense) {
		return sessionMap.containsKey(sequense);
	}

	/**
	 * 获取会话
	 * 
	 * @param sequense
	 *            会话序列号
	 * @return
	 */
	public Session getSession(String channelId) {
		if(channelId==null||channelId.equals("")){
			return null;
		}
		return sessionMap.get(channelId);
	}

	/**
	 * 取得当前状态的会话容器
	 */
	public Map<String, Session> getSessionList() {
		return sessionMap;
	}
	/**
	 * 添加一个会话对象
	 * 
	 * @param session
	 */
	public void addSession(Session session) {
		if (session != null) {
				String sequense = session.getSessionId();
				sessionMap.put(sequense, session);
		}
	}

	/**
	 * 删除一个会话对象
	 * 
	 * @param sequense
	 *            会话序列号
	 */
	public Session delSession(String sequense) {
		if(sequense!=null&&!sequense.equals("")){
		    return sessionMap.remove(sequense);
		}
		return null;
	}

	/**
	 * 删除一个会话对象
	 * 
	 * @param session
	 *            会话对象
	 */
	public Session delSession(Session session) {
		if (session != null) {
			String sequense = session.getSessionId();
			return delSession(sequense);
		}
		return null;
	}
	/**
	 * 发布 发送消息job
	 * @param sessionList
	 */
//	public void tryPublishEvent(Session session,List<Msg> msgs){
//		try {
//			long sequence = hightUserMsgRingBufferWrapper.tryNext();
//			try{
//				SendUserMsgEvent event = hightUserMsgRingBufferWrapper.get(sequence);
//				event.setMsgs(msgs);
//				event.setSession(session);
//			}finally{
//				hightUserMsgRingBufferWrapper.publish(sequence);
//			}
//		} catch (InsufficientCapacityException e) {
//			LogSystem.error(e, "发送用户信息的buffer池子满了！！~~~~~~");
//		}
//		
//	}
//	
//	public void handleEvent(){
//		hightUserMsgRingBufferWrapper.handleEvents();
//	}
	/**
	 * 获得session的数量
	 * @return
	 */
	public int getSessionSize(){
		return sessionMap.size();
	}
}
//class SendUserMsgEvent implements RingBufferEvent{
//	private Session session;
//	private List<Msg> msgs;
//	public Session getSession() {
//		return session;
//	}
//
//	public void setSession(Session session) {
//		this.session = session;
//	}
//
//	public List<Msg> getMsgs() {
//		return msgs;
//	}
//
//	public void setMsgs(List<Msg> msgs) {
//		this.msgs = msgs;
//	}
//
//	public static final EventFactory<SendUserMsgEvent> SendUserMsgEventFactory = new EventFactory<SendUserMsgEvent>() {
//		@Override
//		public SendUserMsgEvent newInstance() {
//			return new SendUserMsgEvent();
//		}
//	}; 
//	@Override
//	public void cleanUp() {
//         		
//	}
//	@Override
//	public void handle() {
//		try {
//			session.flushAll(msgs);
//		} catch (Exception e) {
//			LogSystem.error(e, "session发送消息出现异常"+session.getSessionId()+",msg size=="+msgs.size());
//		}
//	}
//}
