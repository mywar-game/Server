package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;

/**攻击关卡结束**/
public class ForcesAction_endAttackRes implements ICodeAble {

		/**获得的物品**/
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
	
		/**获得的物品**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**获得的物品**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}

	
	
}
