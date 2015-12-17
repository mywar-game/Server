package com.stats.msgbody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;
import com.stats.bean.Guild;

/* 所有军团及军团成员响应消息体,cmdCode = 6101  */
public class ResAllGuildAndGuildMembers implements ICodeAble {

	/** 所有军团 map(军团Id，军团信息) **/
	private Map<String, Guild> guildMap = new HashMap<String, Guild>();
	/** 军团所有玩家id map(军团id,军团userIds) **/
	private Map<String, String> guildUserIdsMap = new HashMap<String, String>();

	public ResAllGuildAndGuildMembers() {
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		int guildMapSize = dataInputStream.readInt();

		for (int i = 0; i < guildMapSize; i++) {
			String key = new String();
			key = dataInputStream.readUTF();
			Guild t = new Guild();
			t.decode(dataInputStream);
			guildMap.put(key, t);
		}

		int guildUserIdsMapSize = dataInputStream.readInt();

		for (int i = 0; i < guildUserIdsMapSize; i++) {
			String key = new String();
			key = dataInputStream.readUTF();
			String t = new String();
			t = dataInputStream.readUTF();
			guildUserIdsMap.put(key, t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		if (guildMap != null) {
			dataOutputStream.writeInt(guildMap.size());
			Iterator<String> it = guildMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				dataOutputStream.writeUTF(key);
				Guild t = (Guild) guildMap.get(key);
				t.encode(dataOutputStream);
			}
		} else {
			dataOutputStream.writeInt(0);
		}

		if (guildUserIdsMap != null) {
			dataOutputStream.writeInt(guildUserIdsMap.size());
			Iterator<String> it = guildUserIdsMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				dataOutputStream.writeUTF(key);
				String t = (String) guildUserIdsMap.get(key);
				dataOutputStream.writeUTF(t);
			}
		} else {
			dataOutputStream.writeInt(0);
		}

	}

	public void putGuildMap(String key, Guild value) {
		guildMap.put(key, value);
	}

	public void delGuildMap(String key) {
		guildMap.remove(key);
	}

	public void delAllGuildMap() {
		guildMap.clear();
	}

	public Guild getGuildMap(String key) {
		return (Guild) guildMap.get(key);
	}

	public void putGuildUserIdsMap(String key, String value) {
		guildUserIdsMap.put(key, value);
	}

	public void delGuildUserIdsMap(String key) {
		guildUserIdsMap.remove(key);
	}

	public void delAllGuildUserIdsMap() {
		guildUserIdsMap.clear();
	}

	public String getGuildUserIdsMap(String key) {
		return (String) guildUserIdsMap.get(key);
	}

	public Map<String, Guild> getGuildMap() {
		return guildMap;
	}

	public void setGuildMap(Map<String, Guild> guildMap) {
		this.guildMap = guildMap;
	}

	public Map<String, String> getGuildUserIdsMap() {
		return guildUserIdsMap;
	}

	public void setGuildUserIdsMap(Map<String, String> guildUserIdsMap) {
		this.guildUserIdsMap = guildUserIdsMap;
	}

}