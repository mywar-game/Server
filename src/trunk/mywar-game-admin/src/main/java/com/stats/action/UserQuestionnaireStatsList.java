package com.stats.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;

import com.dataconfig.bo.QQuestionnaireConstant;
import com.dataconfig.service.QuestionnaireConstantService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserQuestionnaireStats;
import com.stats.service.UserQuestionnaireStatsService;

public class UserQuestionnaireStatsList extends ALDAdminStatsDatePageActionSupport {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, Map<Integer,Integer>> statsMap;

	private List<QQuestionnaireConstant> questionnaireConstantList;
	
	private List<List<Map<String, String>>> allOptionList = new ArrayList<List<Map<String,String>>>();
	
	public String execute(){
		UserQuestionnaireStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserQuestionnaireStatsService.class);
		List<UserQuestionnaireStats> statsList = statsService.findList();
		statsMap = new HashMap<Integer, Map<Integer,Integer>>();
		for (UserQuestionnaireStats stats : statsList) {
			String[] choiceArr = stats.getChoice().split(",");
			Map<Integer,Integer> choiceMap = new HashMap<Integer, Integer>();
			for (String choice : choiceArr) {
				choiceMap.put(Integer.valueOf(choice.split(":")[0]), Integer.valueOf(choice.split(":")[1]));
			}
			statsMap.put(stats.getQuestionId(), choiceMap);
		}
		QuestionnaireConstantService questionnaireConstantService = ServiceCacheFactory.getServiceCache().getService(QuestionnaireConstantService.class);
		questionnaireConstantList = questionnaireConstantService.findQQuestionnaireConstantList();
		if (questionnaireConstantList != null) {
				for (QQuestionnaireConstant questionnaireConstant : questionnaireConstantList) {
					try {
						allOptionList.add(questionnaireConstantService.parseOptions(questionnaireConstant));
					} catch (JSONException e) {
						LogSystem.error(e, e.getMessage());
					}
				}
		}
		return SUCCESS;
	}
	
	public void setQuestionnaireConstantList(
			List<QQuestionnaireConstant> questionnaireConstantList) {
		this.questionnaireConstantList = questionnaireConstantList;
	}

	public List<QQuestionnaireConstant> getQuestionnaireConstantList() {
		return questionnaireConstantList;
	}

	public void setAllOptionList(List<List<Map<String, String>>> allOptionList) {
		this.allOptionList = allOptionList;
	}

	public List<List<Map<String, String>>> getAllOptionList() {
		return allOptionList;
	}

	public void setStatsMap(Map<Integer, Map<Integer,Integer>> statsMap) {
		this.statsMap = statsMap;
	}

	public Map<Integer, Map<Integer,Integer>> getStatsMap() {
		return statsMap;
	}
}
