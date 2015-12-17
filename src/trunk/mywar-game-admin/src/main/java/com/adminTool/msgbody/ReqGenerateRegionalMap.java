package com.adminTool.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/* 生成区域地图请求消息体cmdCode = 6015  */
public class ReqGenerateRegionalMap implements ICodeAble {

	/**  起始页 **/
	private int startPage;
	/**  结束页 **/
	private int endPage;

	public ReqGenerateRegionalMap() {
	}

	public ReqGenerateRegionalMap(int startPage , int endPage) {
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		startPage = dataInputStream.readInt();
		endPage = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(startPage);
		dataOutputStream.writeInt(endPage);
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getEndPage() {
		return endPage;
	}

}