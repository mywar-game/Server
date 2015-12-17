package com.fantingame.game.msgbody.client.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**建造用户生活建筑**/
public class LifeAction_createLifeBuilderReq implements ICodeAble {

		/**建造的类别（1矿场2花圃3渔场）**/
	private Integer category=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(category);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		category = inputStream.readInt();


	}
	
		/**建造的类别（1矿场2花圃3渔场）**/
    public Integer getCategory() {
		return category;
	}
	/**建造的类别（1矿场2花圃3渔场）**/
    public void setCategory(Integer category) {
		this.category = category;
	}

	
	
}
