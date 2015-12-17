package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.pk.UserPkInfoBO;
import java.util.List;
import java.util.ArrayList;
import com.fantingame.game.msgbody.client.pk.PkChallengerBO;

/**获取用户竞技场信息**/
public class PkAction_getUserPkInfoRes implements ICodeAble {

		/**用户竞技场信息**/
	private UserPkInfoBO userPkInfoBO=null;
	/**用户防守阵容列表**/
	private List<String> userDefenceHeroList=null;
	/**用户可挑战列表**/
	private List<PkChallengerBO> userPkList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userPkInfoBO.encode(outputStream);

		
        if(userDefenceHeroList==null||userDefenceHeroList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userDefenceHeroList.size());
		}
		if(userDefenceHeroList!=null&&userDefenceHeroList.size()>0){
			for(int userDefenceHeroListi=0;userDefenceHeroListi<userDefenceHeroList.size();userDefenceHeroListi++){
						outputStream.writeUTF(userDefenceHeroList.get(userDefenceHeroListi));


			}
		}		
        if(userPkList==null||userPkList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userPkList.size());
		}
		if(userPkList!=null&&userPkList.size()>0){
			for(int userPkListi=0;userPkListi<userPkList.size();userPkListi++){
				userPkList.get(userPkListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userPkInfoBO=new UserPkInfoBO();    
		userPkInfoBO.decode(inputStream);

		
        int userDefenceHeroListSize = inputStream.readInt();
		if(userDefenceHeroListSize>0){
			userDefenceHeroList = new ArrayList<String>();
			for(int userDefenceHeroListi=0;userDefenceHeroListi<userDefenceHeroListSize;userDefenceHeroListi++){
				 userDefenceHeroList.add(inputStream.readUTF());
			}
		}		
        int userPkListSize = inputStream.readInt();
		if(userPkListSize>0){
			userPkList = new ArrayList<PkChallengerBO>();
			for(int userPkListi=0;userPkListi<userPkListSize;userPkListi++){
				 PkChallengerBO entry = new PkChallengerBO();entry.decode(inputStream);userPkList.add(entry);
			}
		}
	}
	
		/**用户竞技场信息**/
    public UserPkInfoBO getUserPkInfoBO() {
		return userPkInfoBO;
	}
	/**用户竞技场信息**/
    public void setUserPkInfoBO(UserPkInfoBO userPkInfoBO) {
		this.userPkInfoBO = userPkInfoBO;
	}
	/**用户防守阵容列表**/
    public List<String> getUserDefenceHeroList() {
		return userDefenceHeroList;
	}
	/**用户防守阵容列表**/
    public void setUserDefenceHeroList(List<String> userDefenceHeroList) {
		this.userDefenceHeroList = userDefenceHeroList;
	}
	/**用户可挑战列表**/
    public List<PkChallengerBO> getUserPkList() {
		return userPkList;
	}
	/**用户可挑战列表**/
    public void setUserPkList(List<PkChallengerBO> userPkList) {
		this.userPkList = userPkList;
	}

	
	
}
