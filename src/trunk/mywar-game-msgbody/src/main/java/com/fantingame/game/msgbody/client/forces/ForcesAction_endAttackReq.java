package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**攻击关卡结束**/
public class ForcesAction_endAttackReq implements ICodeAble {

		/**-1输,0平局,1赢,如果为采集关卡直接传1即可**/
	private Integer flag=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(flag);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		flag = inputStream.readInt();


	}
	
		/**-1输,0平局,1赢,如果为采集关卡直接传1即可**/
    public Integer getFlag() {
		return flag;
	}
	/**-1输,0平局,1赢,如果为采集关卡直接传1即可**/
    public void setFlag(Integer flag) {
		this.flag = flag;
	}

	
	
}
