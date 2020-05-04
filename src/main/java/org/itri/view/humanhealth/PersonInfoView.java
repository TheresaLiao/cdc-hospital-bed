package org.itri.view.humanhealth;

import java.util.LinkedList;
import java.util.List;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.dao.PersonState;
import org.itri.view.humanhealth.dao.Status;
import org.itri.view.humanhealth.hibernate.Patient;
import org.zkoss.admin.ecommerce.dao.EcommerceDao;
import org.zkoss.admin.ecommerce.dao.Product;
import org.zkoss.admin.ecommerce.dao.Type;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class PersonInfoView {
	private List<Product> productList;
	private List<PersonState> personStateList;
	private PersonInfosDaoHibernateImpl hqe;

	@Init
	public void init() {
		productList = EcommerceDao.queryProduct();
		hqe = new PersonInfosDaoHibernateImpl();
		queryStates();
	}

	@NotifyChange({ "personStateList" })
	@Command
	public void refreshInfo() {
		System.out.println("refreshInfo");
		hqe = new PersonInfosDaoHibernateImpl();
		queryStates();
	}

	public List<Product> getProductList() {
		return productList;
	}

	public List<PersonState> getPersonStateList() {
		return personStateList;
	}

	private void queryStates() {
		List<Patient> patientList = hqe.getPatientList();
		personStateList = new LinkedList<>();
		for (Patient p : patientList) {
			PersonState patient = new PersonState();

			patient.setId("personInfo-" + p.getPatientId());
			patient.setName(p.getPatientInfos().stream().findFirst().get().getName());
			patient.setBedRoom(p.getRoom().getRoomNum());
			patient.setHeartBeat(p.getRtHeartRhythmRecords().stream().findFirst().get().getHeartRateData());
			patient.setOximeter(p.getRtOximeterRecords().stream().findFirst().get().getOximeterData());
			patient.setBreathRate(p.getRtHeartRhythmRecords().stream().findFirst().get().getBreathData());
			patient.setBodyTemperature(p.getRtTempPadRecords().stream().findFirst().get().getBodyTempData());
			patient.setStatus(Status.DegreeGreen);
			patient.setType(Type.Customer);
			personStateList.add(patient);
		}
	}
}
