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
	private List<PersonState> states;
	private PersonInfosDaoHibernateImpl hqe;

	@Init
	public void init() {
		hqe = new PersonInfosDaoHibernateImpl();
		queryStates();
	}

	private void queryStates() {
		List<Patient> patientList = hqe.getPatientList();

		states = new LinkedList<>();

		for (Patient p : patientList) {
			PersonState state = new PersonState();
			
			state.setName(p.getPatientInfos().stream().findFirst().get().getName());
			state.setBedRoom("201-1");
			System.out.print(state.getName());
			
			if(p.getPatientInfos().stream().findFirst().get().getPatient().getPatientId() == 1) {
				
				state.setHeartBeat(p.getRtHeartRhythmRecords().stream().findFirst().get().getHeartRateData());
				state.setSpo2(p.getRtOximeterRecords().stream().findFirst().get().getOximeterData());
				state.setBreathRate( p.getRtHeartRhythmRecords().stream().findFirst().get().getBreathData());
				state.setBodyTemperature(p.getRtTempPadRecords().stream().findFirst().get().getBodyTempData());
			}
			
			state.setStatus(Status.DegreeGreen);
			state.setType(Type.Customer);
			states.add(state);
		}
	}

	public List<PersonState> getDataList() {
		return states;
	}
}
