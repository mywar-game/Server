package com.stats.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.stats.bo.MallStats;
import com.stats.dao.MallStatsDao;

public class MallStatsService {
	private MallStatsDao mallStatsDao;

	public MallStatsDao getMallStatsDao() {
		return mallStatsDao;
	}

	public void setMallStatsDao(MallStatsDao mallStatsDao) {
		this.mallStatsDao = mallStatsDao;
	}
	
	/**
	 * 批量保存数据
	 * @param list
	 */
	public void saveBatch(List<MallStats> list){
		mallStatsDao.saveBatch(list, DBSource.ADMIN);
	}
	
	/**
	 * 删除某天统计数据
	 * @param dateStr
	 */
	public void delete(String dateStr) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from MallStats where TIME = '");
		sql.append(dateStr);
		sql.append("'");
		mallStatsDao.closeSession(DBSource.ADMIN);
		mallStatsDao.execute(sql.toString());
	}
	
	/**
	 * 查询商城售卖统计信息
	 * @param channel
	 * @param sysNum
	 * @param order
	 * @param dates
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public IPage<Object> findMallStats(Integer channel,Integer sysNum,String order,String[] dates,int pageSize,int pageIndex){
		StringBuilder sql = new StringBuilder();
		sql.append("select treasure_id,treasure_name,price,ifnull(sum(sale_num),0),ifnull(sum(cost_gold),0),ifnull(sum(buy_user_num),0) from mall_stats where 1=1");
		if(channel!=null && channel.intValue()!=0){
			sql.append(" and channel='").append(channel.toString()).append("'");
		}
		if(sysNum!=null && sysNum.intValue()!=0){
			sql.append(" and sys_num=").append(sysNum);
		}
		if(dates!=null){
			sql.append(" and time between '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		sql.append(" group by treasure_id");
		if(order!=null && !order.equals("")){
			if(Integer.parseInt(order)==1){//按销量排序
				sql.append(" order by sum(sale_num) desc");
			}else{//按收入排序
				sql.append(" order by sum(cost_gold) desc");
			}
		}
		mallStatsDao.closeSession(DBSource.ADMIN);
		return mallStatsDao.findSQL_Page_Have_Group(sql.toString(), new ArrayList<Object>(), pageSize, pageIndex);
	}
}
