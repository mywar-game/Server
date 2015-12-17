package com.stats.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* 管理后台查询task执行情况,cmdCode=6022  */
public class ReqStatisticsMsg implements ICodeAble {

	/**  类型;0.关闭1.开启 **/
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