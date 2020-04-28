package org.itri.view.humanhealth.detail;

import java.util.LinkedList;
import java.util.List;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.dao.PersonState;
import org.itri.view.humanhealth.dao.Status;
import org.itri.view.humanhealth.hibernate.Patient;
import org.zkoss.admin.ecommerce.dao.Type;

import org.zkoss.bind.annotation.Init;

public class PersonInfo {
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
		PersonState state = new PersonState();

		state.setName(patientList.get(0).getPatientInfos().stream().findFirst().get().getName());
		state.setBedRoom("201-1");

		state.setHeartBeat(patientList.get(0).getRtHeartRhythmRecords().stream().findFirst().get().getHeartRateData());
		state.setSpo2(patientList.get(0).getRtOximeterRecords().stream().findFirst().get().getOximeterData());
		state.setBreathRate(patientList.get(0).getRtHeartRhythmRecords().stream().findFirst().get().getBreathData());
		state.setBodyTemperature(patientList.get(0).getRtTempPadRecords().stream().findFirst().get().getBodyTempData());
		state.setStatus(Status.DegreeGreen);

		states.add(state);

	}

	public List<PersonState> getStates() {
		return states;
	}
}
