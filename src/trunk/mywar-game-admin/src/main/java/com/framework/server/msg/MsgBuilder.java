package com.framework.server.msg;

import java.util.ArrayList;
import java.util.List;

import com.framework.server.msg.model.ICodeAble;


/**
 * 消息建造器
 * 
 * @author mengc
 * 
 */
public class MsgBuilder {

	/**
	 * 构造响应消息列表
	 * 
	 * @param fromType
	 * @param fromID
	 * @param toType
	 * @param toID
	 * @param msgType
	 * @param modelId
	 * @param cmdCode
	 * @param sysNumber
	 * @param sequense
	 * @param errorCode
	 * @param body 
	 * @return
	 */
	public static List<MsgOfResponse> buildResMsgList(int fromType, String fromID, 
			int toType, String toID, int msgType, int modelId, int cmdCode, 
			int sysNumber, String sequense, int errorCode, ICodeAble body) {
		MsgHead resHead = new MsgHead(fromType, fromID, toType, toID, msgType, 
				modelId, cmdCode, sysNumber, sequense, errorCode);
		MsgOfResponse resMsg = new MsgOfResponse();
		resMsg.setMsgBody(body);
		resMsg.setMsgHead(resHead);
		List<MsgOfResponse> resList = new ArrayList<MsgOfResponse>();
		resList.add(resMsg);
		return resList;
	}

	
	/**
	 * 构造广播消息列表
	 * 
	 * @param fromType
	 * @param fromID
	 * @param toType
	 * @param toID
	 * @param msgType
	 * @param modelId
	 * @param cmdCode
	 * @param sysNumber
	 * @param sequense
	 * @param errorCode
	 * @param body 
	 * @return
	 */
	public static List<MsgOfNotify> buildNotifyMsgList(int fromType, String fromID, 
			int toType, String toID, int msgType, int modelId, int cmdCode, 
			int sysNumber, String sequense, int errorCode, ICodeAble body) {
		MsgHead resHead = new MsgHead(fromType, fromID, toType, toID, msgType, 
				modelId, cmdCode, sysNumber, sequense, errorCode);
		MsgOfNotify resMsg = new MsgOfNotify();
		resMsg.setMsgBody(body);
		resMsg.setMsgHead(resHead);
		List<MsgOfNotify> resList = new ArrayList<MsgOfNotify>();
		resList.add(resMsg);
		return resList;
	}
	
	/**
	 * 构造请求消息列表
	 * 
	 * @param fromType
	 * @param fromID
	 * @param toType
	 * @param toID
	 * @param msgType
	 * @param modelId
	 * @param cmdCode
	 * @param sysNumber
	 * @param sequense
	 * @param errorCode
	 * @param body 
	 * @return
	 */
	public static List<Msg> buildReqMsgList(int fromType, String fromID, 
			int toType, String toID, int msgType, int modelId, int cmdCode, 
			int sysNumber, String sequense, int errorCode, String userSequense, ICodeAble body) {
		MsgHead reqHead = new MsgHead(fromType, fromID, toType, toID, msgType, 
				modelId, cmdCode, sysNumber, sequense, errorCode);
		reqHead.setUserSequense(userSequense);
		Msg reqMsg = new Msg();
		reqMsg.setMsgBody(body);
		reqMsg.setMsgHead(reqHead);
		List<Msg> reqList = new ArrayList<Msg>();
		reqList.add(reqMsg);
		return reqList;
	}
	
	/**
	 * 构造请求消息列表
	 * 
	 * @param fromType
	 * @param fromID
	 * @param toType
	 * @param toID
	 * @param msgType
	 * @param modelId
	 * @param cmdCode
	 * @param sysNumber
	 * @param sequense
	 * @param errorCode
	 * @param body 
	 * @return
	 */
	public static Msg buildMsg(int fromType, String fromID,
			int toType, String toID, int msgType, int modelId, int cmdCode,
			int sysNumber, String sequense, int errorCode,String userSequence,ICodeAble body) {
		MsgHead resHead = new MsgHead(fromType, fromID, toType, toID, msgType,
				modelId, cmdCode, sysNumber, sequense, errorCode);
		resHead.setUserSequense(userSequence);
		Msg resMsg = new Msg();
		resMsg.setMsgBody(body);
		resMsg.setMsgHead(resHead);
		return resMsg;
	}
}
