package com.fantingame.game.msgbody.server;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

import java.io.IOException;

/* 管理后台查询task执行情况,cmdCode=6022  */
public class ReqStatisticsMsg implements ICodeAble {

	/**  类型;1.查询2.开启3.关闭 **/
	private int type;

	public ReqStatisticsMsg(){}

	public ReqStatisticsMsg(int type){
		this.type=type;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		type = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(type);
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

}