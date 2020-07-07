package org.itri.view.humanhealth.oneperson.detail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.dao.PersonState;
import org.itri.view.humanhealth.hibernate.Patient;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class PersonInfo {

	private PersonState personState = new PersonState();
	private PersonInfosDaoHibernateImpl hqe;

	private DateKeyValueSelectBox selectedDate;
	private List<DateKeyValueSelectBox> dateList = new ArrayList<DateKeyValueSelectBox>();

	private long patientId = 1;

	static String NORMAL_PATH = "./resources/image/MapImages/icon_indicator_no_01.png";
	static String WARNING_PATH = "./resources/image/MapImages/icon_indicator_o_01.png";

	@Init
	public void init() {
		hqe = new PersonInfosDaoHibernateImpl();
		queryStates();

		DateKeyValueSelectBox item1 = new DateKeyValueSelectBox(SelectBoxDao.THREE_MIN, "3分鐘");
		DateKeyValueSelectBox item2 = new DateKeyValueSelectBox(SelectBoxDao.FIVE_MIN, "5分鐘");
		DateKeyValueSelectBox item3 = new DateKeyValueSelectBox(SelectBoxDao.ONE_HOUR, "1小時");
		DateKeyValueSelectBox item4 = new DateKeyValueSelectBox(SelectBoxDao.THREE_HOUR, "3小時");
		DateKeyValueSelectBox item5 = new DateKeyValueSelectBox(SelectBoxDao.HALF_DAY, "12小時 ");
		DateKeyValueSelectBox item6 = new DateKeyValueSelectBox(SelectBoxDao.ONE_DAY, "24小時 ");
		dateList.add(item1);
		dateList.add(item2);
		dateList.add(item3);
		dateList.add(item4);
		dateList.add(item5);
		dateList.add(item6);
		selectedDate = item1;
	}

	@NotifyChange({ "personState" })
	@Command
	public void dateSeleted() {
		queryStates();
		personState.setHistoryDate(selectedDate.getValue());
	}

	private void queryStates() {

		System.out.println("queryStates");
		Patient patient = hqe.getPatientById(patientId);
		personState = new PersonState();

		personState.setId(patient.getPatientId());
		personState.setName(patient.getPatientInfos().stream().findFirst().get().getName());
		personState.setBedRoom(patient.getRoom().getRoomNum());
		personState.setHeartBeat(patient.getRtHeartRhythmRecords().stream().findFirst().get().getHeartRateData());
		personState.setOximeter(patient.getRtOximeterRecords().stream().findFirst().get().getOximeterData());
		personState.setBreathRate(patient.getRtHeartRhythmRecords().stream().findFirst().get().getBreathData());
		personState.setBodyTemperature(patient.getRtTempPadRecords().stream().findFirst().get().getBodyTempData());
		personState.setTotalNewsScore(patient.getTotalNewsScore());
	}

	public PersonState getPersonState() {
		return personState;
	}

	public DateKeyValueSelectBox getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(DateKeyValueSelectBox selectedDate) {
		this.selectedDate = selectedDate;
	}

	public List<DateKeyValueSelectBox> getDateList() {
		return dateList;
	}

	public void seDateList(List<DateKeyValueSelectBox> selectedDate) {
		this.dateList = selectedDate;
	}
}
