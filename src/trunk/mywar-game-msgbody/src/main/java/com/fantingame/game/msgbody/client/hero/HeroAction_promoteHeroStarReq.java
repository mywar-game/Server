package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;

/**升星**/
public class HeroAction_promoteHeroStarReq implements ICodeAble {

		/**类型1材料升星2道具升星**/
	private Integer type=0;
	/**用户英雄id**/
	private String userHeroId="";
	/**幸运石**/
	private GoodsBeanBO tool=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(type);

		outputStream.writeUTF(userHeroId);

		tool.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		type = inputStream.readInt();

		userHeroId = inputStream.readUTF();

		tool=new GoodsBeanBO();    
		tool.decode(inputStream);


	}
	
		/**类型1材料升星2道具升星**/
    public Integer getType() {
		return type;
	}
	/**类型1材料升星2道具升星**/
    public void setType(Integer type) {
		this.type = type;
	}
	/**用户英雄id**/
    public String getUserHeroId() {
		return userHeroId;
	}
	/**用户英雄id**/
    public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}
	/**幸运石**/
    public GoodsBeanBO getTool() {
		return tool;
	}
	/**幸运石**/
    public void setTool(GoodsBeanBO tool) {
		this.tool = tool;
	}

	
	
}
