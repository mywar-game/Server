package com.stats.action;

import com.adminTool.constant.AdminToolCMDCode;
import com.framework.cache.CacheManager;
import com.framework.client.http.HttpServersBridge;
import com.framework.client.socket.SocketClient;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.MD5;
import com.framework.config.LocalTools;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.server.msg.MsgBuilder;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.cache.ServerClientCache;
import com.stats.msgbody.MsgBean;
import com.stats.msgbody.ReqStatisticsMsg;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class MsgStataManagerList extends ALDAdminPageActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 命令请求类型
	 * 1查询 2开始 3停止
	 */
	private Integer type;
	
	/**
	 * 命令请求类型
	 * 1平台服务器2战斗服务器 3排位赛服务器4.聊天服务器
	 */
	private Integer requestType;
	
	/**
	 * 当前处理类型1:开启2关闭
	 */
	private Integer showType;
	
	/**
	 * 查询显示信息
	 */
	private MsgBean msgBean = new MsgBean();
	
	public String execute(){
		//默认查询
		if(type == null){
			type = 1;
		}
		if(requestType == null){
			requestType = 1;
		}
		//查询处理平台请求
		ReqStatisticsMsg reqStatisticsMsg = new ReqStatisticsMsg();
		reqStatisticsMsg.setType(type);
		//判断是发送平台服务器,聊天服务器,战斗服务器,或者排位赛服务器
		Msg msg = null;
		SocketClient chatSocketClient = null;
		TGameServer gameServer = null;
		if(requestType > 1){
			gameServer = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum());
			if(gameServer == null){
				return ERROR;
			}
		}
		switch(requestType){
		case 1:
			//平台服务器
			msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.MSG_MANAGETQUERY_TASK, reqStatisticsMsg, MsgBean.class);
			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
				CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
				super.setErroCodeNum(commomMsgBody.getErrorCode());
				super.setErroDescrip(commomMsgBody.getErrorDescription()+" 失败！");
				return SUCCESS;
			}
			break;
		case 2:
			//战斗服务器
			chatSocketClient = CacheManager.getCacheInstance(ServerClientCache.class).get(gameServer.getBattleServerId()+"").getSocketClient();
			if(chatSocketClient!=null){
	    		String userSequence = MD5.md5Of16(AdminToolCMDCode.MSG_MANAGETQUERY_TASK+gameServer.getServerCommunicationKey());
	            Msg msg1 =  MsgBuilder.buildMsg(0, "0", 0, "0", 1, 100, AdminToolCMDCode.MSG_MANAGETQUERY_TASK, LocalTools.getLocalConfig().getSystemNum(), "0", SystemConstant.SUCCESS_CODE,userSequence, reqStatisticsMsg);
	            msg = chatSocketClient.sendMsgsToServer(msg1, MsgBean.class);
	            chatSocketClient.close();
	            if(msg.getMsgHead().getErrorCode()!=SystemConstant.SUCCESS_CODE){
	            	throw new RuntimeException("发送战斗服务器失败:"+((CommomMsgBody)msg.getMsgBody()).getErrorDescription());
	            }
	        }
			break;
		case 3:
			//排位赛服务器
			chatSocketClient = CacheManager.getCacheInstance(ServerClientCache.class).get(gameServer.getOrderServerId()+"").getSocketClient();
			if(chatSocketClient!=null){
	    		String userSequence = MD5.md5Of16(AdminToolCMDCode.MSG_MANAGETQUERY_TASK+gameServer.getServerCommunicationKey());
	            Msg msg1 =  MsgBuilder.buildMsg(0, "0", 0, "0", 1, 100, AdminToolCMDCode.MSG_MANAGETQUERY_TASK, LocalTools.getLocalConfig().getSystemNum(), "0", SystemConstant.SUCCESS_CODE,userSequence, reqStatisticsMsg);
	            msg = chatSocketClient.sendMsgsToServer(msg1, MsgBean.class);
	            chatSocketClient.close();
	            if(msg.getMsgHead().getErrorCode()!=SystemConstant.SUCCESS_CODE){
	            	throw new RuntimeException("发送排位赛服务器失败:"+((CommomMsgBody)msg.getMsgBody()).getErrorDescription());
	            }
	        }
			break;
		case 4:
			//聊天服务器
			chatSocketClient = CacheManager.getCacheInstance(ServerClientCache.class).get(gameServer.getChatServerId()+"").getSocketClient();
			if(chatSocketClient!=null){
	    		String userSequence = MD5.md5Of16(AdminToolCMDCode.MSG_MANAGETQUERY_TASK+gameServer.getServerCommunicationKey());
	            Msg msg1 =  MsgBuilder.buildMsg(0, "0", 0, "0", 1, 100, AdminToolCMDCode.MSG_MANAGETQUERY_TASK, LocalTools.getLocalConfig().getSystemNum(), "0", SystemConstant.SUCCESS_CODE,userSequence, reqStatisticsMsg);
	            msg = chatSocketClient.sendMsgsToServer(msg1, MsgBean.class);
	            chatSocketClient.close();
	            if(msg.getMsgHead().getErrorCode()!=SystemConstant.SUCCESS_CODE){
	            	throw new RuntimeException("发送聊天服务器失败:"+((CommomMsgBody)msg.getMsgBody()).getErrorDescription());
	            }
	        }
			break;
			default:
				break;
		}
		if (msg == null || msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 失败！");
			return ERROR;
		}
		msgBean = (MsgBean) msg.getMsgBody();
		
		if(!msgBean.getMsgNum().equals("") && Long.valueOf(msgBean.getStataTime())>0){
			msgBean.setContinueTime(Math.round(Long.valueOf(msgBean.getMsgNum())*1000/Long.valueOf(msgBean.getStataTime())));
		}else if(msgBean.getMsgNum().equals("")){
			msgBean.setMsgNum("0");
		}
		showType = msgBean.getShowType();
		return SUCCESS;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public MsgBean getMsgBean() {
		return msgBean;
	}

	public void setMsgBean(MsgBean msgBean) {
		this.msgBean = msgBean;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public Integer getRequestType() {
		return requestType;
	}

	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	
}
