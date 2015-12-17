package com.framework.manager;

import java.util.List;

import com.framework.server.msg.Msg;
/**
 * 系统消息队列管理器
 * @author mengc
 *
 */
public class ServerMessagerManager {
  private static ServerMessagerManager serverMessagerManager;
  //发送消息列表
  private Messager messagerSend = new Messager();
  private ServerMessagerManager() {
  }
  
  
  
  public static ServerMessagerManager getInstance() {
	  if (serverMessagerManager == null) {
		  serverMessagerManager = new ServerMessagerManager();
		  return serverMessagerManager;
	  } else {
		  return serverMessagerManager;
	  }
  }
  /**
   * 压入发送消息
   * @param msg
   */
  public void pushSendMsg(Msg msg) {
	  messagerSend.pushMsg(msg);
  }
  
  public void  pushMsgList(List<Msg> msgList) {
	  messagerSend.pushMsgList(msgList);
  }
  /**
   * 获取发送消息
   * @return
   */
  public List<Msg> popSendMsgList(int maxNum) {
	  return messagerSend.popMsgList(maxNum);
  }
}
