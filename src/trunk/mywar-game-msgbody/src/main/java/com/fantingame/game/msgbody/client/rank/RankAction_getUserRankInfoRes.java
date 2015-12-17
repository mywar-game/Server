package com.fantingame.game.msgbody.client.rank;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.user.UserRankBO;
import java.util.List;
import java.util.ArrayList;

/**用户排行榜信息**/
public class RankAction_getUserRankInfoRes implements ICodeAble {

		/**排行榜列表**/
	private List<UserRankBO> rankList=null;
	/**自己的排行榜信息**/
	private UserRankBO selfRankBO=null;

	
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
		}		selfRankBO.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int rankListSize = inputStream.readInt();
		if(rankListSize>0){
			rankList = new ArrayList<UserRankBO>();
			for(int rankListi=0;rankListi<rankListSize;rankListi++){
				 UserRankBO entry = new UserRankBO();entry.decode(inputStream);rankList.add(entry);
			}
		}		selfRankBO=new UserRankBO();    
		selfRankBO.decode(inputStream);


	}
	
		/**排行榜列表**/
    public List<UserRankBO> getRankList() {
		return rankList;
	}
	/**排行榜列表**/
    public void setRankList(List<UserRankBO> rankList) {
		this.rankList = rankList;
	}
	/**自己的排行榜信息**/
    public UserRankBO getSelfRankBO() {
		return selfRankBO;
	}
	/**自己的排行榜信息**/
    public void setSelfRankBO(UserRankBO selfRankBO) {
		this.selfRankBO = selfRankBO;
	}

	
	
}
