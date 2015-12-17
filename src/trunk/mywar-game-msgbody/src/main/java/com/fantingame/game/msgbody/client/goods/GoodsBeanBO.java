package com.fantingame.game.msgbody.client.goods;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**通用奖励对象**/
public class GoodsBeanBO implements ICodeAble {

		/**奖励物品类型**/
	private Integer goodsType=0;
	/**奖励物品id**/
	private Integer goodsId=0;
	/**奖励物品数量**/
	private Integer goodsNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(goodsType);

		outputStream.writeInt(goodsId);

		outputStream.writeInt(goodsNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		goodsType = inputStream.readInt();

		goodsId = inputStream.readInt();

		goodsNum = inputStream.readInt();


	}
	
		/**奖励物品类型**/
    public Integer getGoodsType() {
		return goodsType;
	}
	/**奖励物品类型**/
    public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	/**奖励物品id**/
    public Integer getGoodsId() {
		return goodsId;
	}
	/**奖励物品id**/
    public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	/**奖励物品数量**/
    public Integer getGoodsNum() {
		return goodsNum;
	}
	/**奖励物品数量**/
    public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	
	
}
