package com.fantingame.game.mywar.logic.tool.model;

public class SystemToolOpen {
	/**
	 * 宝箱id
	 */
   private int toolId;
   /**
    * 打开宝箱需要的钥匙id
    */
   private int openToolId;
   
   
	public int getToolId() {
		return toolId;
	}
	public void setToolId(int toolId) {
		this.toolId = toolId;
	}
	public int getOpenToolId() {
		return openToolId;
	}
	public void setOpenToolId(int openToolId) {
		this.openToolId = openToolId;
	}
   
   
}
