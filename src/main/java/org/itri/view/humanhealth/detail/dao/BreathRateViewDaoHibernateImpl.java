package org.itri.view.humanhealth.detail.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.itri.view.humanhealth.hibernate.HeartRhythmRecord;
import org.itri.view.humanhealth.hibernate.RtHeartRhythmRecord;
import org.itri.view.util.HibernateUtil;

public class BreathRateViewDaoHibernateImpl {

	public List<RtHeartRhythmRecord> getRtHeartRhythmRecordList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<RtHeartRhythmRecord> rtHeartRhythmRecordList = new ArrayList<RtHeartRhythmRecord>();
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(RtHeartRhythmRecord.class);
			
			long patientId = 1;
			criteria.add(Restrictions.eq("patient.patientId", patientId));
			
			rtHeartRhythmRecordList = criteria.list();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return rtHeartRhythmRecordList;
	}

	public List<HeartRhythmRecord> getHeartRhythmRecordList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<HeartRhythmRecord> heartRhythmRecordList = new ArrayList<HeartRhythmRecord>();
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(HeartRhythmRecord.class);

			long patientId = 1;
			criteria.add(Restrictions.eq("patient.patientId", patientId));

			Date now = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.MINUTE, -5);
			criteria.add(Restrictions.ge("timeCreated", calendar.getTime()));

			criteria.addOrder(Order.asc("timeCreated"));

			heartRhythmRecordList = criteria.list();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return heartRhythmRecordList;
	}
}
