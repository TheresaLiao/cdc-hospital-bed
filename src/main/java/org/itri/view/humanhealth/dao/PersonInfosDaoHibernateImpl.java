package org.itri.view.humanhealth.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.itri.view.humanhealth.hibernate.Patient;
import org.itri.view.util.HibernateUtil;

public class PersonInfosDaoHibernateImpl {
	
	public List<Patient> getPatientList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Patient> tempPatientList = new ArrayList<Patient>();
		List<Patient> patientList = new ArrayList<Patient>();
		try{
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Patient.class);
			criteria.add(Restrictions.eq("isDeleted", false));
			criteria.addOrder(Order.asc("patientId"));
			tempPatientList = criteria.list();
			
			for (Patient p : tempPatientList) {
				Hibernate.initialize(p.getPatientInfos());
				Hibernate.initialize(p.getRtHeartRhythmRecords());
				Hibernate.initialize(p.getRtOximeterRecords());
				Hibernate.initialize(p.getRtHeartRhythmRecords());
				Hibernate.initialize(p.getRtTempPadRecords());
				patientList.add(p);
			}
			tx.commit();
		} 
		catch (Exception e){
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			session.close();
		}
		return patientList;
	}
	
	

}
