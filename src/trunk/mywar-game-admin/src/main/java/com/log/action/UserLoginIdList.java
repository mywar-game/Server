package com.log.action;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserLoginLogService;

/**
 * 查询用户登陆id
 * 
 * @author yezp
 */
public class UserLoginIdList extends ALDAdminLogPageActionSupport {

	private static final long serialVersionUID = 2492800332018413703L;

	private String isCommit = "F";
	private List<String> lodoIdsList;

	public String execute() {
		if (isCommit.equals("F")) {
			return SUCCESS;
		}

		UserLoginLogService userLoginLogService = ServiceCacheFactory
				.getServiceCache().getService(UserLoginLogService.class);
		List<Object> list = userLoginLogService.findUserLoginIdList(
				super.getStartDate(), super.getEndDate());

		lodoIdsList = new ArrayList<String>();
		String lodoIds = "";
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			if (lodoIds.length() == 0) {
				lodoIds = obj.toString();
			} else {
				lodoIds = lodoIds + "," + obj.toString();
			}

			if (i != 0 && i % 200 == 0) {
				lodoIdsList.add(lodoIds);
				lodoIds = "";
			}
		}
		lodoIdsList.add(lodoIds);

		return SUCCESS;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public List<String> getLodoIdsList() {
		return lodoIdsList;
	}

	public void setLodoIdsList(List<String> lodoIdsList) {
		this.lodoIdsList = lodoIdsList;
	}

}
