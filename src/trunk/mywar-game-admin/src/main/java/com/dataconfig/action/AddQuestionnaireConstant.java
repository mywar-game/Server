package com.dataconfig.action;

import com.dataconfig.bo.QQuestionnaireConstant;
import com.dataconfig.service.QuestionnaireConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddQuestionnaireConstant extends ALDAdminActionSupport implements ModelDriven<QQuestionnaireConstant> {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private QQuestionnaireConstant questionnaireConstant = new QQuestionnaireConstant();
	
	private String isCommit = "F";


	public String execute() throws Exception {
		if ("F".equals(this.isCommit)) {
			return INPUT;
		} else {
			QuestionnaireConstantService questionnaireConstantService = ServiceCacheFactory.getServiceCache().getService(QuestionnaireConstantService.class);
			questionnaireConstantService.addQuestionnaireConstant(questionnaireConstant);	
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

}
