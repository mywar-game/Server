package com.fantingame.game.msgbody.client.pawnshop;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**卖出**/
public class PawnshopAction_sellReq implements ICodeAble {

		/**卖出的商品id**/
	private Integer mallId=0;
	/**买入数量**/
	private Integer num=0;
	/**阵营**/
	private Integer camp=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mallId);

		outputStream.writeInt(num);

		outputStream.writeInt(camp);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mallId = inputStream.readInt();

		num = inputStream.readInt();

		camp = inputStream.readInt();


	}
	
		/**卖出的商品id**/
    public Integer getMallId() {
		return mallId;
	}
	/**卖出的商品id**/
    public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}
	/**买入数量**/
    public Integer getNum() {
		return num;
	}
	/**买入数量**/
    public void setNum(Integer num) {
		this.num = num;
	}
	/**阵营**/
    public Integer getCamp() {
		return camp;
	}
	/**阵营**/
    public void setCamp(Integer camp) {
		this.camp = camp;
	}

	
	
}
