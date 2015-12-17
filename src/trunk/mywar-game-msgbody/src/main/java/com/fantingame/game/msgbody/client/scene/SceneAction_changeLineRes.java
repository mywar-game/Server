package com.fantingame.game.msgbody.client.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.scene.UserSceneDataBO;
import java.util.List;
import java.util.ArrayList;

/**换线**/
public class SceneAction_changeLineRes implements ICodeAble {

		/**（从0开始计数即0为1线1为2线）**/
	private Integer userLineNum=0;
	/**场景内的用户数据对象列表**/
	private List<UserSceneDataBO> sceneUsersList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(userLineNum);

		
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
		userLineNum = inputStream.readInt();

		
        int sceneUsersListSize = inputStream.readInt();
		if(sceneUsersListSize>0){
			sceneUsersList = new ArrayList<UserSceneDataBO>();
			for(int sceneUsersListi=0;sceneUsersListi<sceneUsersListSize;sceneUsersListi++){
				 UserSceneDataBO entry = new UserSceneDataBO();entry.decode(inputStream);sceneUsersList.add(entry);
			}
		}
	}
	
		/**（从0开始计数即0为1线1为2线）**/
    public Integer getUserLineNum() {
		return userLineNum;
	}
	/**（从0开始计数即0为1线1为2线）**/
    public void setUserLineNum(Integer userLineNum) {
		this.userLineNum = userLineNum;
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
