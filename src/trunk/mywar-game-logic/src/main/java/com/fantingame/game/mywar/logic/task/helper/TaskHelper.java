package com.fantingame.game.mywar.logic.task.helper;

import java.util.HashMap;
import java.util.Map;

import com.fandingame.game.framework.log.LogSystem;
import com.google.common.base.Strings;



/**
 * 任务帮助类
 * 
 * 
 */
public class TaskHelper {

	/**
	 * 解析任务参数
	 * 
	 * @param params
	 * @return
	 */
	public static Map<String, String> parse(String params) {

		Map<String, String> map = new HashMap<String, String>();

		if (Strings.isNullOrEmpty(params)) {
			return map;
		}
		try {
			String[] infos = params.split(",");
			for (String info : infos) {
				String[] datas = info.split(":");
				if (datas.length != 2) {
					LogSystem.warn("任务参数错误:"+params);
					continue;
				}
				map.put(datas[0], datas[1]);
			}
		} catch (Exception e) {
			LogSystem.error(e,"错误的任务参数配置.params[" + params + "]");
		}
		return map;
	}

	/**
	 * VIP奖励日志
	 * 
	 * @param taskId
	 * @return
	 */
	public static boolean isVIPTask(int taskId) {
		if (taskId == 90000) {
			return true;
		}
		return false;
	}

	public static int getVipTaskCopperAdd(int vipLevel) {

		if (vipLevel == 2) {
			return 30000;
		} else if (vipLevel == 3) {
			return 60000;
		}

		return 0;
	}

}
