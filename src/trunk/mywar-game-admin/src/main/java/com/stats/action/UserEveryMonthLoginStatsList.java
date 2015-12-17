package com.stats.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserEverydayLoginStats;
import com.stats.service.UserEveryMonthLoginStatsService;


/**
 * 玩家每月登入统计
 * @author Administrator
 *
 */
public class UserEveryMonthLoginStatsList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -8795596808945396442L;
	private Map<String, UserEverydayLoginStats> map = new HashMap<String, UserEverydayLoginStats>();
	private List<Map.Entry<String, UserEverydayLoginStats>> mappingList = null;
	
	@Override
	public String execute() {
		UserEveryMonthLoginStatsService service = ServiceCacheFactory.getServiceCache().getService(UserEveryMonthLoginStatsService.class);
		List<Object> objList = service.findLastAndLeastTime();
		if (objList != null) {
			for (int i = 0; i < objList.size(); i++) {
				Object[] tempObj = (Object[]) objList.get(i);
				Timestamp maxTime = (Timestamp) tempObj[0];
				Timestamp minTime = (Timestamp) tempObj[1];
				
				Map<String, List<Date>> resultMap = translateDates(maxTime, minTime);
				Iterator<String> iter = resultMap.keySet().iterator();

			    while (iter.hasNext()) {

			    	String key = iter.next();
			    	System.out.println(key);
			    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	String[] dates = new String[2];
			    	dates[0] = df.format(resultMap.get(key).get(0));
			    	dates[1] = df.format(resultMap.get(key).get(1));
			    	System.out.println(dates[0]);
			    	System.out.println(dates[1]);
			    	UserEverydayLoginStats sta = service.find(dates);
			    	System.out.println(sta);
			    	map.put(key, sta);
			    }
			}
		}
		
		// 排序
		mappingList = new ArrayList<Map.Entry<String, UserEverydayLoginStats>>(map.entrySet());
		// 通过比较器实现比较排序
		Collections.sort(mappingList, new Comparator<Map.Entry<String, UserEverydayLoginStats>>() {
			public int compare(Map.Entry<String, UserEverydayLoginStats> mapping1, Map.Entry<String, UserEverydayLoginStats> mapping2) {
			// return mapping1.getKey().compareTo(mapping2.getKey());
			int nianIndex1 = mapping1.getKey().lastIndexOf("年");
			int yueIndex1 = mapping1.getKey().lastIndexOf("月");
			//int zhouIndex1 = mapping1.getKey().lastIndexOf("周");
			int nian1 = Integer.valueOf(mapping1.getKey().substring(0, nianIndex1));
			int yue1 = Integer.valueOf(mapping1.getKey().substring(nianIndex1 + 1, yueIndex1));
			//int zhou1 = Integer.valueOf(mapping1.getKey().substring(yueIndex1 + 1, zhouIndex1));
						
			int nianIndex2 = mapping2.getKey().lastIndexOf("年");
			int yueIndex2 = mapping2.getKey().lastIndexOf("月");
			//int zhouIndex2 = mapping2.getKey().lastIndexOf("周");
			int nian2 = Integer.valueOf(mapping2.getKey().substring(0, nianIndex2));
			int yue2 = Integer.valueOf(mapping2.getKey().substring(nianIndex2 + 1, yueIndex2));
			//int zhou2 = Integer.valueOf(mapping2.getKey().substring(yueIndex2 + 1, zhouIndex2));
						
			if (nian1 > nian2) {
				return 1;
			} else if (nian1 < nian2) {
				return -1;
			} else {
				if (yue1 > yue2) {
					return 1;
				} else if (yue1 < yue2) {
					return -1;
				} else {
					return 0;
				}
			}
		}
		});
		return SUCCESS;
	}

	private static Map<String, List<Date>> translateDates(Timestamp maxTime, Timestamp minTime) {
		
		Map<String, List<Date>> resultMap = new HashMap<String, List<Date>>();
		
		Date maxDate = new Date(maxTime.getTime());
		Date minDate = new Date(minTime.getTime());
		Calendar cBegin = new GregorianCalendar();
	    Calendar cEnd = new GregorianCalendar();
	    cBegin.setTime(minDate);
	    cEnd.setTime(maxDate);
	    while(cBegin.before(cEnd)) {
	    	
	    	int month = cBegin.getTime().getMonth();
	    	int year = cBegin.get(Calendar.YEAR);
	    	int maxDay = getDayOfMonth(year, month);
	    	StringBuffer sb = new StringBuffer();
	    	sb.append(year + "年");
	    	sb.append(month + 1 + "月");
	    	
	    	if (resultMap.get(sb.toString()) == null) {
	    		List<Date> list = new ArrayList<Date>();
		    	resultMap.put(sb.toString(), list);
		    	
		    	Calendar tempB = Calendar.getInstance();
		    	tempB.set(year, month, 1, 0, 0, 0);
		    	list.add(tempB.getTime());
		    	
		    	Calendar tempE = Calendar.getInstance();
		    	tempE.set(year, month, maxDay, 23, 59, 59);
		    	list.add(tempE.getTime());
	    	}
	    	cBegin.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    
	    return resultMap;
	}
	
	private static int getDayOfMonth(int year, int month) {
		int[] monDays = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if ( ( (year) % 4 == 0 && (year) % 100 != 0) ||(year) % 400 == 0) 
		        monDays[1]++;
		return monDays[month];
	}
	

	public Map<String, UserEverydayLoginStats> getMap() {
		return map;
	}

	public void setMap(Map<String, UserEverydayLoginStats> map) {
		this.map = map;
	}

	public List<Map.Entry<String, UserEverydayLoginStats>> getMappingList() {
		return mappingList;
	}

	public void setMappingList(
			List<Map.Entry<String, UserEverydayLoginStats>> mappingList) {
		this.mappingList = mappingList;
	}


}
