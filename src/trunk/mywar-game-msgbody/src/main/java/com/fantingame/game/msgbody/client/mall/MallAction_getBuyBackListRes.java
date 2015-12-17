package com.fantingame.game.msgbody.client.mall;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.mall.UserBuyBackInfoBO;
import java.util.List;
import java.util.ArrayList;

/**获取用户回购列表**/
public class MallAction_getBuyBackListRes implements ICodeAble {

		/**用户回购信息列表**/
	private List<UserBuyBackInfoBO> userBuyBackInfoList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userBuyBackInfoList==null||userBuyBackInfoList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userBuyBackInfoList.size());
		}
		if(userBuyBackInfoList!=null&&userBuyBackInfoList.size()>0){
			for(int userBuyBackInfoListi=0;userBuyBackInfoListi<userBuyBackInfoList.size();userBuyBackInfoListi++){
				userBuyBackInfoList.get(userBuyBackInfoListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userBuyBackInfoListSize = inputStream.readInt();
		if(userBuyBackInfoListSize>0){
			userBuyBackInfoList = new ArrayList<UserBuyBackInfoBO>();
			for(int userBuyBackInfoListi=0;userBuyBackInfoListi<userBuyBackInfoListSize;userBuyBackInfoListi++){
				 UserBuyBackInfoBO entry = new UserBuyBackInfoBO();entry.decode(inputStream);userBuyBackInfoList.add(entry);
			}
		}
	}
	
		/**用户回购信息列表**/
    public List<UserBuyBackInfoBO> getUserBuyBackInfoList() {
		return userBuyBackInfoList;
	}
	/**用户回购信息列表**/
    public void setUserBuyBackInfoList(List<UserBuyBackInfoBO> userBuyBackInfoList) {
		this.userBuyBackInfoList = userBuyBackInfoList;
	}

	
	
}
