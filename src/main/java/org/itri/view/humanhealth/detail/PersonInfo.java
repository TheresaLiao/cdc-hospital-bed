package org.itri.view.humanhealth.detail;


import java.util.LinkedList;
import java.util.List;

import org.itri.view.humanhealth.hibernate.Patient;
import org.itri.view.humanhealth.personal.chart.Imp.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.personal.chart.dao.PersonState;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class PersonInfo {

	private List<PersonState> personStateList;
	private PersonInfosDaoHibernateImpl hqe;
	static String NORMAL_PATH = "./resources/image/MapImages/icon_indicator_no_01.png";
	static String WARNING_PATH = "./resources/image/MapImages/icon_indicator_o_01.png";

	@Init
	public void init() {
		hqe = new PersonInfosDaoHibernateImpl();
		queryStates();
	}

	@NotifyChange({ "personStateList" })
	@Command
	public void refreshPatientInfo() {
		hqe = new PersonInfosDaoHibernateImpl();
		queryStates();
	}

	public List<PersonState> getPersonStateList() {
		return personStateList;
	}

	private void queryStates() {

		List<Patient> patientList = hqe.getPatientTotalNewsScoreFourList();
		personStateList = new LinkedList<>();
		for (Patient p : patientList) {
			PersonState patient = new PersonState();

			patient.setId(p.getPatientId());
			patient.setName(p.getPatientInfos().stream().findFirst().get().getName());
			patient.setBedRoom(p.getRoom().getRoomNum());
			patient.setHeartBeat(p.getRtHeartRhythmRecords().stream().findFirst().get().getHeartRateData());
			patient.setOximeter(p.getRtOximeterRecords().stream().findFirst().get().getOximeterData());
			patient.setBreathRate(p.getRtHeartRhythmRecords().stream().findFirst().get().getBreathData());
			patient.setBodyTemperature(p.getRtTempPadRecords().stream().findFirst().get().getBodyTempData());
			patient.setTotalNewsScore(p.getTotalNewsScore());

			
			personStateList.add(patient);
		}
	}
}
