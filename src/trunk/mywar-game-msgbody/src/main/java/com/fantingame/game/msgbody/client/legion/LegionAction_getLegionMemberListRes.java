package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.legion.LegionMemberBO;
import java.util.List;
import java.util.ArrayList;

/**查看公会成员列表**/
public class LegionAction_getLegionMemberListRes implements ICodeAble {

		/**公会成员列表**/
	private List<LegionMemberBO> legionMemberList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(legionMemberList==null||legionMemberList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(legionMemberList.size());
		}
		if(legionMemberList!=null&&legionMemberList.size()>0){
			for(int legionMemberListi=0;legionMemberListi<legionMemberList.size();legionMemberListi++){
				legionMemberList.get(legionMemberListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int legionMemberListSize = inputStream.readInt();
		if(legionMemberListSize>0){
			legionMemberList = new ArrayList<LegionMemberBO>();
			for(int legionMemberListi=0;legionMemberListi<legionMemberListSize;legionMemberListi++){
				 LegionMemberBO entry = new LegionMemberBO();entry.decode(inputStream);legionMemberList.add(entry);
			}
		}
	}
	
		/**公会成员列表**/
    public List<LegionMemberBO> getLegionMemberList() {
		return legionMemberList;
	}
	/**公会成员列表**/
    public void setLegionMemberList(List<LegionMemberBO> legionMemberList) {
		this.legionMemberList = legionMemberList;
	}

	
	
}
