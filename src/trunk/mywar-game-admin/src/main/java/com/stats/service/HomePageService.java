package com.stats.service;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.util.log.Log;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.stats.bo.HomePageStats;
import com.stats.dao.HomePageStatsDao;

public class HomePageService {
	private HomePageStatsDao homePageStatsDao;

	public HomePageStatsDao getHomePageStatsDao() {
		return homePageStatsDao;
	}

	public void setHomePageStatsDao(HomePageStatsDao homePageStatsDao) {
		this.homePageStatsDao = homePageStatsDao;
	}
	
	/**
	 * 批量保存数据
	 * @param result
	 */
	public void saveBatch(List<HomePageStats> result){
		homePageStatsDao.saveBatch(result, DBSource.ADMIN);
	}
	
	/**
	 * 获得某天某个服务器的最大时间节点
	 * @param date
	 * @param sysNum
	 * @return
	 */
	public int getMaxIndex(String date,Integer sysNum){
		homePageStatsDao.closeSession(DBSource.ADMIN);
		String sql = "select ifnull(max(half_hour_index),0) from home_page_stats where TIME='"+date+"' and sys_num="+sysNum;
		List<Object> list = homePageStatsDao.findSQL_(sql);
		if(list!=null && list.size()>0){
			return Integer.valueOf(list.get(0).toString());
		}
		return 0;
	}
	
	/**
	 * 删除过期的时间节点数据
	 * @param date
	 * @param sysNum
	 * @param halfHourIndex
	 */
	public void delete(String date,Integer sysNum,Integer halfHourIndex){
		String delete = "delete from home_page_stats where TIME='"+date+"' and sys_num="+sysNum+" and half_hour_index<"+halfHourIndex;
		homePageStatsDao.executeSQL_(delete);
	}
	/**
	 * 分页查询首页数据
	 * @param dates
	 * @param sysNum
	 * @param channel
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public IPage<Object> findPageData(String[] dates,Integer sysNum,Integer channel, String qn, int pageSize,int pageIndex){
		StringBuilder sql = new StringBuilder();
		sql.append("select time,sys_num,");
		if(channel!=null && channel.intValue()!=0){
			sql.append("total_num,day_active,month_active,new_user,pay_user_num,pay_amount,new_reg_pay_user_num,diamond_used");
		}else{
			sql.append("ifnull(sum(total_num),0),ifnull(sum(day_active),0),ifnull(sum(month_active),0),");
			sql.append("ifnull(sum(new_user),0),ifnull(sum(pay_user_num),0),ifnull(sum(pay_amount),0),");
			sql.append("ifnull(sum(new_reg_pay_user_num),0),ifnull(sum(diamond_used),0)");
		}
		sql.append(",half_hour_index from home_page_stats");
		sql.append(" where 1=1");
		if(dates!=null){
			sql.append(" and time between '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		if(sysNum!=null && sysNum.intValue()!=0){
			sql.append(" and sys_num = ");
			sql.append(sysNum);
		}
		if(qn!=null && !"-1".equals(qn)){
			sql.append(" and qn = '");
			sql.append(qn);
			sql.append("'");
		}
		if(channel!=null && channel.intValue()!=0){
			sql.append(" and channel = '");
			sql.append(channel);
			sql.append("'");
			sql.append(" order by time desc");
			homePageStatsDao.closeSession(DBSource.ADMIN);
			return homePageStatsDao.findSQL_Page(sql.toString(), new ArrayList<Object>(), pageSize, pageIndex);
		}else{
			sql.append(" group by time,sys_num");
			sql.append(" order by time desc");
			homePageStatsDao.closeSession(DBSource.ADMIN);
			Log.info("打印数据 " + sql.toString());
			return homePageStatsDao.findSQL_Page_Have_Group(sql.toString(), new ArrayList<Object>(), pageSize, pageIndex);
		}
		
	}
	
	/**
	 * 查询首页数据
	 * @param dates
	 * @param sysNum
	 * @param channel
	 * @return
	 */
	public List<Object> findData(String[] dates,Integer sysNum,Integer channel){
		StringBuilder sql = new StringBuilder();
		sql.append("select time,sys_num,");
		if(channel!=null && channel.intValue()!=0){
			sql.append("total_num,day_active,month_active,new_user,pay_user_num,pay_amount,new_reg_pay_user_num");
		}else{
			sql.append("ifnull(sum(total_num),0),ifnull(sum(day_active),0),ifnull(sum(month_active),0),");
			sql.append("ifnull(sum(new_user),0),ifnull(sum(pay_user_num),0),ifnull(sum(pay_amount),0),");
			sql.append("ifnull(sum(new_reg_pay_user_num),0)");
		}
		sql.append(",half_hour_index from home_page_stats");
		sql.append(" where 1=1");
		if(dates!=null){
			sql.append(" and time between '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		if(sysNum!=null && sysNum.intValue()!=0){
			sql.append(" and sys_num = ");
			sql.append(sysNum);
		}
		
		if(channel!=null && channel.intValue()!=0){
			sql.append(" and channel = '");
			sql.append(channel);
			sql.append("'");
		}else{
			sql.append(" group by time,sys_num");
		}
		sql.append(" order by time desc");
		System.out.println("打印sql数据 " + sql.toString());
		homePageStatsDao.closeSession(DBSource.ADMIN);
		return homePageStatsDao.findSQL_(sql.toString());
	}
	public List<Object> findData(String[] dates, Integer sysNum,
			Integer channel, String qn) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select time,sys_num,");
		if(channel!=null && channel.intValue()!=0){
			sql.append("total_num,day_active,month_active,new_user,pay_user_num,pay_amount,new_reg_pay_user_num,diamond_used");
		}else{
			sql.append("ifnull(sum(total_num),0),ifnull(sum(day_active),0),ifnull(sum(month_active),0),");
			sql.append("ifnull(sum(new_user),0),ifnull(sum(pay_user_num),0),ifnull(sum(pay_amount),0),");
			sql.append("ifnull(sum(new_reg_pay_user_num),0),ifnull(sum(diamond_used),0)");
		}
		sql.append(",half_hour_index from home_page_stats");
		sql.append(" where 1=1");
		if(dates!=null){
			sql.append(" and time between '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		if(sysNum!=null && sysNum.intValue()!=0){
			sql.append(" and sys_num = ");
			sql.append(sysNum);
		}
		if(qn!=null && !"-1".equals(qn)){
			sql.append(" and qn = '");
			sql.append(qn);
			sql.append("'");
		}
		if(channel!=null && channel.intValue()!=0){
			sql.append(" and channel = '");
			sql.append(channel);
			sql.append("'");
		}else{
			sql.append(" group by time,sys_num");
		}
		sql.append(" order by time desc");
		System.out.println("打印sql数据 " + sql.toString());
		homePageStatsDao.closeSession(DBSource.ADMIN);
		return homePageStatsDao.findSQL_(sql.toString());
	}
	
	public HomePageStats findHomePageStats(HomePageStats homePageStats){
		String sql = "SELECT * FROM home_page_stats h WHERE h.CHANNEL = "+homePageStats.getChannel()
				+ " AND h.SYS_NUM = "+homePageStats.getSysNum()+" AND h.TIME = '2013-12-23'";
		List<Object> objects =  homePageStatsDao.findSQL_(sql);
		if(objects != null && objects.size() > 0){
			Object object = objects.get(0);
		}
		return null;
	}
	
	/**
	 * 查询首页汇总数据
	 * @param dates
	 * @param sysNum
	 * @param channel
	 * @return
	 */
	public List<Object> findTotalData(String[] dates,Integer sysNum,Integer channel, String qn){
		StringBuilder sql = new StringBuilder();
		sql.append("select ifnull(sum(newUser),0),ifnull(sum(payUserNum),0),ifnull(sum(payAmount),0),ifnull(sum(diamondUsed),0),ifnull(sum(dayActive),0) from (");
		sql.append("select ");
		if(channel!=null && channel.intValue()!=0){
			sql.append("new_user as newUser,pay_user_num as payUserNum,pay_amount as payAmount,diamond_used as diamondUsed,day_active as dayActive");
		}else{
			sql.append("ifnull(sum(new_user),0) as newUser,ifnull(sum(pay_user_num),0) as payUserNum,ifnull(sum(pay_amount),0) as payAmount,ifnull(sum(diamond_used),0) as diamondUsed,ifnull(sum(day_active),0) as dayActive");
		}
		sql.append(" from home_page_stats where 1=1");
		if(dates!=null){
			sql.append(" and time between '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		if(sysNum!=null && sysNum.intValue()!=0){
			sql.append(" and sys_num = ");
			sql.append(sysNum);
		}

		if(qn!=null && !"-1".equals(qn)){
			sql.append(" and qn = '");
			sql.append(qn);
			sql.append("'");
		}
		if(channel!=null && channel.intValue()!=0){
			sql.append(" and channel = '");
			sql.append(channel);
			sql.append("'");
		}else{
			sql.append(" group by time,sys_num");
		}
		sql.append(") c");
		homePageStatsDao.closeSession(DBSource.ADMIN);
		return homePageStatsDao.findSQL_(sql.toString());
	}

	/**
	 * 首页数据查询统计(新的需求, 不要引用)
	 * @param dates
	 * @param sysNum
	 * @param channel
	 * @param qn
	 * @return
	 */
	public List<Object> findDataNew(String[] dates, Integer sysNum,
			Integer channel, String qn) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select time,sys_num,");
		if(channel!=null && channel.intValue()!=0){
			sql.append("ifnull(sum(total_num),0),ifnull(sum(day_active),0),ifnull(sum(month_active),0),ifnull(sum(new_user),0),ifnull(sum(pay_user_num),0),ifnull(sum(pay_amount),0),ifnull(sum(new_reg_pay_user_num),0),ifnull(sum(diamond_used),0)");
		}else{
			sql.append("ifnull(sum(total_num),0),ifnull(sum(day_active),0),ifnull(sum(month_active),0),");
			sql.append("ifnull(sum(new_user),0),ifnull(sum(pay_user_num),0),ifnull(sum(pay_amount),0),");
			sql.append("ifnull(sum(new_reg_pay_user_num),0),ifnull(sum(diamond_used),0)");
		}
		sql.append(",half_hour_index,channel from home_page_stats");
		sql.append(" where 1=1");
		if(dates!=null){
			sql.append(" and time between '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		if(sysNum!=null && sysNum.intValue()!=0){
			sql.append(" and sys_num = ");
			sql.append(sysNum);
		}
		if(qn!=null && !"-1".equals(qn)){
			sql.append(" and qn = '");
			sql.append(qn);
			sql.append("'");
		}
		if(channel!=null && channel.intValue()!=0){
			sql.append(" and channel = '");
			sql.append(channel);
			sql.append("'");
			sql.append(" group by time, channel");
		}else{
			sql.append(" group by time,channel");
		}
		sql.append(" order by time desc");
		System.out.println("打印sql数据 " + sql.toString());
		homePageStatsDao.closeSession(DBSource.ADMIN);
		return homePageStatsDao.findSQL_(sql.toString());
	}
	
	/**
	 * 分页查询首页数据(新的需求, 不要引用)
	 * @param dates
	 * @param sysNum
	 * @param channel
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public IPage<Object> findPageDataNew(String[] dates, Integer channel, String qn, int pageSize, int pageIndex) {
		StringBuilder sql = new StringBuilder();
		sql.append("select time, sys_num, ");
		if (channel != null && channel.intValue() != 0) {
			sql.append("ifnull(sum(total_num),0), ifnull(sum(day_active),0), ifnull(sum(month_active),0), ifnull(sum(new_user),0), ifnull(sum(pay_user_num),0), ifnull(sum(pay_amount),0), ifnull(sum(new_reg_pay_user_num),0), ifnull(sum(diamond_used),0)");
		} else {
			sql.append("ifnull(sum(total_num),0), ifnull(sum(day_active),0), ifnull(sum(month_active),0),");
			sql.append("ifnull(sum(new_user),0), ifnull(sum(pay_user_num),0), ifnull(sum(pay_amount),0),");
			sql.append("ifnull(sum(new_reg_pay_user_num),0), ifnull(sum(diamond_used),0)");
		}
		sql.append(",half_hour_index,channel from home_page_stats");
		sql.append(" where 1=1");
		if (dates != null) {
			sql.append(" and time between '");
			sql.append(dates[0]);
			sql.append("' AND '");
			sql.append(dates[1]);
			sql.append("'");
		}
		
		if (qn != null && !"-1".equals(qn)) {
			sql.append(" and qn = '");
			sql.append(qn);
			sql.append("'");
		}
		if (channel != null && channel.intValue() != 0) {
			sql.append(" and channel = '");
			sql.append(channel);
			sql.append("'");
			sql.append(" group by time, channel");
			sql.append(" order by time desc");
			homePageStatsDao.closeSession(DBSource.ADMIN);
			
			return homePageStatsDao.findSQL_Page_Have_Group(sql.toString(), new ArrayList<Object>(), pageSize, pageIndex);
		} else {
			sql.append(" group by time,channel");
			sql.append(" order by time desc");
			homePageStatsDao.closeSession(DBSource.ADMIN);
			Log.info("打印数据 " + sql.toString());
			return homePageStatsDao.findSQL_Page_Have_Group(sql.toString(), new ArrayList<Object>(), pageSize, pageIndex);
		}
	}
}
