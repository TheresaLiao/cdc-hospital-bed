package org.itri.view.humanhealth.detail;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.hibernate.Patient;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class OximeterCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > hlayout > textbox")
	private Textbox textboxId;

	@Wire("window > bs-row > hlayout > label")
	private Label oximeterLabel;

	private static String PERSENT_STR = "%";

	private long patientId = 0;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		
		// Component Setting
		super.doAfterCompose(comp);
		setPatientId(textboxId.getValue());
		oximeterLabel.setValue(getOximeterValueById(getPatientId()) + PERSENT_STR);
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		setPatientId(textboxId.getValue());
		oximeterLabel.setValue(getOximeterValueById(getPatientId()) + PERSENT_STR);
	}

	private String getOximeterValueById(long patientId) {

		PersonInfosDaoHibernateImpl hqe = new PersonInfosDaoHibernateImpl();
		Patient rowData = hqe.getPatientById(patientId);
		if (rowData != null) {
			return rowData.getRtOximeterRecords().stream().findFirst().get().getOximeterData();
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
