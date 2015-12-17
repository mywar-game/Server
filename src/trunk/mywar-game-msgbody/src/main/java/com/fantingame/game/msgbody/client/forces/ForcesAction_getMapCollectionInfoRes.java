package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.forces.UserForcesBO;
import java.util.List;
import java.util.ArrayList;

/**获取地图采集点信息**/
public class ForcesAction_getMapCollectionInfoRes implements ICodeAble {

		/**用户采集信息列表**/
	private List<UserForcesBO> userCollectList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userCollectList==null||userCollectList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userCollectList.size());
		}
		if(userCollectList!=null&&userCollectList.size()>0){
			for(int userCollectListi=0;userCollectListi<userCollectList.size();userCollectListi++){
				userCollectList.get(userCollectListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userCollectListSize = inputStream.readInt();
		if(userCollectListSize>0){
			userCollectList = new ArrayList<UserForcesBO>();
			for(int userCollectListi=0;userCollectListi<userCollectListSize;userCollectListi++){
				 UserForcesBO entry = new UserForcesBO();entry.decode(inputStream);userCollectList.add(entry);
			}
		}
	}
	
		/**用户采集信息列表**/
    public List<UserForcesBO> getUserCollectList() {
		return userCollectList;
	}
	/**用户采集信息列表**/
    public void setUserCollectList(List<UserForcesBO> userCollectList) {
		this.userCollectList = userCollectList;
	}

	
	
}
