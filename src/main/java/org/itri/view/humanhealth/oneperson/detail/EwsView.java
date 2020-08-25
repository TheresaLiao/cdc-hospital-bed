package org.itri.view.humanhealth.oneperson.detail;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.dao.PersonState;
import org.itri.view.humanhealth.hibernate.Patient;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Box;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class EwsView extends SelectorComposer<Window> {

	private long patientId = 0;

	@Wire("#textboxId")
	private Textbox textboxId;

	@Wire("window > box > #ewsLabel")
	private Label ewsLabel;

	private PersonInfosDaoHibernateImpl hqe;

	private String RED_HASH = "#FF0000";
	private String WHITE_HASH = "#FFFFFF";
	private String GREEN_HASH = "#5CE498";

	private int ewsSpec = 4;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		// Component Setting
		super.doAfterCompose(comp);
		hqe = new PersonInfosDaoHibernateImpl();

		// set PatientId
		setPatientId(textboxId.getValue());

		// set EWS value
		Patient patient = hqe.getPatientById(getPatientId());
		ewsLabel.setValue("EWS: " + patient.getTotalNewsScore());
		hightLightLabel(patient.getTotalNewsScore());
	}

	@Listen("onTimer = #timer")
	public void updateValue() {
		// set EWS value
		Patient patient = hqe.getPatientById(getPatientId());
		ewsLabel.setValue("EWS: " + patient.getTotalNewsScore());
		hightLightLabel(patient.getTotalNewsScore());
	}

	private void hightLightLabel(int totalNewsScore) {
		if (totalNewsScore >= ewsSpec) {
			ewsLabel.setStyle("color: " + WHITE_HASH + ";background-color: " + RED_HASH + ";text-align: center");
		} else {
			ewsLabel.setStyle("color: " + GREEN_HASH + ";text-align: center");
		}
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientIdStr) {
		patientId = Long.parseLong(patientIdStr);
		this.patientId = patientId;
	}
}
