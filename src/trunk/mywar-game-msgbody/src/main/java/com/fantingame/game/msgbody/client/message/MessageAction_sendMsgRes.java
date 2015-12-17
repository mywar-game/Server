package com.fantingame.game.msgbody.client.message;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;

/**发送跑马灯**/
public class MessageAction_sendMsgRes implements ICodeAble {

		/**消耗的道具**/
	private GoodsBeanBO goodsBeanBO=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		goodsBeanBO.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		goodsBeanBO=new GoodsBeanBO();    
		goodsBeanBO.decode(inputStream);


	}
	
		/**消耗的道具**/
    public GoodsBeanBO getGoodsBeanBO() {
		return goodsBeanBO;
	}
	/**消耗的道具**/
    public void setGoodsBeanBO(GoodsBeanBO goodsBeanBO) {
		this.goodsBeanBO = goodsBeanBO;
	}

	
	
}
