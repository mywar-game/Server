package com.fantingame.game.msgbody.client.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**重新建造用户生活建筑**/
public class LifeAction_reCreateLifeBuilderReq implements ICodeAble {

		/**重新建造的类别（1矿场2花圃3渔场）**/
	private Integer category=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(category);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		category = inputStream.readInt();


	}
	
		/**重新建造的类别（1矿场2花圃3渔场）**/
    public Integer getCategory() {
		return category;
	}
	/**重新建造的类别（1矿场2花圃3渔场）**/
    public void setCategory(Integer category) {
		this.category = category;
	}

	
	
}
