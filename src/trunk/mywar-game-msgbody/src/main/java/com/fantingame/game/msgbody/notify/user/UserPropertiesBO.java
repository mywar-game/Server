package com.fantingame.game.msgbody.notify.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户属性对象信息**/
public class UserPropertiesBO implements ICodeAble {

		/**1人民币、2体力、3活力、4vip经验、5vip等级、6体力下次恢复时间、7活力下次恢复时间**/
	private Integer key=0;
	/**当前该属性的最新值，客户端覆盖该值即可**/
	private Long value=0l;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(key);

		outputStream.writeLong(value);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		key = inputStream.readInt();

		value = inputStream.readLong();


	}
	
		/**1人民币、2体力、3活力、4vip经验、5vip等级、6体力下次恢复时间、7活力下次恢复时间**/
    public Integer getKey() {
		return key;
	}
	/**1人民币、2体力、3活力、4vip经验、5vip等级、6体力下次恢复时间、7活力下次恢复时间**/
    public void setKey(Integer key) {
		this.key = key;
	}
	/**当前该属性的最新值，客户端覆盖该值即可**/
    public Long getValue() {
		return value;
	}
	/**当前该属性的最新值，客户端覆盖该值即可**/
    public void setValue(Long value) {
		this.value = value;
	}

	
	
}
