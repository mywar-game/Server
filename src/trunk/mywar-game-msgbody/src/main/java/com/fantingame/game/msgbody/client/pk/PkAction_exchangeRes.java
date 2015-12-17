package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;

/**兑换商品**/
public class PkAction_exchangeRes implements ICodeAble {

		/**剩余荣誉点**/
	private Integer honour=0;
	/**通用奖励对象**/
	private CommonGoodsBeanBO drop=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(honour);

		drop.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		honour = inputStream.readInt();

		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);


	}
	
		/**剩余荣誉点**/
    public Integer getHonour() {
		return honour;
	}
	/**剩余荣誉点**/
    public void setHonour(Integer honour) {
		this.honour = honour;
	}
	/**通用奖励对象**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**通用奖励对象**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}

	
	
}
