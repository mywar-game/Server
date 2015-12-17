package com.dataconfig.action;

import com.dataconfig.bo.QQuestionnaireConstant;
import com.dataconfig.service.QuestionnaireConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelQuestionnaireConstant extends ALDAdminActionSupport implements ModelDriven<QQuestionnaireConstant> {

	private static final long serialVersionUID = 1;
	
	private QQuestionnaireConstant questionnaireConstant = new QQuestionnaireConstant();
	
	public void executeDel() {
		QuestionnaireConstantService questionnaireConstantService = ServiceCacheFactory.getServiceCache().getService(QuestionnaireConstantService.class);
		questionnaireConstantService.delQuestionnaireConstant(questionnaireConstant.getQuestionId());
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

}
