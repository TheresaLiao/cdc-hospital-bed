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
import org.itri.view.humanhealth.hibernate.OximeterRecord;
import org.itri.view.humanhealth.hibernate.RtOximeterRecord;
import org.itri.view.util.HibernateUtil;

public class RtOximeterViewDaoHibernateImpl {

	public List<RtOximeterRecord> getRtOximeterRecordList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<RtOximeterRecord> oximeterRecordList = new ArrayList<RtOximeterRecord>();

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(RtOximeterRecord.class);
			
			long patientId = 2;
			criteria.add(Restrictions.eq("patient.patientId", patientId));
			
			oximeterRecordList = criteria.list();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return oximeterRecordList;
	}

	public List<OximeterRecord> getOximeterRecordList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<OximeterRecord> oximeterRecordList = new ArrayList<OximeterRecord>();

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(OximeterRecord.class);

			long patientId = 2;
			criteria.add(Restrictions.eq("patient.patientId", patientId));

			Date now = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.MINUTE, -5);
			criteria.add(Restrictions.ge("timeCreated", calendar.getTime()));

			criteria.addOrder(Order.asc("timeCreated"));

			oximeterRecordList = criteria.list();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return oximeterRecordList;
	}
}
