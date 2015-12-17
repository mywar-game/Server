package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.legion.UserLegionInfoBO;
import com.fantingame.game.msgbody.client.legion.LegionInfoBO;
import java.util.List;
import java.util.ArrayList;

/**查看自己的公会信息**/
public class LegionAction_getLegionInfoRes implements ICodeAble {

		/**用户的公会信息**/
	private UserLegionInfoBO userLegionInfoBO=null;
	/**军团信息**/
	private LegionInfoBO legionInfo=null;
	/**申请的公会id列表（已入公会的为空）**/
	private List<String> applyLegionIdList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userLegionInfoBO.encode(outputStream);

		legionInfo.encode(outputStream);

		
        if(applyLegionIdList==null||applyLegionIdList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(applyLegionIdList.size());
		}
		if(applyLegionIdList!=null&&applyLegionIdList.size()>0){
			for(int applyLegionIdListi=0;applyLegionIdListi<applyLegionIdList.size();applyLegionIdListi++){
						outputStream.writeUTF(applyLegionIdList.get(applyLegionIdListi));


			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userLegionInfoBO=new UserLegionInfoBO();    
		userLegionInfoBO.decode(inputStream);

		legionInfo=new LegionInfoBO();    
		legionInfo.decode(inputStream);

		
        int applyLegionIdListSize = inputStream.readInt();
		if(applyLegionIdListSize>0){
			applyLegionIdList = new ArrayList<String>();
			for(int applyLegionIdListi=0;applyLegionIdListi<applyLegionIdListSize;applyLegionIdListi++){
				 applyLegionIdList.add(inputStream.readUTF());
			}
		}
	}
	
		/**用户的公会信息**/
    public UserLegionInfoBO getUserLegionInfoBO() {
		return userLegionInfoBO;
	}
	/**用户的公会信息**/
    public void setUserLegionInfoBO(UserLegionInfoBO userLegionInfoBO) {
		this.userLegionInfoBO = userLegionInfoBO;
	}
	/**军团信息**/
    public LegionInfoBO getLegionInfo() {
		return legionInfo;
	}
	/**军团信息**/
    public void setLegionInfo(LegionInfoBO legionInfo) {
		this.legionInfo = legionInfo;
	}
	/**申请的公会id列表（已入公会的为空）**/
    public List<String> getApplyLegionIdList() {
		return applyLegionIdList;
	}
	/**申请的公会id列表（已入公会的为空）**/
    public void setApplyLegionIdList(List<String> applyLegionIdList) {
		this.applyLegionIdList = applyLegionIdList;
	}

	
	
}
