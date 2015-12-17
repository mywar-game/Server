package com.framework.server.msg;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.framework.log.LogSystem;
import com.framework.server.io.XIOFactoryManager;
import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;


public class Msg implements ICodeAble {
    private MsgHead msgHead;
    private ICodeAble msgBody;
    private byte[] data;
	public MsgHead getMsgHead() {
		return msgHead;
	}

	public void setMsgHead(MsgHead msgHead) {
		this.msgHead = msgHead;
	}

	public ICodeAble getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(ICodeAble msgBody) {
		this.msgBody = msgBody;
	}

	public void decode(IXInputStream inputStream) throws IOException {
		// TODO Auto-generated method stub
		msgHead = new MsgHead();
		msgHead.decode(inputStream);
		int size = msgHead.getSizeOfMsgBody();
		if (size > 0) {
			data = new byte[size];
			inputStream.readFully(data, 0, size);
		}
	}

	public void encode(IXOutStream outputStream) throws IOException {
		// TODO Auto-generated method stub
		if (msgHead != null) {
			if (msgBody != null) {
				//设置msgHead里的sizeOfMsgBody变量
				IXOutStream temp = XIOFactoryManager.getIoFactoryByKey(XIOFactoryManager.getTypeByOutputStream(outputStream)).getIXOutStream();
				ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
				temp.setOutputStream(bytearray);
				msgBody.encode(temp);
				msgHead.setSizeOfMsgBody(bytearray.toByteArray().length);
				temp.close();
			} else {
				msgHead.setSizeOfMsgBody(0);
			}
			msgHead.encode(outputStream);
			if (msgBody != null) {
				msgBody.encode(outputStream);
			}
		}
	}
	/**
	 * 解析消息体
	 * @param codeAble
	 */
	public void decodeBody(ICodeAble codeAble, int type) {
		msgBody = codeAble;
		if (data != null && data.length > 0) {
		IXInputStream inputStream = XIOFactoryManager.getIoFactoryByKey(type).getIXInputStream();
		ByteArrayInputStream bytearray = new ByteArrayInputStream(data);
		inputStream.setInputStream(bytearray);
		try {
			msgBody.decode(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "decodeBody ERROR");
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LogSystem.error(e, "decodeBody ERROR");
			}
		}
	 }
	}
}
