package com.log.msgbody;

import java.io.IOException;

import com.adminTool.msgbody.UserHeroSomeInfo;
import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/* ReqGetHeroBack请求消息体,cmdCode=6202  */
public class ReqGetHeroBack implements ICodeAble {

	/**  用户英雄信息 **/
	private UserHeroSomeInfo userHeroSomeInfo= new UserHeroSomeInfo() ;

	public ReqGetHeroBack(){}

	public ReqGetHeroBack(UserHeroSomeInfo userHeroSomeInfo){
		this.userHeroSomeInfo=userHeroSomeInfo;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
//		userHeroSomeInfo.decode(dataInputStream);
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
//		userHeroSomeInfo.encode(dataOutputStream);
	}

	public void setUserHeroSomeInfo(UserHeroSomeInfo userHeroSomeInfo) {
		this.userHeroSomeInfo = userHeroSomeInfo;
	}

	public UserHeroSomeInfo getUserHeroSomeInfo() {
		return userHeroSomeInfo;
	}

}