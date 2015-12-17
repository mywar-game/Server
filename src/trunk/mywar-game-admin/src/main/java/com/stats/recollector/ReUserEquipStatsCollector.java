package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bo.UserEquipStats;
import com.stats.service.UserEquipService;
import com.stats.service.UserEquipStatsService;

public class ReUserEquipStatsCollector {
	
	// 所有神器表
	private String[] tables = new String[] {
			"user_equip","user_equip_0","user_equip_1","user_equip_10",
			"user_equip_100","user_equip_101","user_equip_102","user_equip_103",
			"user_equip_104","user_equip_105","user_equip_106","user_equip_107",
			"user_equip_108","user_equip_109","user_equip_11","user_equip_110",
			"user_equip_111","user_equip_112","user_equip_113","user_equip_114",
			"user_equip_115","user_equip_116","user_equip_117","user_equip_118",
			"user_equip_119","user_equip_12","user_equip_121","user_equip_122",
			"user_equip_123","user_equip_124","user_equip_125","user_equip_126",
			"user_equip_13",
			"user_equip_14","user_equip_15","user_equip_16","user_equip_17",
			"user_equip_18","user_equip_19","user_equip_2","user_equip_20",
			"user_equip_21","user_equip_22","user_equip_23","user_equip_24",
			"user_equip_25","user_equip_26","user_equip_27","user_equip_28",
			"user_equip_29","user_equip_3","user_equip_30","user_equip_31",
			"user_equip_32","user_equip_33","user_equip_34","user_equip_35",
			"user_equip_36","user_equip_37","user_equip_38","user_equip_39",
			"user_equip_4","user_equip_40","user_equip_41","user_equip_42",
			"user_equip_43","user_equip_44","user_equip_45","user_equip_46",
			"user_equip_47","user_equip_48","user_equip_49","user_equip_5",
			"user_equip_50","user_equip_51","user_equip_52","user_equip_53",
			"user_equip_54","user_equip_55","user_equip_56","user_equip_57",
			"user_equip_58","user_equip_59","user_equip_6","user_equip_60",
			"user_equip_61","user_equip_62","user_equip_63","user_equip_64",
			"user_equip_65","user_equip_66","user_equip_67","user_equip_68",
			"user_equip_69","user_equip_7","user_equip_70","user_equip_71",
			"user_equip_72","user_equip_73","user_equip_74","user_equip_75",
			"user_equip_76","user_equip_77","user_equip_78","user_equip_79",
			"user_equip_8","user_equip_80","user_equip_81","user_equip_82",
			"user_equip_83","user_equip_84","user_equip_85","user_equip_86",
			"user_equip_87","user_equip_88","user_equip_89","user_equip_9",
			"user_equip_90","user_equip_91","user_equip_92","user_equip_93",
			"user_equip_94","user_equip_95","user_equip_96","user_equip_97",
			"user_equip_98","user_equip_99"};
	
	public void execute(String dateStr) {
		LogSystem.info("手动采集--装备统计Collector开始");
//		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String[] dates = DateUtil.getBeforeDateByDate(dateTemp, 0);
		Date time = DateUtil.getSomeDaysDiffDateByDate(dateTemp, 0);
		
		UserEquipStatsService userEquipStatsService = ServiceCacheFactory.getServiceCache().getService(UserEquipStatsService.class);
		UserEquipService userEquipService = ServiceCacheFactory.getServiceCache().getService(UserEquipService.class);
		List<Object> systemEquipList = userEquipService.getSystemEquip();
		List<Object> list = new ArrayList<Object>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Map<Integer, String> systemEquipMap = new HashMap<Integer, String>();
		
		// 解释systemArtifactList
		for (Object obj : systemEquipList) {
			Object[] objArr = (Object[]) obj;
			Integer equipId = (Integer) objArr[0];
			String name = (String) objArr[1];
			Integer quality = (Integer) objArr[2];
			systemEquipMap.put(equipId, name + "_" + quality);
		}
		
		// 先删除
		userEquipStatsService.delete(dateStr);
		
		for (String tableName : tables) {
			list.addAll(userEquipService.getList(tableName, dates));
		}
		
		for (Object obj : list) {
			Integer artifactId = (Integer) obj;
			Integer num = map.get(artifactId);
			if (num == null) {
				map.put(artifactId, 1);
			} else {
				map.put(artifactId, num + 1);
			}
		}

		Iterator<Integer> iter = map.keySet().iterator();
		while (iter.hasNext()) {	
		    Integer key = iter.next();
		    Integer value = map.get(key);
		    
		    UserEquipStats stats = new UserEquipStats();
			stats.setSysNum(CustomerContextHolder.getSystemNum());
//			stats.setTime(DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY));
			stats.setTime(time);
		    stats.setEquipId(key);
		    stats.setEquipNumber(value);
		    stats.setName(systemEquipMap.get(key).split("_")[0]);
		    stats.setQuality(Integer.valueOf(systemEquipMap.get(key).split("_")[1]));
		    userEquipStatsService.save(stats);
		}

		LogSystem.info("手动采集--装备统计Collector结束");
	}

}
