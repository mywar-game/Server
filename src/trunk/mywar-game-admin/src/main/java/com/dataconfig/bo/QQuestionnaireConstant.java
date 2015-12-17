package com.dataconfig.bo;

/**
 * QQuestionnaireConstant entity. @author MyEclipse Persistence Tools
 */

public class QQuestionnaireConstant implements java.io.Serializable {

	// Fields

	private Integer questionId;
	private String question;
	private String options;

	// Constructors

	/** default constructor */
	public QQuestionnaireConstant() {
	}

	/** full constructor */
	public QQuestionnaireConstant(String question, String options) {
		this.question = question;
		this.options = options;
	}

	// Property accessors

	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptions() {
		return this.options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

}