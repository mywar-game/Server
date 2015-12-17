package com.fantingame.game.msgbody.client.pawnshop;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.pawnshop.UserPawnshopBO;
import java.util.List;
import java.util.ArrayList;

/**获取当铺信息**/
public class PawnshopAction_getPawnshopInfoRes implements ICodeAble {

		/**当铺商品信息对象列表**/
	private List<UserPawnshopBO> userPawnshopList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userPawnshopList==null||userPawnshopList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userPawnshopList.size());
		}
		if(userPawnshopList!=null&&userPawnshopList.size()>0){
			for(int userPawnshopListi=0;userPawnshopListi<userPawnshopList.size();userPawnshopListi++){
				userPawnshopList.get(userPawnshopListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userPawnshopListSize = inputStream.readInt();
		if(userPawnshopListSize>0){
			userPawnshopList = new ArrayList<UserPawnshopBO>();
			for(int userPawnshopListi=0;userPawnshopListi<userPawnshopListSize;userPawnshopListi++){
				 UserPawnshopBO entry = new UserPawnshopBO();entry.decode(inputStream);userPawnshopList.add(entry);
			}
		}
	}
	
		/**当铺商品信息对象列表**/
    public List<UserPawnshopBO> getUserPawnshopList() {
		return userPawnshopList;
	}
	/**当铺商品信息对象列表**/
    public void setUserPawnshopList(List<UserPawnshopBO> userPawnshopList) {
		this.userPawnshopList = userPawnshopList;
	}

	
	
}
