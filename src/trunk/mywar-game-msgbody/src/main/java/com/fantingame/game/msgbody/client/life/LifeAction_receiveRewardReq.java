package com.fantingame.game.msgbody.client.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**领取挂机奖励**/
public class LifeAction_receiveRewardReq implements ICodeAble {

		/**类别（1矿场2花圃3渔场）**/
	private Integer category=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(category);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		category = inputStream.readInt();


	}
	
		/**类别（1矿场2花圃3渔场）**/
    public Integer getCategory() {
		return category;
	}
	/**类别（1矿场2花圃3渔场）**/
    public void setCategory(Integer category) {
		this.category = category;
	}

	
	
}
