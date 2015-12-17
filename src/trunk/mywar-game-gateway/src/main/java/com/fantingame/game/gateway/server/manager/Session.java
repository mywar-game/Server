package com.fantingame.game.gateway.server.manager;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.msgbody.common.codec.DataCodecFactory;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.common.model.MsgHead;
import com.fantingame.game.server.channel.AbstractChannel;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.monitor.MonitorService;
import com.google.common.collect.Maps;



/**
 * 一个会话
 * 
 * @author mengc
 * 
 */
public class Session {

    //session中对应的session id
	private Channel channel;

	//用户消息列表
//	private MsgStack<Msg> message;

	// 最后访问时间
	private long lastRecordTime = System.currentTimeMillis();

	// 登录时间
	private long loginTime = System.currentTimeMillis();
	
	public static final String NO_LOGIN = "NOLOGIN";
	
	private Map<Integer,Object> sequenceRe = Maps.newConcurrentMap();
 
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public Session(Channel channel) {
		this.channel = channel;
	}
	/**
	 * 该会话的channel是否关闭
	 * 
	 * @return
	 */
	public boolean isChannelClosed() {
		if (channel == null) {
			return false;
		}
		return channel.isClosed();
	}
    /**
     * 关闭连接
     */
	public void closeChannel() {
		if (channel != null && !channel.isClosed()) {
			channel.close();
		}
	}
	public void sendMsg(Msg msg){
		if(sequenceRe.containsKey(msg.getMsgHead().getMsgSequense())){
			LogSystem.warn("--------------------------------------------------------------------------------------重复发送的的sequence"+msg.getMsgHead()+",sequence = "+msg.getMsgHead().getMsgSequense());
		}else{
			sequenceRe.put(msg.getMsgHead().getMsgSequense(), 1);
		}
		//低优先级的消息  如果繁忙 则直接丢弃
		if(msg.getMsgHead().getPriority()==MsgHead.LOWER){
			if(channel==null||!channel.isWriteAble()){
				return;
			}
		}

		List<Msg> msgs = new ArrayList<Msg>(1);
		msgs.add(msg);
		flushAll(msgs);
//		SessionManager.getInstance().tryPublishEvent(this,msgs);
	}
	
//	public void sendMsg(List<Msg> msgs){
//		if(msgs==null||msgs.size()==0){
//			return;
//		}
//		if(msgs.size()==1){
//			Msg msg = msgs.get(0);
//			if(msg.getMsgHead().getPriority()==MsgHead.LOWER){
//				if(channel==null||!channel.isWriteAble()){
//					return;
//				}
//			}
//		}else{
//			throw new RuntimeException("一次发送有多条消息，怎么来的？不符合预期");
//		}
//		flushAll(msgs);
////		SessionManager.getInstance().tryPublishEvent(this,msgs);
//	}
	/**
	 * 写完数据后关闭连接
	 * @param msg
	 */
	public void sendMsgAndCloseChannel(Msg msg){
		List<Msg> list = new ArrayList<Msg>();
		list.add(msg);
		byte[] datas = DataCodecFactory.getInstance().encodeMsgUser(list);
		channel.writeAfterClose(datas);
	}
	/**
	 * 发送数据
	 * @param msgs
	 */
	public void flushAll(List<Msg> msgs){
		if(msgs.size()>0){
//			LogSystem.debug("准备给session"+channel.getChannelId()+"，发送数据---->"+msgs.size()+",cmdCode="+msgs.get(0).getMsgHead().getCmdCode()+",errorCode="+msgs.get(0).getMsgHead().getErrorCode());
			byte[] datas = DataCodecFactory.getInstance().encodeMsgUser(msgs);
			if(channel!=null&&!channel.isClosed()){
//				LogSystem.debug("真的给session"+channel.getChannelId()+"，发送数据---->"+msgs.size()+",data length = "+datas.length);
				MonitorService.getInstance().markUserOutcomingBandwidth(datas.length);
				channel.write(datas);
			}
		}
	}
	public void flushAll(Msg msg){
		List<Msg> msgs = new ArrayList<Msg>(1);
		msgs.add(msg);
		flushAll(msgs);
	}
    /**
     * 获取客户端类型
     * @return
     */
	public int getClientType(){
		return this.channel.getClientType();
	}
	/**
	 * 获取协议类型
	 * @return
	 */
	public int getProtocolType(){
		return channel.getProtocolType();
	}
	public long getLastRecordTime() {
		return lastRecordTime;
	}
	public void setLastRecordTime(long lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	/**
	 * 获取ip地址
	 * @return
	 */
	public String getIp(){
		Object o = channel.getAttribute(AbstractChannel.IP);
		if(o!=null){
			return (String)o;
		}
		return "";
	}
	/**
	 * 清理channel上的信息
	 */
	public void resetChannelInfo(){
 		channel.clearAttribute();
	}
	
	public String getSessionId(){
		return channel.getChannelId();
	}
	/**
	 * 获取用户id (登录后才会有值)
	 * @return
	 */
	public String getUserId(){
		Object userIdObject = channel.getAttribute(AbstractChannel.USER_ID);
		if(userIdObject!=null){
			return (String)userIdObject;
		}
		return null;
	}
	/**
	 * 获取登录token (登录后才会有值)
	 * @return
	 */
	public String getToken(){
		Object tokenObject = channel.getAttribute(AbstractChannel.TOKEN);
		if(tokenObject!=null){
			return (String)tokenObject;
		}
		return null;
	}
	/**
	 * 在线秒数
	 * @return
	 */
	public int getOnlineSeconds(){
		long now = System.currentTimeMillis();
		long distance = now-loginTime;
		return (int)(distance/1000);
	}
}
