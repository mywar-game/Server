package com.framework.manager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.server.io.XIOFactoryManager;
import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.Msg;
import com.framework.server.msg.MsgGroup;
import com.framework.server.msg.model.ICodeAble;
import com.framework.server.msg.model.SynList;

/**
 * 服务器之间的http通讯
 * 
 * @author Administrator
 * 
 */
public class ServersBridge {

	public static List<Msg> sendMsgsToServer(String address, 
			List<Msg> msgList, Class<? extends ICodeAble> resBodyType) {
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
			Class<? extends ICodeAble> resBodyType) throws IOException, 
			IllegalArgumentException, SecurityException, 
			InstantiationException, IllegalAccessException, 
			InvocationTargetException, NoSuchMethodException {
		
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream cacheOutputStream = new DataOutputStream(
				arrayOutputStream);

		byte[] bytes = new byte[256];
		int len = 0;
		while (true) {
			len = inputStream.read(bytes, 0, 256);
			cacheOutputStream.write(bytes);
			if (len <= 256 && inputStream.available() == 0) {
				break;
			}
		}

		ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(
				arrayOutputStream.toByteArray());
		IXInputStream inputStream2 = XIOFactoryManager.getIoFactoryByKey(SystemConstant.JAVA_CLIENT).getIXInputStream();
		inputStream2.setInputStream(arrayInputStream);
		MsgGroup resGroup = new MsgGroup();
		resGroup.decode(inputStream2);
		SynList<Msg> resVector = resGroup.getMsgsList();
		for (Msg msg : resVector) {
			ICodeAble resBody = resBodyType.getConstructor().newInstance();
			msg.decodeBody(resBody, SystemConstant.JAVA_CLIENT);
			msg.setMsgBody(resBody);
		}
		List<Msg> resList = new ArrayList<Msg>();
		resList.addAll(resVector);
		return resList;
	}
	
	public static void main(String[] args) {
//		bodyExample b = new bodyExample();
//		b.setName("123");
//		List<Msg> list = MsgBuilder.buildMsgList(0, "0", 0, "0", 1, 101, 1001, 10, "0", 0, b);
//		
//		sendMsgsToServer("http://192.168.110.188:8080/XXGameServer/servlet/HttpTransfer", list, CommomMsgBody.class);
	}
}
