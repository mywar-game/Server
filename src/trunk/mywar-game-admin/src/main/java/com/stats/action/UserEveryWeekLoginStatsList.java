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
import com.log.service.UserGoldLogService;
import com.stats.bo.UserEverydayLoginStats;
import com.stats.service.UserEveryWeekLoginStatsService;

/**
 * 玩家每周登入统计
 * @author Administrator
 *
 */
public class UserEveryWeekLoginStatsList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -1100587657267677215L;
	private Map<String, UserEverydayLoginStats> map = new HashMap<String, UserEverydayLoginStats>();
	private List<Map.Entry<String, UserEverydayLoginStats>> mappingList = null;
	
	@Override
	public String execute() {
		
		UserEveryWeekLoginStatsService service = ServiceCacheFactory.getServiceCache().getService(UserEveryWeekLoginStatsService.class);
//		Map<String, String[]> tempMap = new HashMap<String, String[]>();
		List<Object> objList = service.findLastAndLeastTime();
		if (objList != null) {
			for (int i = 0; i < objList.size(); i++) {
				Object[] tempObj = (Object[]) objList.get(i);
				Timestamp maxTime = (Timestamp) tempObj[0];
				Timestamp minTime = (Timestamp) tempObj[1];
				Map<String, List<Date>> resultMap = translateDates(maxTime, minTime);
				
			    Iterator<String> iterKey = resultMap.keySet().iterator();
			    List<String> tempList = new ArrayList<String>();
			    while (iterKey.hasNext()) {
			    	String key = iterKey.next();
			    	tempList.add(key);
			    }
			    Collections.sort(tempList, new Comparator<String>() {
			    	public int compare(String s1, String s2) {
			    		int nianIndex1 = s1.lastIndexOf("年");
						int yueIndex1 = s1.lastIndexOf("月");
						int zhouIndex1 = s1.lastIndexOf("周");
						int nian1 = Integer.valueOf(s1.substring(0, nianIndex1));
						int yue1 = Integer.valueOf(s1.substring(nianIndex1 + 1, yueIndex1));
						int zhou1 = Integer.valueOf(s1.substring(yueIndex1 + 1, zhouIndex1));
						
						int nianIndex2 = s2.lastIndexOf("年");
						int yueIndex2 = s2.lastIndexOf("月");
						int zhouIndex2 = s2.lastIndexOf("周");
						int nian2 = Integer.valueOf(s2.substring(0, nianIndex2));
						int yue2 = Integer.valueOf(s2.substring(nianIndex2 + 1, yueIndex2));
						int zhou2 = Integer.valueOf(s2.substring(yueIndex2 + 1, zhouIndex2));
						
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
								if (zhou1 > zhou2) {
									return 1;
								} else {
									return -1;
								}
							}
						}
			    	}
			    });
			    
			    if (tempList != null && tempList.size() != 0) {
			    	String tempKey = tempList.get(0);
			    	List<Date> listDate = resultMap.get(tempKey);
			    	Date beginDate = listDate.get(0);
			    	beginDate.setHours(0);
			    	beginDate.setMinutes(0);
			    	beginDate.setSeconds(0);
			    }
				Iterator<String> iter2 = resultMap.keySet().iterator();

			    while (iter2.hasNext()) {

			    	String key = iter2.next();
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
				int zhouIndex1 = mapping1.getKey().lastIndexOf("周");
				int nian1 = Integer.valueOf(mapping1.getKey().substring(0, nianIndex1));
				int yue1 = Integer.valueOf(mapping1.getKey().substring(nianIndex1 + 1, yueIndex1));
				int zhou1 = Integer.valueOf(mapping1.getKey().substring(yueIndex1 + 1, zhouIndex1));
				
				int nianIndex2 = mapping2.getKey().lastIndexOf("年");
				int yueIndex2 = mapping2.getKey().lastIndexOf("月");
				int zhouIndex2 = mapping2.getKey().lastIndexOf("周");
				int nian2 = Integer.valueOf(mapping2.getKey().substring(0, nianIndex2));
				int yue2 = Integer.valueOf(mapping2.getKey().substring(nianIndex2 + 1, yueIndex2));
				int zhou2 = Integer.valueOf(mapping2.getKey().substring(yueIndex2 + 1, zhouIndex2));
				
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
						if (zhou1 > zhou2) {
							return 1;
						} else {
							return -1;
						}
					}
				}
			}
		}); 
	
		return SUCCESS;
	}
	
	/**
	 * 计算月份，周数算法 (有少少复杂，难懂了。算法以后优化)
	 * @param maxTime
	 * @param minTime
	 * @return
	 */
	private static Map<String, List<Date>> translateDates(Timestamp maxTime, Timestamp minTime) {
		
		Map<String, List<Date>> resultMap = new HashMap<String, List<Date>>();
		
		Date beginDate = new Date(minTime.getTime());
		Date maxDate = new Date(maxTime.getTime());
		Date minDate = new Date(minTime.getTime());
		Calendar cBegin = new GregorianCalendar();
	    Calendar cEnd = new GregorianCalendar();
	    cBegin.setTime(minDate);
	    cEnd.setTime(maxDate);
	    
	    // 当前天数
	    int count = cBegin.get(Calendar.DAY_OF_MONTH);
	    Calendar a = Calendar.getInstance();  
	    a.setTime(beginDate);
	    // a.set(Calendar.MONTH, beginDate.getMonth());  
	    a.set(Calendar.DATE, 1); // 把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1); // 日期回滚一天，也就是最后一天  
	    int max = a.get(Calendar.DATE); // 当前月份最大天数
	    boolean flag = false;
	    int zhou = 1;
	    if (count == 7 || count == 14 || count == 21 || count == 28) {
	    	zhou = count / 7;
	    } else {
	    	zhou = count / 7 + 1;
	    }
	    boolean isFirstDay = true;
	    
	    while(cBegin.before(cEnd)) {
	    	if (count % 7 == 0) {
	    		List<Date> list = new ArrayList<Date>();
	    		StringBuffer sb = new StringBuffer();
	    		sb.append(cBegin.get(Calendar.YEAR) + "年");
	    		sb.append(cBegin.get(Calendar.MONTH) + 1);
	    		sb.append("月");
	    		sb.append(zhou + "周");
	    		resultMap.put(sb.toString(), list);
	    		list.add(beginDate);
	    		beginDate = cBegin.getTime();
	    		list.add(beginDate);
	    		flag = true;
	    		zhou ++; // 周
	    		isFirstDay = false;
	    	}
	    	
	    	if (count == 1 && (isFirstDay == false)) {

//	    		a.set(Calendar.MONTH, beginDate.getMonth());  
//	    		a.set(Calendar.DATE, max); // 把日期设置为当月第一天  
//	    		a.roll(Calendar.DATE, -1); // 日期回滚一天，也就是最后一天  
//	    		max = cBegin.get(Calendar.DATE);
	    		
	    		max = getDayOfMonth(cBegin.get(Calendar.YEAR), cBegin.get(Calendar.MONTH));
	    	}
	    	
	    	if (max == count) {
	    		count = 0;
	    		List<Date> list = new ArrayList<Date>();
	    		StringBuffer sb = new StringBuffer();
	    		sb.append(cBegin.get(Calendar.YEAR) + "年");
	    		sb.append(cBegin.get(Calendar.MONTH) + 1);
	    		sb.append("月");
	    		sb.append(zhou + "周");
	    		resultMap.put(sb.toString(), list);
	    		list.add(beginDate);
	    		beginDate = cBegin.getTime();
	    		list.add(beginDate);
	    		flag = false;
	    		zhou = 1;
	    		isFirstDay = false;
	    	}
	    	cBegin.add(Calendar.DAY_OF_YEAR, 1);
	    	count ++;
	    }
	    // 剩余的
	    if (flag == false) {
	    	List<Date> list = new ArrayList<Date>();
	    	StringBuffer sb = new StringBuffer();
	    	sb.append(cBegin.get(Calendar.YEAR) + "年");
    		sb.append(cBegin.get(Calendar.MONTH) + 1);
    		sb.append("月");
    		sb.append(zhou + "周");
    		resultMap.put(sb.toString(), list);
	 	    list.add(beginDate);
	 	    list.add(maxDate);
	    }
	    
	    Iterator<String> iter = resultMap.keySet().iterator();

    	while (iter.hasNext()) {

    	    String key = iter.next();
    	    System.out.println("key = " + key + " ");
    	    List<Date> value = resultMap.get(key);
    	    
    	    for (int f = 0; f < value.size(); f++) {
    	    	if (f == 0) {
    	    		value.get(f).setHours(23);
    	    		value.get(f).setMinutes(59);
    	    		value.get(f).setSeconds(59);
    	    	} else {
    	    		value.get(f).setHours(23);
    	    		value.get(f).setMinutes(59);
    	    		value.get(f).setSeconds(59);
    	    	}
    	    	System.out.print((new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(value.get(f)) + " ");
    	    }
    	    System.out.println();
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
