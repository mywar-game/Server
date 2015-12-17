package com.framework.server.msg;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;



public class MsgHead implements ICodeAble {
	public MsgHead(int fromType, String fromID, int toType, String toID,
			int msgType, int modelId, int cmdCode, int sysNumber,
			String sequense, int errorCode) {
		super();
		this.fromType = fromType;
		this.fromID = fromID;
		this.toType = toType;
		this.toID = toID;
		this.msgType = msgType;
		this.modelId = modelId;
		this.cmdCode = cmdCode;
		this.sysNumber = sysNumber;
		this.sequense = sequense;
		this.errorCode = errorCode;
	}

	public MsgHead() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 消息类型
	 */
	public static final int TYPEOFREQUEST = 1;

	public static final int TYPEOFRESPONSE = 2;

	public static final int TYPEOFNOTIFY = 3;

	/**
	 * 消息广播的类型
	 */
	public static final int SOURCE_USER = 0;

	public static final int SOURCE_NODE = 1;

	public static final int SOURCE_SYSTEM = 3;

	//消息来源类型
	private int fromType;
    //消息来源ID
	private String fromID = new String("0");
    //消息目的类型
	private int toType;
    //消息目的ID
	private String toID = new String("0");
    //消息类型
	private int msgType;
    //模块编号
	private int modelId;
    //命令码
	private int cmdCode;
    //目的系统编号
	private int sysNumber;
    //消息序列号
	private String sequense = new String("0");
    //错误码
	private int errorCode;
    //消息体大小
	private int sizeOfMsgBody;
	//用户序列号
	private String userSequense = new String("0");
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof MsgHead) {
			MsgHead m = (MsgHead) obj;
			if (m.getCmdCode() == cmdCode) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return cmdCode;
	}
	public void decode(IXInputStream inputStream) throws IOException {
		errorCode = inputStream.readInt();
		fromType = inputStream.readInt();
		fromID = inputStream.readUTF();
		toType = inputStream.readInt();
		toID = inputStream.readUTF();
		msgType = inputStream.readInt();
		modelId = inputStream.readInt();
		cmdCode = inputStream.readInt();
		sysNumber = inputStream.readInt();
		sequense = inputStream.readUTF();
		userSequense = inputStream.readUTF();
		sizeOfMsgBody = inputStream.readInt();
		
		
//		System.out.println("decode errorCode" + errorCode);
//		System.out.println("decode fromType" + fromType);
//		System.out.println("decode fromID" + fromID);
//		System.out.println("decode toType" + toType);
//		System.out.println("decode toID" + toID);
//		System.out.println("decode msgType" + msgType);
//		System.out.println("decode modelId" + modelId);
//		System.out.println("decode cmdCode" + cmdCode);
//		System.out.println("decode sysNumber" + sysNumber);
//		System.out.println("decode sequense" + sequense);
//		System.out.println("decode userSequense" + userSequense);
//		System.out.println("decode sizeOfMsgBody" + sizeOfMsgBody);
	}

	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(errorCode);
		outputStream.writeInt(fromType);
		outputStream.writeUTF(fromID);
		outputStream.writeInt(toType);
		outputStream.writeUTF(toID);
		outputStream.writeInt(msgType);
		outputStream.writeInt(modelId);
		outputStream.writeInt(cmdCode);
		outputStream.writeInt(sysNumber);
		outputStream.writeUTF(sequense);
		outputStream.writeUTF(userSequense);
		outputStream.writeInt(sizeOfMsgBody);

//		System.out.println("encode errorCode" + errorCode);
//		System.out.println("encode fromType" + fromType);
//		System.out.println("encode fromID" + fromID);
//		System.out.println("encode toType" + toType);
//		System.out.println("encode toID" + toID);
//		System.out.println("encode msgType" + msgType);
//		System.out.println("encode modelId" + modelId);
//		System.out.println("encode cmdCode" + cmdCode);
//		System.out.println("encode sysNumber" + sysNumber);
//		System.out.println("encode sequense" + sequense);
//		System.out.println("encode userSequense" + userSequense);
//		System.out.println("encode sizeOfMsgBody" + sizeOfMsgBody);
	}

	public int getFromType() {
		return fromType;
	}

	public void setFromType(int fromType) {
		this.fromType = fromType;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public int getToType() {
		return toType;
	}

	public void setToType(int toType) {
		this.toType = toType;
	}

	public String getToID() {
		return toID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getCmdCode() {
		return cmdCode;
	}


	public int getSysNumber() {
		return sysNumber;
	}

	public void setSysNumber(int sysNumber) {
		this.sysNumber = sysNumber;
	}



	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getSizeOfMsgBody() {
		return sizeOfMsgBody;
	}

	public void setSizeOfMsgBody(int sizeOfMsgBody) {
		this.sizeOfMsgBody = sizeOfMsgBody;
	}


	
	public String getSequense() {
		return sequense;
	}

	public void setSequense(String sequense) {
		this.sequense = sequense;
	}

	public String getUserSequense() {
		return userSequense;
	}

	public void setUserSequense(String userSequense) {
		this.userSequense = userSequense;
	}

	public String toString() {
		return cmdCode + "";
	}

	public void setCmdCode(int cmdCode) {
		this.cmdCode = cmdCode;
	}

	public MsgHead(int fromType, String fromID, int toType, String toID,
			int msgType, int modelId, int cmdCode, int sysNumber,
			String sequense, int errorCode, String userSequense) {
		super();
		this.fromType = fromType;
		this.fromID = fromID;
		this.toType = toType;
		this.toID = toID;
		this.msgType = msgType;
		this.modelId = modelId;
		this.cmdCode = cmdCode;
		this.sysNumber = sysNumber;
		this.sequense = sequense;
		this.errorCode = errorCode;
		this.userSequense = userSequense;
	}
}
