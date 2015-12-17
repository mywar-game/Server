package com.fantingame.game.msgbody.server.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

/** 发送道具或装备 **/
public class AdminAction_addEquipmentOrToolsRes implements ICodeAble {

	/** 返回结果 **/
	private Map<String, List<AdminSendAttachBO>> result = null;

	@Override
	public void encode(IXOutStream outputStream) throws IOException {

		if (result == null || result.size() == 0) {
			outputStream.writeInt(0);
		} else {
			outputStream.writeInt(result.size());
		}

		if (result != null && result.size() > 0) {
			for (Entry<String, List<AdminSendAttachBO>> entry : result
					.entrySet()) {
				outputStream.writeUTF(entry.getKey());
				List<AdminSendAttachBO> list = result.get(entry.getKey());

				if (list != null && list.size() > 0) {
					outputStream.writeInt(list.size());

					for (int i = 0; i < list.size(); i++) {
						list.get(i).encode(outputStream);
					}
				} else {
					outputStream.writeInt(0);
				}
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {

		int resultSize = inputStream.readInt();
		if (resultSize > 0) {
			result = new HashMap<String, List<AdminSendAttachBO>>();
			for (int resultCursor = 0; resultCursor < resultSize; resultCursor++) {
				String key = inputStream.readUTF();
				int listSize = inputStream.readInt();
				if (listSize > 0) {
					List<AdminSendAttachBO> list = new ArrayList<AdminSendAttachBO>();
					for (int i = 0; i < listSize; i++) {
						AdminSendAttachBO entry = new AdminSendAttachBO();
						entry.decode(inputStream);
						list.add(entry);
					}
					result.put(key, list);
				}
			}
		}
	}

	/** 返回结果 **/
	public Map<String, List<AdminSendAttachBO>> getResult() {
		return result;
	}

	/** 返回结果 **/
	public void setResult(Map<String, List<AdminSendAttachBO>> result) {
		this.result = result;
	}

}