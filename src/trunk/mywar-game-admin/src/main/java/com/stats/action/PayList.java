package com.stats.action;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.service.PayListService;

/**
 * 付费账号数
 * @author Administrator
 *
 */
public class PayList extends ALDAdminStatsDatePageActionSupport {

	private static final long serialVersionUID = 2447987284557265424L;
	private String beginDate = "";
	private String enDate = "";
	
	private BigInteger count;
	private Boolean isCommit = false;
	
	
	public String execute() {
		
		PayListService payListService = ServiceCacheFactory.getServiceCache().getService(PayListService.class);
		if (isCommit) {
			
		} else {
			List<Object> objList = payListService.findLastAndLeastTime();
			if (objList != null) {
				for (int i = 0; i < objList.size(); i++) {
					Object[] tempObj = (Object[]) objList.get(i);
					Timestamp maxTime = (Timestamp) tempObj[0];
					Timestamp minTime = (Timestamp) tempObj[1];
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if (maxTime == null || minTime == null) {
						
					} else {
						beginDate = df.format(minTime.getTime());
						enDate = df.format(maxTime.getTime());
					}
				}
			}
		}
		if (beginDate == "" || beginDate.equals("")) {
			count = null;
			beginDate = "表中没有数据";
		} else {
			count = payListService.findInDate(beginDate, enDate);
		}
		return SUCCESS;
	}
	
	public Boolean getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(Boolean isCommit) {
		this.isCommit = isCommit;
	}

	public BigInteger getCount() {
		return count;
	}
	public void setCount(BigInteger count) {
		this.count = count;
	}
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEnDate() {
		return enDate;
	}

	public void setEnDate(String enDate) {
		this.enDate = enDate;
	}
}
