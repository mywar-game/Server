package com.framework.server.msg.manager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.framework.server.io.XIOFactoryManager;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.Msg;
import com.framework.server.msg.MsgGroup;
import com.framework.server.msg.model.SynList;


/**
 * 
 * 
 * @author mengchao
 * 
 */
public class MsgManageTool {

	/**
	 * 
	 * 
	 * @param responseMsgs
	 * @return
	 * @throws IOException
	 */
	public static byte[] saveResponseMsgs(List<Msg> responseMsgs, int type)
			throws IOException {
		if (responseMsgs == null || responseMsgs.size() == 0) {
			return null;
		}
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		IXOutStream ixOutStream = XIOFactoryManager.getIoFactoryByKey(type).getIXOutStream();
		ixOutStream.setOutputStream(byteOutputStream);
		SynList<Msg> msgsList = new SynList<Msg>();
		msgsList.addAll(responseMsgs);
		MsgGroup group = new MsgGroup();
		group.setMsgsList(msgsList);
		group.encode(ixOutStream);
		byte[] cache = byteOutputStream.toByteArray();
		ixOutStream.close();
		return cache;
	}
}
