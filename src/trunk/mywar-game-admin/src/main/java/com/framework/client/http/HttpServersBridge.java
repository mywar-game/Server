package com.framework.client.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.adminTool.constant.AdminErrorCode;
import com.example.msgbody.bodyExample;
import com.framework.common.CommomMsgBody;
import com.framework.common.MD5;
import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.server.io.XIOFactoryManager;
import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.Msg;
import com.framework.server.msg.MsgBuilder;
import com.framework.server.msg.MsgGroup;
import com.framework.server.msg.MsgHead;
import com.framework.server.msg.model.ICodeAble;
import com.framework.server.msg.model.SynList;
import com.framework.servicemanager.CustomerContextHolder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

/**
 * 服务器之间的http通讯
 * 
 * @author Administrator
 * 
 */
public class HttpServersBridge {
	
	/**
	 * 连接游戏服务器以执行task
	 * @return
	 */
	public static Msg connectServerToExecuteTask(int cmdCode, ICodeAble reqMsgBody, Object resMsgBodyType){
		TGameServer gameServer = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum());
		String md5str = MD5.md5Of16(cmdCode + gameServer.getServerCommunicationKey()); 
		List<Msg> reqMsgList = MsgBuilder.buildReqMsgList(0, "0", 1, "0", 1, 600, cmdCode, 0, "0", 0, md5str, reqMsgBody); 
		List<Msg> resMsgList = sendMsgsToServer(gameServer.getGameServerHttpUrl(), reqMsgList, resMsgBodyType); 
		return resMsgList.get(0);
	}

	private static List<Msg> sendMsgsToServer(String address, 
			List<Msg> msgList, Object resBodyType) {
		List<Msg> resList = null;
		try {
			URL url = new URL(address);
			URLConnection httpURLConnection = url.openConnection();
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			IXOutStream outStream = XIOFactoryManager.getIoFactoryByKey(SystemConstant.JAVA_CLIENT).getIXOutStream();
			
			outStream.setOutputStream(httpURLConnection.getOutputStream());

			// 发送消息
			sendMsgs(outStream, msgList);

			InputStream inputStream = httpURLConnection.getInputStream();
			resList = receiveMsgs(inputStream, resBodyType);
		} catch (Exception e) {
			Msg msg = new Msg();
			MsgHead msgHead = new MsgHead();
			msgHead.setErrorCode(SystemConstant.FAIL_CODE);
			msg.setMsgHead(msgHead);
			
			CommomMsgBody commomMsgBody = new CommomMsgBody();
			Integer errorCode;
			//如果连接不上游戏服务器
			if(e instanceof ConnectException){
				errorCode = AdminErrorCode.CAN_NOT_CONNECT;
			} else {
				errorCode = AdminErrorCode.UNDEFINE_ERROR;
			}
			commomMsgBody.setErrorCode(errorCode);
			
			ActionSupport actionSupport = new ActionSupport();
			if (ActionContext.getContext() != null) {
				commomMsgBody.setErrorDescription(actionSupport.getText("errorCode_"+errorCode)+" "+ e.toString());
			} else {
				commomMsgBody.setErrorDescription("errorCode_"+errorCode+" "+ e.toString());
			}
			msg.setMsgBody(commomMsgBody);
			
			resList = new ArrayList<Msg>();
			resList.add(msg);
			LogSystem.error(e, "");
		}
		return resList;
	}

	private static void sendMsgs(IXOutStream outStream, 
			List<Msg> msgList) {
		MsgGroup msgGroup = new MsgGroup();
		SynList<Msg> msgsList = new SynList<Msg>();
		msgsList.addAll(msgList);
		msgGroup.setMsgsList(msgsList);
		try {
			msgGroup.encode(outStream);
		} catch (IOException e) {
			LogSystem.error(e, "");
		}
	}

	private static List<Msg> receiveMsgs(InputStream inputStream, 
			Object resBodyType) throws IOException, 
			IllegalArgumentException, SecurityException, 
			InstantiationException, IllegalAccessException, 
			InvocationTargetException, NoSuchMethodException {
		
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream cacheOutputStream = new DataOutputStream(
				arrayOutputStream);

		byte[] buff = new byte[256];
//		int len = 0;
//		while (true) {
//			len = inputStream.read(bytes, 0, 256);
//			cacheOutputStream.write(bytes);
//			if (len <= 256 && inputStream.available() == 0) {
//				break;
//			}
//		}
		int size;
		while ((size = inputStream.read(buff, 0, 256)) > 0) {
			cacheOutputStream.write(buff, 0, size);
		}
		ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(
				arrayOutputStream.toByteArray());
		IXInputStream inputStream2 = XIOFactoryManager.getIoFactoryByKey(SystemConstant.JAVA_CLIENT).getIXInputStream();
		inputStream2.setInputStream(arrayInputStream);
		MsgGroup resGroup = new MsgGroup();
		resGroup.decode(inputStream2);
		SynList<Msg> resVector = resGroup.getMsgsList();
		for (Msg msg : resVector) {
			ICodeAble resBody = null;
			// 如果是错误信息 则要把错误信息解析出来
			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
				resBody = new CommomMsgBody();
			} else {
//				resBody = resBodyType.getConstructor().newInstance();
			}
			msg.decodeBody(resBody, SystemConstant.JAVA_CLIENT);
			msg.setMsgBody(resBody);
		}
		List<Msg> resList = new ArrayList<Msg>();
		resList.addAll(resVector);
		return resList;
	}
	
	public static void main(String[] args) {
		bodyExample b = new bodyExample();
		b.setName("123");
		String userSequnce = MD5.md5Of16(997 + "asdf@#$%ASS1546asdfok!@#$$$44211");
		List<Msg> list = MsgBuilder.buildReqMsgList(0, "0", 0, "0", 1, 101, 997, 10, "0", 0, userSequnce, b);
		
		sendMsgsToServer("http://localhost:8080/gameframe/servlet/HttpTransfer", list, CommomMsgBody.class);
	}
}
