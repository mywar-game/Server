/**
 * 
 */
package com.framework.common;

import java.util.Date;

import com.framework.log.LogSystem;

/**
 * @author huanglong
 *
 * 2011-10-15
 */
public class ALDAdminStatsDatePageActionSupport extends ALDAdminPageActionSupport {
	
	/**  **/
	private static final long serialVersionUID = 1L;

	/**  **/
	private Date startDate;
	
	/**  **/
	private Date endDate;
	@Override
	public void addActionError(String anErrorMessage){
		LogSystem.info(anErrorMessage);
	}
	@Override
	public void addActionMessage(String aMessage){
		LogSystem.info(aMessage);
	}
	@Override
	public void addFieldError(String fieldName, String errorMessage){
		LogSystem.info("属性:"+fieldName+":"+errorMessage);
	}
	//表中某项数据的最大值(用于绘图yAxis)
//	private Integer yAxisMaxValue;
	
	@Override
	public String toString() {
		return "ALDAdminStatsDatePageActionSupport [endDate = " + endDate
				+ ", startDate = " + startDate
//				+ ", yAxisMaxValue = " + yAxisMaxValue
				+ "]";
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
