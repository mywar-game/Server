package com.fantingame.game.msgbody.client.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.life.UserLifeInfoBO;

/**领取挂机奖励**/
public class LifeAction_receiveRewardRes implements ICodeAble {

		/**掉落物品**/
	private CommonGoodsBeanBO drop=null;
	/**挂机信息**/
	private UserLifeInfoBO userLifeInfoBO=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		drop.encode(outputStream);

		userLifeInfoBO.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);

		userLifeInfoBO=new UserLifeInfoBO();    
		userLifeInfoBO.decode(inputStream);


	}
	
		/**掉落物品**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**掉落物品**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}
	/**挂机信息**/
    public UserLifeInfoBO getUserLifeInfoBO() {
		return userLifeInfoBO;
	}
	/**挂机信息**/
    public void setUserLifeInfoBO(UserLifeInfoBO userLifeInfoBO) {
		this.userLifeInfoBO = userLifeInfoBO;
	}

	
	
}
