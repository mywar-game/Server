package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;

/**结束采集**/
public class ForcesAction_endCollectRes implements ICodeAble {

		/**是否遇到怪物0没有1有**/
	private Integer isFightAgain=0;
	/**公共物品掉落**/
	private CommonGoodsBeanBO drop=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(isFightAgain);

		drop.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		isFightAgain = inputStream.readInt();

		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);


	}
	
		/**是否遇到怪物0没有1有**/
    public Integer getIsFightAgain() {
		return isFightAgain;
	}
	/**是否遇到怪物0没有1有**/
    public void setIsFightAgain(Integer isFightAgain) {
		this.isFightAgain = isFightAgain;
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
