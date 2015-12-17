package com.stats.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* 管理后台查询task执行情况,cmdCode=6023  */
public class MsgBean implements ICodeAble {

	/**  执行次数 **/
	private String msgNum= new String() ;
	/**  持续时间 **/
	private String stataTime= new String() ;
	/**  每次执行需要的平均时间 **/
	private int continueTime;
	/**  当前是否开启:1开启2关闭 **/
	private int showType;

	public MsgBean(){}

	public MsgBean(String msgNum , String stataTime){
		this.msgNum=msgNum;
		this.stataTime=stataTime;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		msgNum = dataInputStream.readUTF();
		stataTime = dataInputStream.readUTF();
		showType = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(msgNum);
		dataOutputStream.writeUTF(stataTime);
		dataOutputStream.writeInt(showType);
	}

	public void setMsgNum(String msgNum) {
		this.msgNum = msgNum;
	}

	public String getMsgNum() {
		return msgNum;
	}

	public void setStataTime(String stataTime) {
		this.stataTime = stataTime;
	}

	public String getStataTime() {
		return stataTime;
	}

	public int getContinueTime() {
		return continueTime;
	}

	public void setContinueTime(int continueTime) {
		this.continueTime = continueTime;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}

}