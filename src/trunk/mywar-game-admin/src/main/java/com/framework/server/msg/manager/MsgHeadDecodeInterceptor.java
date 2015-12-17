package com.framework.server.msg.manager;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.framework.log.LogSystem;
import com.framework.server.channle.AbstractChnnel;
import com.framework.server.channle.Channel;
import com.framework.server.io.XIOFactoryManager;
import com.framework.server.io.iface.IXInputStream;
import com.framework.server.msg.Msg;
import com.framework.server.msg.MsgGroup;
import com.framework.server.msg.model.SynList;
/**
 * 消息头解析
 * @author mengc
 *
 */


public class MsgHeadDecodeInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Object[] args = methodInvocation.getArguments();
		Channel channel = (Channel) args[0];
		ByteToMsg byteToMsg = (ByteToMsg) args[1];

		byte[] reqDatas = byteToMsg.getDatas();
		
		boolean isDecodeSucess = true;
		IXInputStream inputStream = XIOFactoryManager.getIoFactoryByKey(channel.getClientType()).getIXInputStream();
		ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(reqDatas);
		inputStream.setInputStream(arrayInputStream);
		MsgGroup msgGroup = new MsgGroup();
		try {
			msgGroup.decode(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "解流错误！");
			isDecodeSucess = false;
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LogSystem.error(e, "解流错误！");
				isDecodeSucess = false;
			}
		}
		if (isDecodeSucess) {
			SynList<Msg> msgVector = msgGroup.getMsgsList();
			byteToMsg.setMsgVector(msgVector);		
			if (msgVector.get(0).getMsgHead().getUserSequense() != null && Integer.valueOf(msgVector.get(0).getMsgHead().getUserSequense()) != 0) {
				channel.addAttribute(AbstractChnnel.USER_SEQUENSE, Long
						.valueOf(msgVector.get(0).getMsgHead()
								.getUserSequense()));
			}
			methodInvocation.proceed();
		} else {
			channel.close();
		}
		return null;
	}

}
