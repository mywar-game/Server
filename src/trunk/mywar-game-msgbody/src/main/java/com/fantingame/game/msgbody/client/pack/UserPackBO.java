package com.fantingame.game.msgbody.client.pack;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户背包对象**/
public class UserPackBO implements ICodeAble {

		/**用户背包物品唯一id**/
	private Integer userBackPackId=0;
	/**参照doc目录下的道具类型定义**/
	private Integer goodsType=0;
	/**用户物品唯一id，如果是装备则是用户装备唯一id，如果是道具则为道具唯一id**/
	private String userGoodsId="";
	/**物品所在背包位置从1开始**/
	private Integer pos=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {

		outputStream.writeInt(userBackPackId);

		outputStream.writeInt(goodsType);

		outputStream.writeUTF(userGoodsId);

		outputStream.writeInt(pos);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {

		userBackPackId = inputStream.readInt();

		goodsType = inputStream.readInt();

		userGoodsId = inputStream.readUTF();

		pos = inputStream.readInt();


	}
	
		/**用户背包物品唯一id**/
    public Integer getUserBackPackId() {
		return userBackPackId;
	}
	/**用户背包物品唯一id**/
    public void setUserBackPackId(Integer userBackPackId) {
		this.userBackPackId = userBackPackId;
	}
	/**参照doc目录下的道具类型定义**/
    public Integer getGoodsType() {
		return goodsType;
	}
	/**参照doc目录下的道具类型定义**/
    public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	/**用户物品唯一id，如果是装备则是用户装备唯一id，如果是道具则为道具唯一id**/
    public String getUserGoodsId() {
		return userGoodsId;
	}
	/**用户物品唯一id，如果是装备则是用户装备唯一id，如果是道具则为道具唯一id**/
    public void setUserGoodsId(String userGoodsId) {
		this.userGoodsId = userGoodsId;
	}
	/**物品所在背包位置从1开始**/
    public Integer getPos() {
		return pos;
	}
	/**物品所在背包位置从1开始**/
    public void setPos(Integer pos) {
		this.pos = pos;
	}

	
	
}
