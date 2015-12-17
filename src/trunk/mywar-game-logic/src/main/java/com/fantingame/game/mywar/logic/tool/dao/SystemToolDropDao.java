package com.fantingame.game.mywar.logic.tool.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.tool.model.SystemToolDrop;


/**
 * 道具掉落dao
 * 
 * @author mengchao
 * 
 */
public interface SystemToolDropDao {

	public List<SystemToolDrop> getSystemToolDropList(int toolId);
	
}
