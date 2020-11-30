package org.itri.view.humanhealth.personal.table;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.itri.view.humanhealth.hibernate.PatientInfo;
import org.itri.view.humanhealth.personal.table.Imp.PersonInfoTableDaoHibernateImpl;
import org.itri.view.humanhealth.personal.table.dao.DateKeyValueSelectBox;
import org.itri.view.humanhealth.personal.table.dao.PersonInfoTableDao;
import org.itri.view.login.AuthenticationService;
import org.itri.view.login.AuthenticationServiceImpl;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class PersonInfoTable {

	private PersonInfoTableDaoHibernateImpl hqe;
	AuthenticationService authService = new AuthenticationServiceImpl();

	private DateKeyValueSelectBox selectPatient;
	private List<DateKeyValueSelectBox> patientList = new ArrayList<DateKeyValueSelectBox>();

	private Timestamp selectStrDate;
	private Timestamp selectEndDate;
	private Timestamp selectStrTime;
	private Timestamp selectEndTime;

	static final String MONTHLY = "MONTHLY";
	static final String DAYLY = "DAYLY";
	static final String HOURLY = "HOURLY";
	static final String MINUTELY = "MINUTELY";

	private String selectedOption = null;

	private List<PersonInfoTableDao> personTableList = new ArrayList<PersonInfoTableDao>();

	@Init
	public void init() {
		hqe = new PersonInfoTableDaoHibernateImpl();
		getSelectBoxList();
	}

	@NotifyChange({ "personTableList" })
	@Command
	public void buttonClick() {
		if (selectPatient == null || selectStrDate == null || selectEndDate == null || selectedOption == null) {
			Messagebox.show("所有欄位需選擇", "Warning", 1, "ERROR");
		} else {

			queryStates();
		}
	}

	// Get Patient List
	private void getSelectBoxList() {
		List<PatientInfo> dataList = hqe.getPatientList();
		for (PatientInfo patient : dataList) {
			DateKeyValueSelectBox item = new DateKeyValueSelectBox(patient.getPatient().getPatientId(),
					patient.getName());
			patientList.add(item);
		}
	}

	// Get query data list
	private void queryStates() {
		personTableList = new ArrayList<PersonInfoTableDao>();

		Date strTime = setDateTime(selectStrDate, selectStrTime);
		Date endTime = setDateTime(selectEndDate, selectEndTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("selectStrTime :" + sdf.format(strTime));
		System.out.println("selectEndTime :" + sdf.format(endTime));

		List<PersonInfoTableDao> dataList = hqe.getPatientByIdAndTimeCreated(selectPatient.getValue(), strTime,
				endTime);

		Map<Long, Double> hrGroup = new HashMap<Long, Double>();
		Map<Long, Double> oxGroup = new HashMap<Long, Double>();
		Map<Long, Double> brGroup = new HashMap<Long, Double>();
		Map<Long, Double> teGroup = new HashMap<Long, Double>();
		Map<Long, Double> ewsGroup = new HashMap<Long, Double>();

		// Split data by columns
		for (PersonInfoTableDao item : dataList) {
			hrGroup.put(item.getTimeCreated().getTime(), item.getHeartRateData());
			oxGroup.put(item.getTimeCreated().getTime(), item.getOximeterData());
			brGroup.put(item.getTimeCreated().getTime(), item.getBreathData());
			teGroup.put(item.getTimeCreated().getTime(), item.getBodyTempData());
			ewsGroup.put(item.getTimeCreated().getTime(), item.getNewsScore());
		}

		// Group and count avg. data
		System.out.println("selectedOption: " + selectedOption);
		hrGroup = groupAndAvg(hrGroup, selectedOption);
		oxGroup = groupAndAvg(oxGroup, selectedOption);
		brGroup = groupAndAvg(brGroup, selectedOption);
		teGroup = groupAndAvg(teGroup, selectedOption);
		ewsGroup = groupAndAvg(ewsGroup, selectedOption);

		// Merge data by time
		List<Long> keySetList = new ArrayList<>(hrGroup.keySet());
		Collections.sort(keySetList, Collections.reverseOrder());
		System.out.println(keySetList.size());

		for (Long key : keySetList) {
			PersonInfoTableDao item = new PersonInfoTableDao();
			item.setTimeCreated(new Date(key));
			item.setHeartRateData(hrGroup.get(key));
			item.setOximeterData(oxGroup.get(key));
			item.setBreathData(brGroup.get(key));
			item.setBodyTempData(teGroup.get(key));
			item.setNewsScore(ewsGroup.get(key));
			personTableList.add(item);
		}
		System.out.println(personTableList.size());

		if (personTableList.size() == 0) {
			Messagebox.show("資料為空", "Warning", 1, "ERROR");
		}

	}

	private Date setDateTime(Timestamp date, Timestamp time) {

		Calendar cd = Calendar.getInstance();
		Calendar ct = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		cd.setTimeInMillis(date.getTime());
		ct.setTimeInMillis(time.getTime());

		c.set(Calendar.YEAR, cd.get(Calendar.YEAR));
		c.set(Calendar.MONTH, cd.get(Calendar.MONTH));
		c.set(Calendar.DATE, cd.get(Calendar.DATE));

		c.set(Calendar.HOUR_OF_DAY, ct.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, ct.get(Calendar.MINUTE));
		c.set(Calendar.SECOND, ct.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, ct.get(Calendar.MILLISECOND));

		return c.getTime();
	}

	private Map<Long, Double> groupAndAvg(Map<Long, Double> rawData, String type) {
		// group by
		Map<Long, List> groupby = new HashMap<Long, List>();
		for (Long timeCreated : rawData.keySet()) {
			Long key = switchAvgPeriod(timeCreated, type);
			Double value = rawData.get(timeCreated);

			if (groupby.get(key) != null) {
				groupby.get(key).add(value);
			} else {
				List<Double> dataList = new ArrayList();
				dataList.add(value);
				groupby.put(key, dataList);
			}
		}

		// AVG value group by date
		Map<Long, Double> resp = new HashMap<Long, Double>();
		for (Long key : groupby.keySet()) {
			List<Double> dataList = groupby.get(key);
			Double sum = new Double(0);
			for (Double data : dataList) {
				sum = Double.sum(sum, data);
			}
			resp.put(key, (sum / dataList.size()));
		}
		return resp;
	}

	static Long switchAvgPeriod(Long timeCreated, String type) {
		switch (type) {
		case MINUTELY:
			return formateByMinute(timeCreated);
		case HOURLY:
			return formateByHour(timeCreated);
		case DAYLY:
			return formateByDay(timeCreated);
		case MONTHLY:
			return formateByMonthly(timeCreated);
		default:
			return timeCreated;
		}
	}

	static Long formateByMinute(Long timeCreated) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timeCreated);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime().getTime();
	}

	static Long formateByHour(Long timeCreated) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timeCreated);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime().getTime();
	}

	static Long formateByDay(Long timeCreated) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timeCreated);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime().getTime();
	}

	static Long formateByMonthly(Long timeCreated) {
//		Date orgDate = new Date(timeCreated);
//		orgDate.setSeconds(0);
//		orgDate.setMinutes(0);
//		orgDate.setHours(0);
//		return orgDate.getTime();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timeCreated);
		c.set(Calendar.DATE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime().getTime();
	}

	public List<PersonInfoTableDao> getPersonTableList() {
		return personTableList;
	}

	public List<DateKeyValueSelectBox> getPatientList() {
		return patientList;
	}

	public DateKeyValueSelectBox getSelectPatient() {
		return selectPatient;
	}

	public void setSelectPatient(DateKeyValueSelectBox selectPatient) {
		this.selectPatient = selectPatient;
	}

	public Timestamp getSelectStrDate() {
		return selectStrDate;
	}

	public void setSelectStrDate(Timestamp selectStrDate) {
		this.selectStrDate = selectStrDate;
	}

	public Timestamp getSelectEndDate() {
		return selectEndDate;
	}

	public void setSelectEndDate(Timestamp selectEndDate) {
		this.selectEndDate = selectEndDate;
	}

	public Timestamp getSelectStrTime() {
		return selectStrTime;
	}

	public void setSelectStrTime(Timestamp selectStrTime) {
		this.selectStrTime = selectStrTime;
	}

	public Timestamp getSelectEndTime() {
		return selectEndTime;
	}

	public void setSelectEndTime(Timestamp selectEndTime) {
		this.selectEndTime = selectEndTime;
	}

	public String getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}

}
