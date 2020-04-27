package org.itri.view.humanhealth;

import java.util.LinkedList;
import java.util.List;

import org.itri.view.humanhealth.dao.PersonState;
import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.dao.Status;
import org.itri.view.humanhealth.hibernate.Patient;
import org.zkoss.admin.ecommerce.dao.Type;

import org.zkoss.bind.annotation.Init;

public class PersonInfos {
	private List<PersonState> dataList;
	private PersonInfosDaoHibernateImpl hqe;

	@Init
	public void init() {
		hqe = new PersonInfosDaoHibernateImpl();
		queryStates();
	}

	private void queryStates() {

		
		List<Patient> patientList = hqe.getPatientList();

		dataList = new LinkedList<>();

		for (Patient p : patientList) {
			PersonState data = new PersonState();
			
			data.setName(p.getPatientInfos().stream().findFirst().get().getName());
			data.setBedRoom("201-1");
			
			data.setHeartBeat(p.getRtHeartRhythmRecords().stream().findFirst().get().getHeartRateData());
			data.setSpo2(p.getRtOximeterRecords().stream().findFirst().get().getOximeterData());
			data.setBreathRate( p.getRtHeartRhythmRecords().stream().findFirst().get().getBreathData());
			data.setBodyTemperature(p.getRtTempPadRecords().stream().findFirst().get().getBodyTempData());

			dataList.add(data);

		}
	}

	public List<PersonState> getDataList() {
		return dataList;
	}
}
