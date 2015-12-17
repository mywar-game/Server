package com.fantingame.game.msgbody.client.setting;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**设置同屏显示人数**/
public class SettingAction_setDisplayNumRes implements ICodeAble {

		/**显示人数**/
	private Integer num=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(num);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		num = inputStream.readInt();


	}
	
		/**显示人数**/
    public Integer getNum() {
		return num;
	}
	/**显示人数**/
    public void setNum(Integer num) {
		this.num = num;
	}

	
	
}
