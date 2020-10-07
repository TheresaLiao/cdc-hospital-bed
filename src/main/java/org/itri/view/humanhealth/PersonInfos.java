package org.itri.view.humanhealth;

import java.util.LinkedList;
import java.util.List;

import org.itri.view.humanhealth.hibernate.Patient;
import org.itri.view.humanhealth.personal.chart.Imp.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.personal.chart.dao.PersonState;
import org.itri.view.admin.dao.Type;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class PersonInfos {

	private List<PersonState> states;
	private PersonInfosDaoHibernateImpl hqe;

	@Init
	public void init() {
		hqe = new PersonInfosDaoHibernateImpl();
		queryStates();
	}

	public List<PersonState> getStates() {
		return states;
	}

	@NotifyChange({ "states" })
	@Command
	public void refreshInfo() {

		hqe = new PersonInfosDaoHibernateImpl();
		queryStates();
	}

	private void queryStates() {
		List<Patient> patientList = hqe.getPatientList();
		states = new LinkedList<>();
		for (Patient p : patientList) {
			PersonState state = new PersonState();

			state.setName(p.getPatientInfos().stream().findFirst().get().getName());
			state.setBedRoom(p.getRoom().getRoomNum());

			state.setHeartBeat(p.getRtHeartRhythmRecords().stream().findFirst().get().getHeartRateData());
			state.setOximeter(p.getRtOximeterRecords().stream().findFirst().get().getOximeterData());
			state.setBreathRate(p.getRtHeartRhythmRecords().stream().findFirst().get().getBreathData());
			state.setBodyTemperature(p.getRtTempPadRecords().stream().findFirst().get().getBodyTempData());
			// state.setStatus(Status.DegreeGreen);
			state.setType(Type.Customer);
			states.add(state);
		}
	}

}
