package com.fantingame.game.msgbody.client.activity;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**领取每月签到奖励**/
public class ActivityAction_receiveLoginReward30Req implements ICodeAble {

		/**领取第几天**/
	private Integer day=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(day);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		day = inputStream.readInt();


	}
	
		/**领取第几天**/
    public Integer getDay() {
		return day;
	}
	/**领取第几天**/
    public void setDay(Integer day) {
		this.day = day;
	}

	
	
}
