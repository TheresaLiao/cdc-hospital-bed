package org.itri.view.humanhealth.detail;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.zkoss.zul.Label;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.itri.view.humanhealth.hibernate.Patient;

public class HeartBeatCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > vlayout > textbox")
	private Textbox textboxId;

	@Wire("window > bs-row > vlayout > label")
	private Label heartBeatLabel;

	private static String BMP_STR = "bmp";

	private long patientId = 0;

	@Override
	public void doAfterCompose(Window comp) throws Exception {

		// Component Setting
		super.doAfterCompose(comp);
		setPatientId(textboxId.getValue());
		heartBeatLabel.setValue(getHeartBeatValueById(getPatientId()) + BMP_STR);
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		setPatientId(textboxId.getValue());
		heartBeatLabel.setValue(getHeartBeatValueById(getPatientId()) + BMP_STR);
	}

	private String getHeartBeatValueById(long patientId) {

		PersonInfosDaoHibernateImpl hqe = new PersonInfosDaoHibernateImpl();
		Patient rowData = hqe.getPatientById(patientId);
		if (rowData != null) {
			return rowData.getRtHeartRhythmRecords().stream().findFirst().get().getHeartRateData();
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
