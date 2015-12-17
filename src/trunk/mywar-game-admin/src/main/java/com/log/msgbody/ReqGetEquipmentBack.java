package com.log.msgbody;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.*;
import java.io.IOException;

import com.adminTool.msgbody.UserEquipmentSomeInfo;

/* ReqGetEquipmentBack请求消息体,cmdCode=6203  */
public class ReqGetEquipmentBack implements ICodeAble {

	/**  用户装备信息 **/
	private UserEquipmentSomeInfo userEquipmentSomeInfo= new UserEquipmentSomeInfo() ;

	public ReqGetEquipmentBack(){}

	public ReqGetEquipmentBack(UserEquipmentSomeInfo userEquipmentSomeInfo){
		this.userEquipmentSomeInfo=userEquipmentSomeInfo;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
//		userEquipmentSomeInfo.decode(dataInputStream);
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
//		userEquipmentSomeInfo.encode(dataOutputStream);
	}

	/**
	 * 获取 用户装备信息 
	 */
	public UserEquipmentSomeInfo getUserEquipmentSomeInfo() {
		return userEquipmentSomeInfo;
	}

	/**
	 * 设置 用户装备信息 
	 */
	public void setUserEquipmentSomeInfo(UserEquipmentSomeInfo userEquipmentSomeInfo) {
		this.userEquipmentSomeInfo = userEquipmentSomeInfo;
	}

}