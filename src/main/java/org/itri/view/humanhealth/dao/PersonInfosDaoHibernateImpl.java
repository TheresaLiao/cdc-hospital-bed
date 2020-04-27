package org.itri.view.humanhealth.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.itri.view.humanhealth.hibernate.Patient;
import org.itri.view.util.HibernateUtil;

public class PersonInfosDaoHibernateImpl {
	
	public static void main(String[] args) {
		
    }
	
	public List<Patient> getPatientList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Patient> patientList = new ArrayList<Patient>();
		try{
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Patient.class);
			criteria.add(Restrictions.eq("isDeleted", false));
			patientList = criteria.list();
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
