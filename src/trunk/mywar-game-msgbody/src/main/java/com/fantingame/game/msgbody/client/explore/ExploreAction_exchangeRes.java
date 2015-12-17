package com.fantingame.game.msgbody.client.explore;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;

/**兑换英雄**/
public class ExploreAction_exchangeRes implements ICodeAble {

		/**通用奖励对象**/
	private CommonGoodsBeanBO drop=null;
	/**用户剩余积分**/
	private Integer integral=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		drop.encode(outputStream);

		outputStream.writeInt(integral);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);

		integral = inputStream.readInt();


	}
	
		/**通用奖励对象**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**通用奖励对象**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}
	/**用户剩余积分**/
    public Integer getIntegral() {
		return integral;
	}
	/**用户剩余积分**/
    public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	
	
}
