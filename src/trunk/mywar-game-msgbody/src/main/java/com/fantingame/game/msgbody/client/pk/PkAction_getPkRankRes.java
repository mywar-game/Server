package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.pk.PkRankBO;
import java.util.List;
import java.util.ArrayList;

/**查看排行榜**/
public class PkAction_getPkRankRes implements ICodeAble {

		/**排行榜列表**/
	private List<PkRankBO> rankList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(rankList==null||rankList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(rankList.size());
		}
		if(rankList!=null&&rankList.size()>0){
			for(int rankListi=0;rankListi<rankList.size();rankListi++){
				rankList.get(rankListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int rankListSize = inputStream.readInt();
		if(rankListSize>0){
			rankList = new ArrayList<PkRankBO>();
			for(int rankListi=0;rankListi<rankListSize;rankListi++){
				 PkRankBO entry = new PkRankBO();entry.decode(inputStream);rankList.add(entry);
			}
		}
	}
	
		/**排行榜列表**/
    public List<PkRankBO> getRankList() {
		return rankList;
	}
	/**排行榜列表**/
    public void setRankList(List<PkRankBO> rankList) {
		this.rankList = rankList;
	}

	
	
}
