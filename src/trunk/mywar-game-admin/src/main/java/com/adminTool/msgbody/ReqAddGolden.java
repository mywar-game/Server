package com.adminTool.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/* 请求刷新玩家金币,cmdCode = 6010  */
public class ReqAddGolden implements ICodeAble {

	/** 用户编号 **/
	private String userId = new String();
	/** 增加金币数量 **/
	private int golden;

	public ReqAddGolden() {
	}

	public ReqAddGolden(String userId, int golden) {
		this.userId = userId;
		this.golden = golden;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userId = dataInputStream.readUTF();
		golden = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userId);
		dataOutputStream.writeInt(golden);
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setGolden(int golden) {
		this.golden = golden;
	}

	public int getGolden() {
		return golden;
	}

}