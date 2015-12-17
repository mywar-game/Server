package com.dataconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.dataconfig.bo.QQuestionnaireConstant;
import com.dataconfig.dao.QQuestionnaireConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class QuestionnaireConstantService {

	private QQuestionnaireConstantDao questionnaireConstantDao;
	
	/**
	 * 新增问卷常量数据
	 * @param questionnaireConstant
	 */
	public void addQuestionnaireConstant(QQuestionnaireConstant questionnaireConstant) {
		questionnaireConstantDao.save(questionnaireConstant, DBSource.CFG);
	}
	
	/**
	 * 删除问卷常量数据
	 * @param keyWord
	 */
	public void delQuestionnaireConstant(Integer questionId) {
		questionnaireConstantDao.remove(findOneQuestionnaireConstant(questionId), DBSource.CFG);
	}
	
	/**
	 * 修改问卷常量数据
	 * @param questionnaireConstant
	 */
	public void updateOneQuestionnaireConstant(QQuestionnaireConstant questionnaireConstant) {
		questionnaireConstantDao.update(questionnaireConstant, DBSource.CFG);
	}

	/**
	 * 查询问卷常量数据
	 * @param keyWord
	 * @return
	 */
	public QQuestionnaireConstant findOneQuestionnaireConstant(Integer questionId) {
		return this.questionnaireConstantDao.loadBy("questionId", questionId, DBSource.CFG);
	}
	
	/**
	 * 查询问卷常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<QQuestionnaireConstant> findQuestionnaireConstantPageList(Integer currentPage, Integer pageSize) {
		questionnaireConstantDao.closeSession(DBSource.CFG);
		return questionnaireConstantDao.findPage("from QQuestionnaireConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public List<Map<String, String>> parseOptions(QQuestionnaireConstant questionnaireConstant) throws JSONException{
		List<Map<String, String>> optionList = new ArrayList<Map<String, String>>();
		
		String options = questionnaireConstant.getOptions();
		if (options != null && !"".equals(options)) {
			JSONArray optionJsonArr = new JSONArray(options); 
			for (int i = 0; i < optionJsonArr.length(); i++) {
				Map<String, String> optionMap = new HashMap<String, String>(); 
				JSONObject optionJsonObj = optionJsonArr.getJSONObject(i); 
				int optionId = optionJsonObj.getInt("optionId"); 
				String option = optionJsonObj.getString("option");
				optionMap.put("optionId", optionId+""); 
				optionMap.put("option", option); 
				optionList.add(optionMap); 
			}
		}
		return optionList; 
	}
	
	public List<QQuestionnaireConstant> findQQuestionnaireConstantList() {
		questionnaireConstantDao.closeSession(DBSource.CFG);
		return questionnaireConstantDao.findAll();
	}

	public void setQuestionnaireConstantDao(QQuestionnaireConstantDao questionnaireConstantDao) {
		this.questionnaireConstantDao = questionnaireConstantDao;
	}

	public QQuestionnaireConstantDao getQuestionnaireConstantDao() {
		return questionnaireConstantDao;
	}
}
