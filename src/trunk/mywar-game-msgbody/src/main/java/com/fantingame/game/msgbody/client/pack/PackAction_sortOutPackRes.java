package com.fantingame.game.msgbody.client.pack;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.pack.UserPackBO;
import java.util.List;
import java.util.ArrayList;

/**整理背包**/
public class PackAction_sortOutPackRes implements ICodeAble {

		/**修改过的用户背包对象，替换掉客户端中的缓存。注意，不是全部替换,只替换该list中存在的对象**/
	private List<UserPackBO> changePackList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {

		
        if(changePackList==null||changePackList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(changePackList.size());
		}
		if(changePackList.size()>0){
			for(int changePackListi=0;changePackListi<changePackList.size();changePackListi++){
				changePackList.get(changePackListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {

		
        int changePackListSize = inputStream.readInt();
		if(changePackListSize>0){
			changePackList = new ArrayList<UserPackBO>();
			for(int changePackListi=0;changePackListi<changePackListSize;changePackListi++){
				 UserPackBO entry = new UserPackBO();entry.decode(inputStream);changePackList.add(entry);
			}
		}
	}
	
		/**修改过的用户背包对象，替换掉客户端中的缓存。注意，不是全部替换,只替换该list中存在的对象**/
    public List<UserPackBO> getChangePackList() {
		return changePackList;
	}
	/**修改过的用户背包对象，替换掉客户端中的缓存。注意，不是全部替换,只替换该list中存在的对象**/
    public void setChangePackList(List<UserPackBO> changePackList) {
		this.changePackList = changePackList;
	}

	
	
}
