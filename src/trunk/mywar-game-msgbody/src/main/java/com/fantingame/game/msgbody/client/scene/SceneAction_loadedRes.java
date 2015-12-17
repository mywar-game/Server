package com.fantingame.game.msgbody.client.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.scene.UserSceneDataBO;
import java.util.List;
import java.util.ArrayList;

/**加载场景完成**/
public class SceneAction_loadedRes implements ICodeAble {

		/**场景内的用户数据对象列表**/
	private List<UserSceneDataBO> sceneUsersList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(sceneUsersList==null||sceneUsersList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(sceneUsersList.size());
		}
		if(sceneUsersList!=null&&sceneUsersList.size()>0){
			for(int sceneUsersListi=0;sceneUsersListi<sceneUsersList.size();sceneUsersListi++){
				sceneUsersList.get(sceneUsersListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int sceneUsersListSize = inputStream.readInt();
		if(sceneUsersListSize>0){
			sceneUsersList = new ArrayList<UserSceneDataBO>();
			for(int sceneUsersListi=0;sceneUsersListi<sceneUsersListSize;sceneUsersListi++){
				 UserSceneDataBO entry = new UserSceneDataBO();entry.decode(inputStream);sceneUsersList.add(entry);
			}
		}
	}
	
		/**场景内的用户数据对象列表**/
    public List<UserSceneDataBO> getSceneUsersList() {
		return sceneUsersList;
	}
	/**场景内的用户数据对象列表**/
    public void setSceneUsersList(List<UserSceneDataBO> sceneUsersList) {
		this.sceneUsersList = sceneUsersList;
	}

	
	
}
