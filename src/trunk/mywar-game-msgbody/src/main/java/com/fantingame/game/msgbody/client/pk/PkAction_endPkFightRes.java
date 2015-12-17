package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;

/**结束竞技场战斗**/
public class PkAction_endPkFightRes implements ICodeAble {

		/**通用奖励对象**/
	private CommonGoodsBeanBO drop=null;
	/**用户之前排名**/
	private Integer oldRank=0;
	/**用户当前排名**/
	private Integer rank=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		drop.encode(outputStream);

		outputStream.writeInt(oldRank);

		outputStream.writeInt(rank);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);

		oldRank = inputStream.readInt();

		rank = inputStream.readInt();


	}
	
		/**通用奖励对象**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**通用奖励对象**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}
	/**用户之前排名**/
    public Integer getOldRank() {
		return oldRank;
	}
	/**用户之前排名**/
    public void setOldRank(Integer oldRank) {
		this.oldRank = oldRank;
	}
	/**用户当前排名**/
    public Integer getRank() {
		return rank;
	}
	/**用户当前排名**/
    public void setRank(Integer rank) {
		this.rank = rank;
	}

	
	
}
