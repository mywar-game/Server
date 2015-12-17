package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**职业解锁**/
public class HeroAction_careerClearReq implements ICodeAble {

		/**详细职业id**/
	private Integer detailedCareerId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(detailedCareerId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		detailedCareerId = inputStream.readInt();


	}
	
		/**详细职业id**/
    public Integer getDetailedCareerId() {
		return detailedCareerId;
	}
	/**详细职业id**/
    public void setDetailedCareerId(Integer detailedCareerId) {
		this.detailedCareerId = detailedCareerId;
	}

	
	
}
