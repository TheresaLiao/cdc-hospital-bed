package org.itri.view.humanhealth.detail.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.itri.view.humanhealth.hibernate.RtHeartRhythmRecord;
import org.itri.view.util.HibernateUtil;

public class BreathRateViewDaoHibernateImpl {
	
	public List<RtHeartRhythmRecord> getRtHeartRhythmRecordList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<RtHeartRhythmRecord> rtHeartRhythmRecordList = new ArrayList<RtHeartRhythmRecord>();
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(RtHeartRhythmRecord.class);
			rtHeartRhythmRecordList = criteria.list();
			tx.commit();
		} 
		catch (Exception e){
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			session.close();
		}
		return rtHeartRhythmRecordList;
	}
}
