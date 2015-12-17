package com.dataconfig.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;

import com.dataconfig.bo.QQuestionnaireConstant;
import com.dataconfig.service.QuestionnaireConstantService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;

public class QuestionnaireConstantList extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -3349705427824768356L;

	private List<QQuestionnaireConstant> questionnaireConstantList;
	
	private List<List<Map<String, String>>> allOptionList = new ArrayList<List<Map<String,String>>>();
	
	public String execute() {
		QuestionnaireConstantService questionnaireConstantService = ServiceCacheFactory.getServiceCache().getService(QuestionnaireConstantService.class);
		IPage<QQuestionnaireConstant> list = questionnaireConstantService.findQuestionnaireConstantPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (list != null) {
			questionnaireConstantList = (List<QQuestionnaireConstant>) list.getData();
			if (questionnaireConstantList != null && questionnaireConstantList.size() > 0) {
				for (QQuestionnaireConstant questionnaireConstant : questionnaireConstantList) {
					try {
						allOptionList.add(questionnaireConstantService.parseOptions(questionnaireConstant));
					} catch (JSONException e) {
						LogSystem.error(e, e.getMessage());
					}
				}
			}
			super.setTotalPage(list.getTotalPage());
			super.setTotalSize(list.getTotalSize());
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
}
