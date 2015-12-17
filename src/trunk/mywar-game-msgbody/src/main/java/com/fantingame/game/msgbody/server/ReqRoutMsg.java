package com.fantingame.game.msgbody.server;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;

public class ReqRoutMsg implements ICodeAble {

	private Msg msg = new Msg();
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
