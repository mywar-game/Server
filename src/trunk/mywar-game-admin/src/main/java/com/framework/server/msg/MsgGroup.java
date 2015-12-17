package com.framework.server.msg; 

import java.io.IOException; 

import com.framework.server.io.iface.IXInputStream; 
import com.framework.server.io.iface.IXOutStream; 
import com.framework.server.msg.model.ICodeAble; 
import com.framework.server.msg.model.SynList; 

public class MsgGroup implements ICodeAble {
    private int sizeOfMsg; 
    private SynList<Msg> msgsList; 
	public SynList<Msg> getMsgsList() {
		return msgsList; 
	}

	public void setMsgsList(SynList<Msg> msgsList) {
		this.msgsList = msgsList; 
	}

	public void decode(IXInputStream inputStream) throws IOException {
		// TODO Auto-generated method stub
//		sizeOfMsgGroup = inputStream.readInt(); 
		sizeOfMsg = inputStream.readInt(); 
		msgsList = new SynList<Msg>(); 
		for (int i = 0; i < sizeOfMsg; i++) {
			Msg temp = new Msg(); 
			temp.decode(inputStream); 
			msgsList.add(temp); 
		}
	}

	public void encode(IXOutStream outputStream) throws IOException {
		// TODO Auto-generated method stub
		sizeOfMsg = msgsList.size(); 
		if (sizeOfMsg > 0) {
			outputStream.writeInt(sizeOfMsg); 
			for (Msg msg:msgsList) {
				msg.encode(outputStream); 
			}
		}
	}
	
	public int getSizeOfMsg() {
		return sizeOfMsg; 
	}

	public void setSizeOfMsg(int sizeOfMsg) {
		this.sizeOfMsg = sizeOfMsg; 
	}
}
