package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserCareerInfoBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;

/**职业解锁回退**/
public class HeroAction_returnJobExpRes implements ICodeAble {

		/**用户剩余魂能**/
	private Integer jobExp=0;
	/**用户职业信息对象**/
	private UserCareerInfoBO userCareerInfo=null;
	/**消耗的魂能令牌道具**/
	private GoodsBeanBO tool=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(jobExp);

		userCareerInfo.encode(outputStream);

		tool.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		jobExp = inputStream.readInt();

		userCareerInfo=new UserCareerInfoBO();    
		userCareerInfo.decode(inputStream);

		tool=new GoodsBeanBO();    
		tool.decode(inputStream);


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
	/**消耗的魂能令牌道具**/
    public GoodsBeanBO getTool() {
		return tool;
	}
	/**消耗的魂能令牌道具**/
    public void setTool(GoodsBeanBO tool) {
		this.tool = tool;
	}

	
	
}
