package com.fantingame.game.msgbody.client.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**记录玩家新手引导**/
public class UserAction_recordGuideStepReq implements ICodeAble {

		/**引导步奏id**/
	private Integer guideStep=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(guideStep);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		guideStep = inputStream.readInt();


	}
	
		/**引导步奏id**/
    public Integer getGuideStep() {
		return guideStep;
	}
	/**引导步奏id**/
    public void setGuideStep(Integer guideStep) {
		this.guideStep = guideStep;
	}

	
	
}
