package com.stats.service;

import java.util.List;

import com.framework.common.DBSource;
import com.framework.servicemanager.CustomerContextHolder;
import com.stats.bo.UserQuestionnaireStats;
import com.stats.dao.UserQuestionnaireStatsDao;

public class UserQuestionnaireStatsService {
	
	private UserQuestionnaireStatsDao userQuestionnaireStatsDao;
	
	public void save(List<UserQuestionnaireStats> statsList){
		userQuestionnaireStatsDao.saveBatch(statsList, DBSource.ADMIN);
	}
	
	public List<UserQuestionnaireStats> findList(){
		return userQuestionnaireStatsDao.find("from UserQuestionnaireStats stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), DBSource.ADMIN);
	}

	public void setUserQuestionnaireStatsDao(UserQuestionnaireStatsDao userQuestionnaireStatsDao) {
		this.userQuestionnaireStatsDao = userQuestionnaireStatsDao;
	}

	public UserQuestionnaireStatsDao getUserQuestionnaireStatsDao() {
		return userQuestionnaireStatsDao;
	}

}
