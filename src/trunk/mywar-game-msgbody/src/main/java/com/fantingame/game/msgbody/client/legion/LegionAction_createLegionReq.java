package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**创建军团**/
public class LegionAction_createLegionReq implements ICodeAble {

		/**军团名称**/
	private String legionName="";
	/**创建军团花费类型，1钻石2金币**/
	private Integer type=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(legionName);

		outputStream.writeInt(type);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		legionName = inputStream.readUTF();

		type = inputStream.readInt();


	}
	
		/**军团名称**/
    public String getLegionName() {
		return legionName;
	}
	/**军团名称**/
    public void setLegionName(String legionName) {
		this.legionName = legionName;
	}
	/**创建军团花费类型，1钻石2金币**/
    public Integer getType() {
		return type;
	}
	/**创建军团花费类型，1钻石2金币**/
    public void setType(Integer type) {
		this.type = type;
	}

	
	
}
