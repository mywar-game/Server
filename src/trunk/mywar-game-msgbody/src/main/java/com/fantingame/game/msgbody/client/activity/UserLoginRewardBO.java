package com.fantingame.game.msgbody.client.activity;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户登陆奖励对象**/
public class UserLoginRewardBO implements ICodeAble {

		/**天数**/
	private Integer day=0;
	/**领取状态0未领取1已领取**/
	private Integer status=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(day);

		outputStream.writeInt(status);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		day = inputStream.readInt();

		status = inputStream.readInt();


	}
	
		/**天数**/
    public Integer getDay() {
		return day;
	}
	/**天数**/
    public void setDay(Integer day) {
		this.day = day;
	}
	/**领取状态0未领取1已领取**/
    public Integer getStatus() {
		return status;
	}
	/**领取状态0未领取1已领取**/
    public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
