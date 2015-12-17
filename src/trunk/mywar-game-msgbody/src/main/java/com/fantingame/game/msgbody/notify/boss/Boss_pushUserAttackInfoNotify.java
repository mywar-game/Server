package com.fantingame.game.msgbody.notify.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.boss.EffectBO;
import java.util.List;
import java.util.ArrayList;

/**推送用户攻击信息**/
public class Boss_pushUserAttackInfoNotify implements ICodeAble {

		/**用户Id**/
	private String userIdStr="";
	/**释放的技能id**/
	private Integer skillId=0;
	/**技能效果列表**/
	private List<EffectBO> effectList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userIdStr);

		outputStream.writeInt(skillId);

		
        if(effectList==null||effectList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(effectList.size());
		}
		if(effectList!=null&&effectList.size()>0){
			for(int effectListi=0;effectListi<effectList.size();effectListi++){
				effectList.get(effectListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userIdStr = inputStream.readUTF();

		skillId = inputStream.readInt();

		
        int effectListSize = inputStream.readInt();
		if(effectListSize>0){
			effectList = new ArrayList<EffectBO>();
			for(int effectListi=0;effectListi<effectListSize;effectListi++){
				 EffectBO entry = new EffectBO();entry.decode(inputStream);effectList.add(entry);
			}
		}
	}
	
		/**用户Id**/
    public String getUserIdStr() {
		return userIdStr;
	}
	/**用户Id**/
    public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}
	/**释放的技能id**/
    public Integer getSkillId() {
		return skillId;
	}
	/**释放的技能id**/
    public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	/**技能效果列表**/
    public List<EffectBO> getEffectList() {
		return effectList;
	}
	/**技能效果列表**/
    public void setEffectList(List<EffectBO> effectList) {
		this.effectList = effectList;
	}

	
	
}
