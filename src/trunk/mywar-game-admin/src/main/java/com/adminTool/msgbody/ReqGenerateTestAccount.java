package com.adminTool.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

/* 后台管理生成测试账号请求消息体cmdCode=6014  */
public class ReqGenerateTestAccount implements ICodeAble {

	/**  生成个数 **/
	private int generateNum;
	/**区域编号**/
	private int areaId;
	public ReqGenerateTestAccount(){}

	public ReqGenerateTestAccount(int generateNum,int areaId){
		this.generateNum=generateNum;
		this.areaId = areaId;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		generateNum = dataInputStream.readInt();
		areaId = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(generateNum);
		dataOutputStream.writeInt(areaId);
	}

	public void setGenerateNum(int generateNum) {
		this.generateNum = generateNum;
	}

	public int getGenerateNum() {
		return generateNum;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

}