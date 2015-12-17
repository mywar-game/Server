package com.fantingame.game.msgbody.notify.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.notify.boss.UserBossRankBO;
import java.util.List;
import java.util.ArrayList;

/**推送世界Boss的排行榜**/
public class Boss_pushWorldBossRankNotify implements ICodeAble {

		/**伤害排行榜列表**/
	private List<UserBossRankBO> hurtRankList=null;
	/**治疗量排行榜列表**/
	private List<UserBossRankBO> treamentRankList=null;
	/**承受伤害排行榜列表**/
	private List<UserBossRankBO> beHitRankList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(hurtRankList==null||hurtRankList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(hurtRankList.size());
		}
		if(hurtRankList!=null&&hurtRankList.size()>0){
			for(int hurtRankListi=0;hurtRankListi<hurtRankList.size();hurtRankListi++){
				hurtRankList.get(hurtRankListi).encode(outputStream);
			}
		}		
        if(treamentRankList==null||treamentRankList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(treamentRankList.size());
		}
		if(treamentRankList!=null&&treamentRankList.size()>0){
			for(int treamentRankListi=0;treamentRankListi<treamentRankList.size();treamentRankListi++){
				treamentRankList.get(treamentRankListi).encode(outputStream);
			}
		}		
        if(beHitRankList==null||beHitRankList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(beHitRankList.size());
		}
		if(beHitRankList!=null&&beHitRankList.size()>0){
			for(int beHitRankListi=0;beHitRankListi<beHitRankList.size();beHitRankListi++){
				beHitRankList.get(beHitRankListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int hurtRankListSize = inputStream.readInt();
		if(hurtRankListSize>0){
			hurtRankList = new ArrayList<UserBossRankBO>();
			for(int hurtRankListi=0;hurtRankListi<hurtRankListSize;hurtRankListi++){
				 UserBossRankBO entry = new UserBossRankBO();entry.decode(inputStream);hurtRankList.add(entry);
			}
		}		
        int treamentRankListSize = inputStream.readInt();
		if(treamentRankListSize>0){
			treamentRankList = new ArrayList<UserBossRankBO>();
			for(int treamentRankListi=0;treamentRankListi<treamentRankListSize;treamentRankListi++){
				 UserBossRankBO entry = new UserBossRankBO();entry.decode(inputStream);treamentRankList.add(entry);
			}
		}		
        int beHitRankListSize = inputStream.readInt();
		if(beHitRankListSize>0){
			beHitRankList = new ArrayList<UserBossRankBO>();
			for(int beHitRankListi=0;beHitRankListi<beHitRankListSize;beHitRankListi++){
				 UserBossRankBO entry = new UserBossRankBO();entry.decode(inputStream);beHitRankList.add(entry);
			}
		}
	}
	
		/**伤害排行榜列表**/
    public List<UserBossRankBO> getHurtRankList() {
		return hurtRankList;
	}
	/**伤害排行榜列表**/
    public void setHurtRankList(List<UserBossRankBO> hurtRankList) {
		this.hurtRankList = hurtRankList;
	}
	/**治疗量排行榜列表**/
    public List<UserBossRankBO> getTreamentRankList() {
		return treamentRankList;
	}
	/**治疗量排行榜列表**/
    public void setTreamentRankList(List<UserBossRankBO> treamentRankList) {
		this.treamentRankList = treamentRankList;
	}
	/**承受伤害排行榜列表**/
    public List<UserBossRankBO> getBeHitRankList() {
		return beHitRankList;
	}
	/**承受伤害排行榜列表**/
    public void setBeHitRankList(List<UserBossRankBO> beHitRankList) {
		this.beHitRankList = beHitRankList;
	}

	
	
}
