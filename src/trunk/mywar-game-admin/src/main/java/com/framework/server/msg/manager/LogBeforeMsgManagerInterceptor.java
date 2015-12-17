package com.framework.server.msg.manager;

import java.lang.reflect.Method;
import java.util.Vector;

import org.springframework.aop.MethodBeforeAdvice;

import com.framework.log.LogSystem;
import com.framework.server.msg.Msg;
import com.framework.server.msg.MsgHead;

/**
 * 在消息处理之前，log消息头
 * 
 * @author mengchao
 * 
 */
public class LogBeforeMsgManagerInterceptor implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		ByteToMsg byteToMsg = (ByteToMsg) args[1];
		Vector<Msg> msgVector = byteToMsg.getMsgVector();
		Msg clientMsg = msgVector.get(0);
		MsgHead head = clientMsg.getMsgHead();
		LogSystem.info("before msg manage, the head is:" + head.toString() + "msg type" + head.getMsgType());
	}

}
