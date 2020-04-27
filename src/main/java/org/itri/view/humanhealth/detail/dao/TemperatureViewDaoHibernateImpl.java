package org.itri.view.humanhealth.detail.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.itri.view.humanhealth.hibernate.RtTempPadRecord;
import org.itri.view.util.HibernateUtil;

public class TemperatureViewDaoHibernateImpl {
	
	public List<RtTempPadRecord> getRtTempPadRecordList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<RtTempPadRecord> rtTempPadRecordList = new ArrayList<RtTempPadRecord>();
		try{
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(RtTempPadRecord.class);
			rtTempPadRecordList = criteria.list();
		
			tx.commit();
		} 
		catch (Exception e){
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			session.close();
		}
		return rtTempPadRecordList;
	}
}