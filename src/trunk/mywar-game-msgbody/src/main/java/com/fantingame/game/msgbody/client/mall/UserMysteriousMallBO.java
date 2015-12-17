package com.fantingame.game.msgbody.client.mall;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户神秘商店商品对象**/
public class UserMysteriousMallBO implements ICodeAble {

		/**商品id**/
	private Integer mallId=0;
	/**购买状态0可购买1不可购买**/
	private Integer buyStatus=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mallId);

		outputStream.writeInt(buyStatus);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mallId = inputStream.readInt();

		buyStatus = inputStream.readInt();


	}
	
		/**商品id**/
    public Integer getMallId() {
		return mallId;
	}
	/**商品id**/
    public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}
	/**购买状态0可购买1不可购买**/
    public Integer getBuyStatus() {
		return buyStatus;
	}
	/**购买状态0可购买1不可购买**/
    public void setBuyStatus(Integer buyStatus) {
		this.buyStatus = buyStatus;
	}

	
	
}
