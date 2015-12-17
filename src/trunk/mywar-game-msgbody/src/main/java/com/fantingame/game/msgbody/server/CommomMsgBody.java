package com.fantingame.game.msgbody.server;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

public class CommomMsgBody implements ICodeAble {
	private int errorCode;
	private String errorDescription = new String("");

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public void decode(IXInputStream inputStream) throws IOException {
		// TODO Auto-generated method stub
		errorCode = inputStream.readInt();
		errorDescription = inputStream.readUTF();
	}

	public void encode(IXOutStream outputStream) throws IOException {
		// TODO Auto-generated method stub
		outputStream.writeInt(errorCode);
		outputStream.writeUTF(errorDescription);
	}
	
	public String toString(){
		return "errorCode="+errorCode+",errorDescription="+errorDescription;
	}
}
