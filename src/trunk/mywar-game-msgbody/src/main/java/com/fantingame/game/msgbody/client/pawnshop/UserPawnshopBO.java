package com.fantingame.game.msgbody.client.pawnshop;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**当铺商品信息对象**/
public class UserPawnshopBO implements ICodeAble {

		/**商品id**/
	private Integer mallId=0;
	/**商品数量**/
	private Integer num=0;
	/**浮动值**/
	private Integer floatValue=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mallId);

		outputStream.writeInt(num);

		outputStream.writeInt(floatValue);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mallId = inputStream.readInt();

		num = inputStream.readInt();

		floatValue = inputStream.readInt();


	}
	
		/**商品id**/
    public Integer getMallId() {
		return mallId;
	}
	/**商品id**/
    public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}
	/**商品数量**/
    public Integer getNum() {
		return num;
	}
	/**商品数量**/
    public void setNum(Integer num) {
		this.num = num;
	}
	/**浮动值**/
    public Integer getFloatValue() {
		return floatValue;
	}
	/**浮动值**/
    public void setFloatValue(Integer floatValue) {
		this.floatValue = floatValue;
	}

	
	
}
