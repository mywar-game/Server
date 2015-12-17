package com.fantingame.game.msgbody.client.setting;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**设置用户每日提示**/
public class SettingAction_setDailyTipsReq implements ICodeAble {

		/**提示编号**/
	private Integer tip=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(tip);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		tip = inputStream.readInt();


	}
	
		/**提示编号**/
    public Integer getTip() {
		return tip;
	}
	/**提示编号**/
    public void setTip(Integer tip) {
		this.tip = tip;
	}

	
	
}
