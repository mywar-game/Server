package com.fantingame.game.mywar.logic.task.check;

import java.util.Map;

public interface ITaskHitChecker {
	/**
	 * 任务是否命中
	 * 
	 * @param taskTarget
	 * @param params
	 * @return
	 */
	public Map<String, Object> isHit(int systemTaskId, int taskLibrary, Map<String, String> params);
}
