package com.framework.manager.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.Msg;
import com.framework.server.msg.model.ICodeAble;

public class ReqRoutMsg implements ICodeAble {

	private Msg msg;
	public void decode(IXInputStream inputStream) throws IOException {
		// TODO Auto-generated method stub
		msg.decode(inputStream);
	}

	public void encode(IXOutStream outputStream) throws IOException {
		// TODO Auto-generated method stub
		msg.encode(outputStream);
	}

	public Msg getMsg() {
		return msg;
	}

	public void setMsg(Msg msg) {
		this.msg = msg;
	}

}
