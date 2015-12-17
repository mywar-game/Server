package com.log.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.bo.UserGoldLog;
import com.log.service.UserGoldLogService;

public class UserGoldLogList extends ALDAdminLogPageActionSupport {

	/** * */
	private static final long serialVersionUID = -3080972922307588892L;
	
	/**  */
	private List<UserGoldLog> userGoldLogList = new ArrayList<UserGoldLog>();
	
	private Integer searchCategory;
	//
	private Integer searchType;
	
	private String isCommit = "F";
	
	private Integer count = -1;
	
	private Integer useCount = -1;
	
	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String execute() throws Exception {
		UserGoldLogService userGoldLogService = ServiceCacheFactory.getServiceCache().getService(UserGoldLogService.class);
		if(isCommit.equals("T")){
			String searchUserId = super.searchUser();
			
			//搜索玩家的时候出错了，返回
			if (super.getErroDescrip() != null) {
				return SUCCESS;
			}
			IPage<Object> list = userGoldLogService.findPageLogListByCondition(searchUserId, super.getStartDate(), super.getEndDate(), searchCategory, searchType, super.getToPage(), super.getPageSize());
			count = userGoldLogService.countAll(1, searchUserId, super.getStartDate(), super.getEndDate());
			useCount = userGoldLogService.countAll(2, searchUserId, super.getStartDate(), super.getEndDate());
			
			if (list != null) {
				List<Object> ll = (List<Object>)list.getData();
				for(int i=0;i<ll.size();i++){
					Object[] arr = (Object[])ll.get(i);
					UserGoldLog log = new UserGoldLog();
					log.setUserId(arr[0].toString());
					log.setUserName(arr[1].toString());
					log.setLodoId(Integer.valueOf(arr[2].toString()));
					log.setUserLevel(Integer.valueOf(arr[3].toString()));
					log.setCategory(Integer.valueOf(arr[4].toString()));
					log.setType(Integer.valueOf(arr[5].toString()));
					log.setChangeNum(Integer.valueOf(arr[6].toString()));
					log.setTime(new Timestamp(DateUtil.stringtoDate(arr[7].toString(), DateUtil.FORMAT_ONE).getTime()));
					userGoldLogList.add(log);
				}
				super.setTotalPage(list.getTotalPage());
				super.setTotalSize(list.getTotalSize());
			}
		}
		
		return SUCCESS;
	}

	public void setUserGoldLogList(List<UserGoldLog> userGoldLogList) {
		this.userGoldLogList = userGoldLogList;
	}

	public List<UserGoldLog> getUserGoldLogList() {
		return userGoldLogList;
	}

	public void setSearchCategory(Integer searchCategory) {
		this.searchCategory = searchCategory;
	}

	public Integer getSearchCategory() {
		return searchCategory;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}