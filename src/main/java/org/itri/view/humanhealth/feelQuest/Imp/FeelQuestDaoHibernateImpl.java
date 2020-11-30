package org.itri.view.humanhealth.feelQuest.Imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.itri.view.humanhealth.hibernate.SurveyQuestion;
import org.itri.view.humanhealth.hibernate.SurveyResult;
import org.itri.view.util.HibernateUtil;

public class FeelQuestDaoHibernateImpl {

	public List<SurveyQuestion> getSurveyQuestionList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<SurveyQuestion> resp = new ArrayList<SurveyQuestion>();
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(SurveyQuestion.class);
			criteria.addOrder(Order.asc("surveyQuestionId"));
			resp = criteria.list();

			for (SurveyQuestion item : resp) {
				Hibernate.initialize(item.getSurveyQuestionChoices());
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return resp;
	}

	public List<SurveyResult> getSurveyResultList(long patientId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<SurveyResult> resp = new ArrayList<SurveyResult>();
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(SurveyResult.class);
			criteria.add(Restrictions.eq("patient.patientId", patientId));

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date fromDate = calendar.getTime();

			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			Date toDate = calendar.getTime();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			System.out.println(sdf.format(fromDate));
			System.out.println(sdf.format(toDate));

//			criteria.add(Restrictions.between("timeCreated", fromDate, toDate));

			resp = criteria.list();

			for (SurveyResult item : resp) {
				Hibernate.initialize(item.getSurveyQuestion());
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return resp;
	}
}
