package com.fantingame.game.msgbody.client.mall;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;

/**回购**/
public class MallAction_buyBackRes implements ICodeAble {

		/**公共物品掉落**/
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
	
		/**公共物品掉落**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**公共物品掉落**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}

	
	
}
