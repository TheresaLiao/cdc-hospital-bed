package org.itri.view.humanhealth.hibernate;

import javax.persistence.GeneratedValue;import javax.persistence.SequenceGenerator;import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SurveyResult generated by hbm2java
 */
@SuppressWarnings("serial")@Entity
@Table(name = "survey_result")
public class SurveyResult implements java.io.Serializable {

	private long surveyResultId;
	private SurveyQuestion surveyQuestion;
	private Patient patient;
	private String surveyQuestionChoiceArray;
	private Date timeCreated;

	public SurveyResult() {
	}

	public SurveyResult(long surveyResultId, SurveyQuestion surveyQuestion, Patient patient,
			String surveyQuestionChoiceArray, Date timeCreated) {
		this.surveyResultId = surveyResultId;
		this.surveyQuestion = surveyQuestion;
		this.patient = patient;
		this.surveyQuestionChoiceArray = surveyQuestionChoiceArray;
		this.timeCreated = timeCreated;
	}

	@SequenceGenerator(name="survey_result_seq", sequenceName="survey_result_survey_result_id_seq", allocationSize=1)	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="survey_result_seq")	@Id

	@Column(name = "survey_result_id", unique = true, nullable = false)
	public long getSurveyResultId() {
		return this.surveyResultId;
	}

	public void setSurveyResultId(long surveyResultId) {
		this.surveyResultId = surveyResultId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_question_id", nullable = false)
	public SurveyQuestion getSurveyQuestion() {
		return this.surveyQuestion;
	}

	public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Column(name = "survey_question_choice_array", nullable = false, length = 128)
	public String getSurveyQuestionChoiceArray() {
		return this.surveyQuestionChoiceArray;
	}

	public void setSurveyQuestionChoiceArray(String surveyQuestionChoiceArray) {
		this.surveyQuestionChoiceArray = surveyQuestionChoiceArray;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_created", nullable = false, length = 29)
	public Date getTimeCreated() {
		return this.timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

}
