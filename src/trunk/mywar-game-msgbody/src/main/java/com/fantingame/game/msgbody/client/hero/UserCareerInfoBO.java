package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户职业信息对象**/
public class UserCareerInfoBO implements ICodeAble {

		/**详细职业id**/
	private Integer detailedCareerId=0;
	/**已打开的层数**/
	private Integer level=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(detailedCareerId);

		outputStream.writeInt(level);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		detailedCareerId = inputStream.readInt();

		level = inputStream.readInt();


	}
	
		/**详细职业id**/
    public Integer getDetailedCareerId() {
		return detailedCareerId;
	}
	/**详细职业id**/
    public void setDetailedCareerId(Integer detailedCareerId) {
		this.detailedCareerId = detailedCareerId;
	}
	/**已打开的层数**/
    public Integer getLevel() {
		return level;
	}
	/**已打开的层数**/
    public void setLevel(Integer level) {
		this.level = level;
	}

	
	
}
