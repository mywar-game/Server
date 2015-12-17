package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserCareerInfoBO;

/**职业解锁**/
public class HeroAction_careerClearRes implements ICodeAble {

		/**用户剩余魂能**/
	private Integer jobExp=0;
	/**用户职业信息对象**/
	private UserCareerInfoBO userCareerInfo=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(jobExp);

		userCareerInfo.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		jobExp = inputStream.readInt();

		userCareerInfo=new UserCareerInfoBO();    
		userCareerInfo.decode(inputStream);


	}
	
		/**用户剩余魂能**/
    public Integer getJobExp() {
		return jobExp;
	}
	/**用户剩余魂能**/
    public void setJobExp(Integer jobExp) {
		this.jobExp = jobExp;
	}
	/**用户职业信息对象**/
    public UserCareerInfoBO getUserCareerInfo() {
		return userCareerInfo;
	}
	/**用户职业信息对象**/
    public void setUserCareerInfo(UserCareerInfoBO userCareerInfo) {
		this.userCareerInfo = userCareerInfo;
	}

	
	
}
