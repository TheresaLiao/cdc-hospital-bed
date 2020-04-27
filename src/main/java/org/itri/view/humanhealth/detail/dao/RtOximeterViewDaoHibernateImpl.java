package org.itri.view.humanhealth.detail.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.itri.view.humanhealth.hibernate.OximeterRecord;
import org.itri.view.humanhealth.hibernate.RtOximeterRecord;
import org.itri.view.humanhealth.hibernate.RtTempPadRecord;
import org.itri.view.util.HibernateUtil;

public class RtOximeterViewDaoHibernateImpl {

	public List<RtOximeterRecord> getRtOximeterRecordList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<RtOximeterRecord> oximeterRecordList = new ArrayList<RtOximeterRecord>();

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(RtOximeterRecord.class);
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
