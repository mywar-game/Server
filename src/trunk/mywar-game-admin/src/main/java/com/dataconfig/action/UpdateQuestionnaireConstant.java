package com.dataconfig.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;

import com.dataconfig.bo.QQuestionnaireConstant;
import com.dataconfig.service.QuestionnaireConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateQuestionnaireConstant extends ALDAdminActionSupport implements ModelDriven<QQuestionnaireConstant> {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private QQuestionnaireConstant questionnaireConstant = new QQuestionnaireConstant();
	
	private String isCommit = "F";
	
	private List<Map<String, String>> optionList = new ArrayList<Map<String, String>>();

	public String execute() throws Exception {
		QuestionnaireConstantService questionnaireConstantService = ServiceCacheFactory.getServiceCache().getService(QuestionnaireConstantService.class);
		if ("F".equals(this.isCommit)) {
			questionnaireConstant = questionnaireConstantService.findOneQuestionnaireConstant(questionnaireConstant.getQuestionId());
			if (questionnaireConstant != null) {
				try {
					optionList = questionnaireConstantService.parseOptions(questionnaireConstant);
				} catch (JSONException e) {
					LogSystem.error(e, e.getMessage());
				}
			}
			return INPUT;
		} else {
			questionnaireConstantService.updateOneQuestionnaireConstant(questionnaireConstant);
			return SUCCESS;
		}
	}
	
	@Override
	public QQuestionnaireConstant getModel() {
		return questionnaireConstant;
	}

	public void setQuestionnaireConstant(QQuestionnaireConstant questionnaireConstant) {
		this.questionnaireConstant = questionnaireConstant;
	}

	public QQuestionnaireConstant getQuestionnaireConstant() {
		return questionnaireConstant;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setOptionList(List<Map<String, String>> optionList) {
		this.optionList = optionList;
	}

	public List<Map<String, String>> getOptionList() {
		return optionList;
	}

}
