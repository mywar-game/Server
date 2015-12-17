package com.fantingame.game.msgbody.client.activity;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;

/**领取每月签到奖励**/
public class ActivityAction_receiveLoginReward30Res implements ICodeAble {

		/**得到的奖励对象**/
	private CommonGoodsBeanBO drop=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		drop.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);


	}
	
		/**得到的奖励对象**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**得到的奖励对象**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}

	
	
}
