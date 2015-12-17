package com.log.action;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.UserBattleReport;
import com.log.service.BattleLogService;

/**
 * @author hzy
 * 2012-7-20
 */
public class BattleLogList extends ALDAdminLogPageActionSupport {
	
	/**  **/
	private static final long serialVersionUID = 7625543941895179533L;
	private String isCommit = "F";
	/**  **/
	private List<UserBattleReport> battleLogList;
	
	/** 战斗类型查询条件  **/
	private Integer battleType;
	
	@Override
	public String execute() throws Exception {
		BattleLogService battleLogService = ServiceCacheFactory.getServiceCache().getService(BattleLogService.class);
		IPage<Object> page = null;
		if(isCommit.equals("T")){
			String searchUserId = super.searchUser();
			//搜索玩家的时候出错了，返回
			if (super.getErroDescrip() != null) {
				return SUCCESS;
			}
			page = battleLogService.findBattleLogByCondition(searchUserId, battleType,super.getStartDate(),super.getEndDate(),super.getToPage(), super.getPageSize());
			//将查询后的结果格式化
			if (page != null) {
				List<Object> list = (List<Object>) page.getData();
				battleLogList = new ArrayList<UserBattleReport>();
				for(int i=0;i<list.size();i++){
					Object[] arr = (Object[])list.get(i);
					UserBattleReport report = new UserBattleReport();
					report.setUserBattleReportId(Integer.valueOf(arr[0].toString()));
					report.setUserId(arr[1].toString());
					report.setUserLevel(Integer.valueOf(arr[2].toString()));
					report.setTargetId(arr[3].toString());
					report.setType(Short.valueOf(arr[4].toString()));
					report.setFlag(Integer.valueOf(arr[5].toString()));
					report.setCreatTime(DateUtil.stringtoDate(arr[6].toString(), DateUtil.FORMAT_ONE));
					battleLogList.add(report);
				}
				super.setTotalPage(page.getTotalPage());
				super.setTotalSize(page.getPageSize());
			}
		}
		return SUCCESS;
	}


	public List<UserBattleReport> getBattleLogList() {
		return battleLogList;
	}


	public void setBattleLogList(List<UserBattleReport> battleLogList) {
		this.battleLogList = battleLogList;
	}


	public Integer getBattleType() {
		return battleType;
	}

	public void setBattleType(Integer battleType) {
		this.battleType = battleType;
	}


	public String getIsCommit() {
		return isCommit;
	}


	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
