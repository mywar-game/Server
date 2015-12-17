package com.framework.server.msg.manager;


import com.framework.server.msg.Msg;
import com.framework.server.msg.model.SynList;


public class ByteToMsg {
	
	private byte[] datas;
	
	private SynList<Msg> msgVector;
	
	public ByteToMsg(byte[] datas) {
		super();
		this.datas = datas;
	}

	public byte[] getDatas() {
		return datas;
	}

	public SynList<Msg> getMsgVector() {
		return msgVector;
	}

	public void setMsgVector(SynList<Msg> msgVector) {
		this.msgVector = msgVector;
	}
	
}
