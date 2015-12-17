package com.fantingame.game.msgbody.client.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.boss.UserAttactBossInfoBO;
import java.util.List;
import java.util.ArrayList;

/**技能效果对象**/
public class EffectBO implements ICodeAble {

		/**用户攻击信息列表**/
	private List<UserAttactBossInfoBO> attactList=null;
	/**效果buff**/
	private String buff="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(attactList==null||attactList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(attactList.size());
		}
		if(attactList!=null&&attactList.size()>0){
			for(int attactListi=0;attactListi<attactList.size();attactListi++){
				attactList.get(attactListi).encode(outputStream);
			}
		}		outputStream.writeUTF(buff);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int attactListSize = inputStream.readInt();
		if(attactListSize>0){
			attactList = new ArrayList<UserAttactBossInfoBO>();
			for(int attactListi=0;attactListi<attactListSize;attactListi++){
				 UserAttactBossInfoBO entry = new UserAttactBossInfoBO();entry.decode(inputStream);attactList.add(entry);
			}
		}		buff = inputStream.readUTF();


	}
	
		/**用户攻击信息列表**/
    public List<UserAttactBossInfoBO> getAttactList() {
		return attactList;
	}
	/**用户攻击信息列表**/
    public void setAttactList(List<UserAttactBossInfoBO> attactList) {
		this.attactList = attactList;
	}
	/**效果buff**/
    public String getBuff() {
		return buff;
	}
	/**效果buff**/
    public void setBuff(String buff) {
		this.buff = buff;
	}

	
	
}
