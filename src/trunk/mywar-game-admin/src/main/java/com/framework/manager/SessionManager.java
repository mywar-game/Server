package com.framework.manager;

import java.util.HashMap;
import java.util.Map;


public class SessionManager {

	// 会话容器 < 会话序列号, Session 会话对象>
	private final Map<String, Session> sessionMap = new HashMap<String, Session>();

	// 单例对象
	private static SessionManager sessionManager;

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
	public Session getSession(String sequense) {
		return sessionMap.get(sequense);
	}

	/**
	 * 取得当前状态的会话容器
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Session> getSessionList() {
		return (Map<String, Session>) ((HashMap<String, Session>) sessionMap)
				.clone();
	}

	/**
	 * 添加一个会话对象
	 * 
	 * @param session
	 */
	public void addSession(Session session) {
		if (session != null) {
			synchronized (sessionMap) {
				String sequense = session.getSessionSequense();
				sessionMap.put(sequense, session);
			}
		}
	}

	/**
	 * 删除一个会话对象
	 * 
	 * @param sequense
	 *            会话序列号
	 */
	public void delSession(String sequense) {
		synchronized (sessionMap) {
			sessionMap.remove(sequense);
		}
	}

	/**
	 * 删除一个会话对象
	 * 
	 * @param session
	 *            会话对象
	 */
	public void delSession(Session session) {
		if (session != null) {
			String sequense = session.getSessionSequense();
			synchronized (sessionMap) {
				sessionMap.remove(sequense);
			}
		}
	}
}
