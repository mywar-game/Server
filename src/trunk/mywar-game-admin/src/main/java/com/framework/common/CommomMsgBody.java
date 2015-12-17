package com.framework.common;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

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
}
