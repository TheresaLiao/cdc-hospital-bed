package org.itri.view.humanhealth.detail;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.hibernate.Patient;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class BreathRateCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > vlayout > textbox")
	private Textbox textboxId;

	@Wire("window > bs-row > vlayout > label")
	private Label breathRateLabel;

	private static String TIMES_STR = "¦¸";

	private long patientId = 0;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		
		// Component Setting
		super.doAfterCompose(comp);
		setPatientId(textboxId.getValue());
		breathRateLabel.setValue(getBreathRateValueById(getPatientId()) + TIMES_STR);
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		setPatientId(textboxId.getValue());
		breathRateLabel.setValue(getBreathRateValueById(getPatientId()) + TIMES_STR);
	}

	private String getBreathRateValueById(long patientId) {

		PersonInfosDaoHibernateImpl hqe = new PersonInfosDaoHibernateImpl();
		Patient rowData = hqe.getPatientById(patientId);
		if (rowData != null) {
			return rowData.getRtHeartRhythmRecords().stream().findFirst().get().getBreathData();
		}
		System.out.println("patientId :" + patientId + " can't find.");
		return "NULL";
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientIdStr) {
		patientId = Long.parseLong(patientIdStr);
		this.patientId = patientId;
	}

}
