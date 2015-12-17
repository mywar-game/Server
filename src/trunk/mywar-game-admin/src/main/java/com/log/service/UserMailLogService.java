package com.log.service;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.log.bean.MailAttach;
import com.log.bo.UserMailLog;
import com.log.dao.UserMailLogDao;

public class UserMailLogService {
	
	private UserMailLogDao userMailLogDao;
	
	public UserMailLogDao getUserMailLogDao() {
		return userMailLogDao;
	}

	public void setUserMailLogDao(UserMailLogDao userMailLogDao) {
		this.userMailLogDao = userMailLogDao;
	}

	public IPage<UserMailLog> getUserMailLogList(int searchType, String searchUserId, Integer currentPage, Integer pageSize) {
		StringBuffer sb = new StringBuffer("from UserMailLog where 1 = 1");
		if (searchType == 1) {
			sb.append(" and senderId = '").append(searchUserId).append("'");
		} else if (searchType == 2) {
			sb.append(" and senderId = '").append(searchUserId).append("'");
		} else if (searchType == 3) {
			sb.append(" and receiveId = '").append(searchUserId).append("'");
		}
		sb.append(" order by sendTime DESC");
		userMailLogDao.closeSession(DBSource.LOG);
		return userMailLogDao.findPage(sb.toString(), new ArrayList<UserMailLog>(), pageSize, currentPage);
	}
	
	public List<MailAttach> parseMailAttach(String mailAttach) throws Exception{
		List<MailAttach> mailAttachList = new ArrayList<MailAttach>();
		JSONArray jsonArray = new JSONArray(mailAttach);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject one = jsonArray.getJSONObject(i); 
			Integer attachId = one.getInt("attachId"); 
			Integer attachType = one.getInt("attachType"); 
			Integer attachNum = one.getInt("attachNum");
			mailAttachList.add(new MailAttach(attachId,attachType,attachNum));
		}
		return mailAttachList;
	}

}
