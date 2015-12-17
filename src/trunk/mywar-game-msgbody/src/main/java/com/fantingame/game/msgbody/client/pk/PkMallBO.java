package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**荣誉兑换对象**/
public class PkMallBO implements ICodeAble {

		/**商品id**/
	private Integer mallId=0;
	/**每日已购买次数**/
	private Integer dayBuyNum=0;
	/**总共已购买次数**/
	private Integer totalBuyNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mallId);

		outputStream.writeInt(dayBuyNum);

		outputStream.writeInt(totalBuyNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mallId = inputStream.readInt();

		dayBuyNum = inputStream.readInt();

		totalBuyNum = inputStream.readInt();


	}
	
		/**商品id**/
    public Integer getMallId() {
		return mallId;
	}
	/**商品id**/
    public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}
	/**每日已购买次数**/
    public Integer getDayBuyNum() {
		return dayBuyNum;
	}
	/**每日已购买次数**/
    public void setDayBuyNum(Integer dayBuyNum) {
		this.dayBuyNum = dayBuyNum;
	}
	/**总共已购买次数**/
    public Integer getTotalBuyNum() {
		return totalBuyNum;
	}
	/**总共已购买次数**/
    public void setTotalBuyNum(Integer totalBuyNum) {
		this.totalBuyNum = totalBuyNum;
	}

	
	
}
