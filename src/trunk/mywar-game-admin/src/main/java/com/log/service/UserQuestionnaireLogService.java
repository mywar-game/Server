package com.log.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.SystemStatsDate;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.util.DateUtil;
import com.log.dao.UserQuestionnaireLogDao;
import com.stats.bo.UserQuestionnaireStats;

public class UserQuestionnaireLogService {
	
	private UserQuestionnaireLogDao userQuestionnaireLogDao;
	
	public List<UserQuestionnaireStats> collectData(){
//		SELECT QUESTION_ID,CHOICE,COUNT(USER_ID) FROM user_questionnaire_log GROUP BY QUESTION_ID,CHOICE
		List<UserQuestionnaireStats> statsList = new ArrayList<UserQuestionnaireStats>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT a.QUESTION_ID,GROUP_CONCAT(CAST(a.CHOICE AS CHAR)) FROM (");
		sql.append(" SELECT QUESTION_ID,CONCAT_WS(':',CHOICE,COUNT(CHOICE)) AS CHOICE FROM user_questionnaire_log GROUP BY QUESTION_ID,CHOICE");
		sql.append(") a GROUP BY a.QUESTION_ID");
		userQuestionnaireLogDao.closeSession(DBSource.LOG);
		List<Object> list = userQuestionnaireLogDao.findSQL_(sql.toString());
		if (list == null  || list.size() == 0) {
			return statsList;
		}
		UserQuestionnaireStats stats;
		Date d = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		for (int i = 0; i < list.size(); i++) {
			stats = new UserQuestionnaireStats();
			stats.setQuestionId(Integer.valueOf(((Object[]) list.get(i))[0].toString()));
			stats.setChoice(((Object[]) list.get(i))[1].toString());
			stats.setSysNum(CustomerContextHolder.getSystemNum());
			stats.setDate(d);
			statsList.add(stats);
		}
		return statsList;
	}

	public void setUserQuestionnaireLogDao(UserQuestionnaireLogDao userQuestionnaireLogDao) {
		this.userQuestionnaireLogDao = userQuestionnaireLogDao;
	}

	public UserQuestionnaireLogDao getUserQuestionnaireLogDao() {
		return userQuestionnaireLogDao;
	}

}
