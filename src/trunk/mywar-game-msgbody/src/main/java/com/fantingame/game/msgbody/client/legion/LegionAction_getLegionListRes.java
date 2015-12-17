package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.legion.LegionInfoBO;
import java.util.List;
import java.util.ArrayList;

/**查看公会列表**/
public class LegionAction_getLegionListRes implements ICodeAble {

		/**推荐的军团列表**/
	private List<LegionInfoBO> recommendLegionList=null;
	/**军团排行榜列表**/
	private List<LegionInfoBO> legionRankList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(recommendLegionList==null||recommendLegionList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(recommendLegionList.size());
		}
		if(recommendLegionList!=null&&recommendLegionList.size()>0){
			for(int recommendLegionListi=0;recommendLegionListi<recommendLegionList.size();recommendLegionListi++){
				recommendLegionList.get(recommendLegionListi).encode(outputStream);
			}
		}		
        if(legionRankList==null||legionRankList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(legionRankList.size());
		}
		if(legionRankList!=null&&legionRankList.size()>0){
			for(int legionRankListi=0;legionRankListi<legionRankList.size();legionRankListi++){
				legionRankList.get(legionRankListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int recommendLegionListSize = inputStream.readInt();
		if(recommendLegionListSize>0){
			recommendLegionList = new ArrayList<LegionInfoBO>();
			for(int recommendLegionListi=0;recommendLegionListi<recommendLegionListSize;recommendLegionListi++){
				 LegionInfoBO entry = new LegionInfoBO();entry.decode(inputStream);recommendLegionList.add(entry);
			}
		}		
        int legionRankListSize = inputStream.readInt();
		if(legionRankListSize>0){
			legionRankList = new ArrayList<LegionInfoBO>();
			for(int legionRankListi=0;legionRankListi<legionRankListSize;legionRankListi++){
				 LegionInfoBO entry = new LegionInfoBO();entry.decode(inputStream);legionRankList.add(entry);
			}
		}
	}
	
		/**推荐的军团列表**/
    public List<LegionInfoBO> getRecommendLegionList() {
		return recommendLegionList;
	}
	/**推荐的军团列表**/
    public void setRecommendLegionList(List<LegionInfoBO> recommendLegionList) {
		this.recommendLegionList = recommendLegionList;
	}
	/**军团排行榜列表**/
    public List<LegionInfoBO> getLegionRankList() {
		return legionRankList;
	}
	/**军团排行榜列表**/
    public void setLegionRankList(List<LegionInfoBO> legionRankList) {
		this.legionRankList = legionRankList;
	}

	
	
}
