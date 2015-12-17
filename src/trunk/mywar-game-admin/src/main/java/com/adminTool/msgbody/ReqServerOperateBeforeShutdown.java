package com.adminTool.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* 关服前操作请求消息体cmdCode=6204  */
public class ReqServerOperateBeforeShutdown implements ICodeAble {

	/**  操作步骤 **/
	private int operateNum;

	public ReqServerOperateBeforeShutdown(){}

	public ReqServerOperateBeforeShutdown(int operateNum){
		this.operateNum=operateNum;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		operateNum = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(operateNum);
	}

	public void setOperateNum(int operateNum) {
		this.operateNum = operateNum;
	}

	public int getOperateNum() {
		return operateNum;
	}

}