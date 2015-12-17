package com.fantingame.game.msgbody.client.rank;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户排行榜信息**/
public class RankAction_getUserRankInfoReq implements ICodeAble {

		/**排行榜类型1等级2战斗力3土豪榜**/
	private Integer type=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(type);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		type = inputStream.readInt();


	}
	
		/**排行榜类型1等级2战斗力3土豪榜**/
    public Integer getType() {
		return type;
	}
	/**排行榜类型1等级2战斗力3土豪榜**/
    public void setType(Integer type) {
		this.type = type;
	}

	
	
}
