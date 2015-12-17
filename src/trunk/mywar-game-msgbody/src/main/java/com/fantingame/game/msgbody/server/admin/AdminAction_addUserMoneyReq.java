package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**给用户发钻石**/
public class AdminAction_addUserMoneyReq implements ICodeAble {

		/**用户Ids**/
	private String userIds="";
	/**钻石**/
	private Integer diamond=0;
	/**是否发送全服**/
	private Boolean isSendAll=false;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userIds);

		outputStream.writeInt(diamond);

		outputStream.writeBoolean(isSendAll);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userIds = inputStream.readUTF();

		diamond = inputStream.readInt();

		isSendAll = inputStream.readBoolean();


	}
	
		/**用户Ids**/
    public String getUserIds() {
		return userIds;
	}
	/**用户Ids**/
    public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	/**钻石**/
    public Integer getDiamond() {
		return diamond;
	}
	/**钻石**/
    public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	/**是否发送全服**/
    public Boolean getIsSendAll() {
		return isSendAll;
	}
	/**是否发送全服**/
    public void setIsSendAll(Boolean isSendAll) {
		this.isSendAll = isSendAll;
	}

	
	
}
