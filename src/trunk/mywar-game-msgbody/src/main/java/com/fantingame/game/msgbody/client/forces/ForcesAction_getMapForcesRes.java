package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.forces.UserForcesBO;
import java.util.List;
import java.util.ArrayList;

/**获取某个地图下所有用户关卡信息列表**/
public class ForcesAction_getMapForcesRes implements ICodeAble {

		/**用户关卡对象，如果对应地图的关卡没有在此列表中找到数据，则说明用户还没有攻打过此关卡，客户端需要缓存该对象，并维护该对象的变化，不要重复去查询**/
	private List<UserForcesBO> userForcesList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userForcesList==null||userForcesList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userForcesList.size());
		}
		if(userForcesList!=null&&userForcesList.size()>0){
			for(int userForcesListi=0;userForcesListi<userForcesList.size();userForcesListi++){
				userForcesList.get(userForcesListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userForcesListSize = inputStream.readInt();
		if(userForcesListSize>0){
			userForcesList = new ArrayList<UserForcesBO>();
			for(int userForcesListi=0;userForcesListi<userForcesListSize;userForcesListi++){
				 UserForcesBO entry = new UserForcesBO();entry.decode(inputStream);userForcesList.add(entry);
			}
		}
	}
	
		/**用户关卡对象，如果对应地图的关卡没有在此列表中找到数据，则说明用户还没有攻打过此关卡，客户端需要缓存该对象，并维护该对象的变化，不要重复去查询**/
    public List<UserForcesBO> getUserForcesList() {
		return userForcesList;
	}
	/**用户关卡对象，如果对应地图的关卡没有在此列表中找到数据，则说明用户还没有攻打过此关卡，客户端需要缓存该对象，并维护该对象的变化，不要重复去查询**/
    public void setUserForcesList(List<UserForcesBO> userForcesList) {
		this.userForcesList = userForcesList;
	}

	
	
}
