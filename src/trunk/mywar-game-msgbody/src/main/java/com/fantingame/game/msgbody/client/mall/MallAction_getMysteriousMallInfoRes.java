package com.fantingame.game.msgbody.client.mall;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.mall.UserMysteriousMallBO;
import java.util.List;
import java.util.ArrayList;

/**获取神秘商店的信息**/
public class MallAction_getMysteriousMallInfoRes implements ICodeAble {

		/**用户商品信息列表**/
	private List<UserMysteriousMallBO> userMallList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userMallList==null||userMallList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userMallList.size());
		}
		if(userMallList!=null&&userMallList.size()>0){
			for(int userMallListi=0;userMallListi<userMallList.size();userMallListi++){
				userMallList.get(userMallListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userMallListSize = inputStream.readInt();
		if(userMallListSize>0){
			userMallList = new ArrayList<UserMysteriousMallBO>();
			for(int userMallListi=0;userMallListi<userMallListSize;userMallListi++){
				 UserMysteriousMallBO entry = new UserMysteriousMallBO();entry.decode(inputStream);userMallList.add(entry);
			}
		}
	}
	
		/**用户商品信息列表**/
    public List<UserMysteriousMallBO> getUserMallList() {
		return userMallList;
	}
	/**用户商品信息列表**/
    public void setUserMallList(List<UserMysteriousMallBO> userMallList) {
		this.userMallList = userMallList;
	}

	
	
}
