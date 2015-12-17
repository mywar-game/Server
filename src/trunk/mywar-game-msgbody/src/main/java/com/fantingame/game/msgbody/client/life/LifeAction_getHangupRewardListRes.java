package com.fantingame.game.msgbody.client.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import java.util.List;
import java.util.ArrayList;

/**获取挂机奖励**/
public class LifeAction_getHangupRewardListRes implements ICodeAble {

		/**挂机奖励列表**/
	private List<GoodsBeanBO> userLifeRewardList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userLifeRewardList==null||userLifeRewardList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userLifeRewardList.size());
		}
		if(userLifeRewardList!=null&&userLifeRewardList.size()>0){
			for(int userLifeRewardListi=0;userLifeRewardListi<userLifeRewardList.size();userLifeRewardListi++){
				userLifeRewardList.get(userLifeRewardListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userLifeRewardListSize = inputStream.readInt();
		if(userLifeRewardListSize>0){
			userLifeRewardList = new ArrayList<GoodsBeanBO>();
			for(int userLifeRewardListi=0;userLifeRewardListi<userLifeRewardListSize;userLifeRewardListi++){
				 GoodsBeanBO entry = new GoodsBeanBO();entry.decode(inputStream);userLifeRewardList.add(entry);
			}
		}
	}
	
		/**挂机奖励列表**/
    public List<GoodsBeanBO> getUserLifeRewardList() {
		return userLifeRewardList;
	}
	/**挂机奖励列表**/
    public void setUserLifeRewardList(List<GoodsBeanBO> userLifeRewardList) {
		this.userLifeRewardList = userLifeRewardList;
	}

	
	
}
