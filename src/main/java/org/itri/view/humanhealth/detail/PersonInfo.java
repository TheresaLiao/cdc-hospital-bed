package org.itri.view.humanhealth.detail;

import java.util.List;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.dao.PersonState;
import org.itri.view.humanhealth.hibernate.Patient;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class PersonInfo {

	private PersonState personState;

	private PersonInfosDaoHibernateImpl hqe;
	static String NORMAL_PATH = "./resources/image/MapImages/icon_indicator_no_01.png";
	static String WARNING_PATH = "./resources/image/MapImages/icon_indicator_o_01.png";

	@Init
	public void init() {
		hqe = new PersonInfosDaoHibernateImpl();
		queryStates();
	}

	@NotifyChange({ "personState" })
	@Command
	public void refreshPatientInfo() {
		hqe = new PersonInfosDaoHibernateImpl();
		queryStates();
	}

	private void queryStates() {
		List<Patient> patientList = hqe.getPatientListById();
		Patient p = patientList.get(0);

		personState = new PersonState();

		personState.setName(p.getPatientInfos().stream().findFirst().get().getName());
		personState.setBedRoom(p.getRoom().getRoomNum());

		personState.setHeartBeat(p.getRtHeartRhythmRecords().stream().findFirst().get().getHeartRateData());
		personState.setOximeter(p.getRtOximeterRecords().stream().findFirst().get().getOximeterData());
		personState.setBreathRate(p.getRtHeartRhythmRecords().stream().findFirst().get().getBreathData());
		personState.setBodyTemperature(p.getRtTempPadRecords().stream().findFirst().get().getBodyTempData());

		personState.setTotalStatus(NORMAL_PATH);
		if ((p.getHeartRateStatus().equals("W") && p.getBodyTempStatus().equals("W"))
				|| (p.getHeartRateStatus().equals("W") && p.getBreathStatus().equals("W"))
				|| (p.getBodyTempStatus().equals("W") && p.getBreathStatus().equals("W"))) {
			personState.setTotalStatus(WARNING_PATH);
		}

	}

	public PersonState getPersonState() {
		return personState;
	}
}
