package com.fantingame.game.msgbody.client.tool;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import java.util.List;
import java.util.ArrayList;

/**打开宝箱**/
public class ToolAction_openBoxRes implements ICodeAble {

		/**获得的物品**/
	private CommonGoodsBeanBO drop=null;
	/**消耗的道具**/
	private List<GoodsBeanBO> toolList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		drop.encode(outputStream);

		
        if(toolList==null||toolList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(toolList.size());
		}
		if(toolList!=null&&toolList.size()>0){
			for(int toolListi=0;toolListi<toolList.size();toolListi++){
				toolList.get(toolListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);

		
        int toolListSize = inputStream.readInt();
		if(toolListSize>0){
			toolList = new ArrayList<GoodsBeanBO>();
			for(int toolListi=0;toolListi<toolListSize;toolListi++){
				 GoodsBeanBO entry = new GoodsBeanBO();entry.decode(inputStream);toolList.add(entry);
			}
		}
	}
	
		/**获得的物品**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**获得的物品**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}
	/**消耗的道具**/
    public List<GoodsBeanBO> getToolList() {
		return toolList;
	}
	/**消耗的道具**/
    public void setToolList(List<GoodsBeanBO> toolList) {
		this.toolList = toolList;
	}

	
	
}
