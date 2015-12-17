package com.adminTool.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adminTool.bo.AdminMarquee;
import com.adminTool.bo.MallDiscount;
import com.adminTool.dao.MallDiscountDao;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.constant.SystemConstant;
import com.system.bo.TGameServer;

public class MallDiscountService {
	
	private MallDiscountDao mallDiscountDao;
	
	
	
	public void setMallDiscountDao(MallDiscountDao mallDiscountDao) {
		this.mallDiscountDao = mallDiscountDao;
	}

	//获取正在进行的打折活动
	public List<MallDiscount> getMallDiscountList() {
		return this.mallDiscountDao.find("from MallDiscount where status = 1", DBSource.ADMIN);
	}
	
	public IPage<MallDiscount> findMallDiscountList(Integer currentPage, Integer pageSize) {
		mallDiscountDao.closeSession(DBSource.ADMIN);
		return mallDiscountDao.findPage("from MallDiscount", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	public IPage<MallDiscount> findMallAuditList(Integer currentPage, Integer pageSize) {
		mallDiscountDao.closeSession(DBSource.ADMIN);
		return mallDiscountDao.findPage("from MallDiscount as md where md.status = 0 or md.status = -1", new ArrayList<Object>(), pageSize, currentPage);

	}
	
	public IPage<MallDiscount>findMallHistory(Integer currentPage, Integer pageSize) {
		mallDiscountDao.closeSession(DBSource.ADMIN);
		return mallDiscountDao.findPage("from MallDiscount as md where md.status = 2 or md.status = 3", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	public void deleteMallDiscount(String  activityId) {
		mallDiscountDao.closeSession(DBSource.ADMIN);
		String query = "delete from MallDiscount as md where md.activityId = '" + activityId + "'";
		mallDiscountDao.execute(query);
	}
	
	
	public MallDiscount findOneMallDiscount(String activityId) {
		mallDiscountDao.closeSession(DBSource.ADMIN);
		return mallDiscountDao.loadBy("activityId", activityId, DBSource.ADMIN);
	}
	
	public void updateMallDiscount(MallDiscount mallDiscount) {
		mallDiscountDao.update(mallDiscount, DBSource.ADMIN);
	}
	
	public void addMallDiscount(MallDiscount mallDiscount) {
		mallDiscountDao.save(mallDiscount, DBSource.ADMIN);
	}
	
	public boolean isOutOfDate(Date endTime) {
		Date now = new Date();
		if (now.after(endTime)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String computCountdown(Date startTime, Date endTime) {
		Date now = new Date();
		if (now.before(startTime)) {
			return "活动还未开始";
		}
		
		long nowMS = now.getTime();
		long endTimeMS = endTime.getTime();

		long timeDiff = endTimeMS - nowMS; // 时间差的毫秒数

		// 计算出相差天数
		int days = (int) Math.floor(timeDiff / (24 * 3600 * 1000));

		long leave1 = timeDiff % (24 * 3600 * 1000); // 计算天数后剩余的毫秒数
		int hours = (int) Math.floor(leave1 / (3600 * 1000)); // 计算相差分钟数

		long leave2 = leave1 % (3600 * 1000); // 计算小时数后剩余的毫秒数
		int minutes = (int) Math.floor(leave2 / (60 * 1000)); // 计算相差秒数

		long leave3 = leave2 % (60 * 1000); // 计算分钟数后剩余的毫秒数
		int seconds = Math.round(leave3 / 1000);
		
		if (days > 0) {
			return (days + "天 " + hours + "小时 " + minutes + "分钟 " + seconds + "秒");
		} else if (hours > 0) {
			return (hours + "小时 " + minutes + "分钟 " + seconds + "秒 ");
		} else if (minutes > 0) {
			return (minutes + "分钟 " + seconds + "秒");
		} else if (seconds > 0) {
			return (seconds + " 秒");
		} else {
			return "活动已经结束";
		}
	}

	
}
