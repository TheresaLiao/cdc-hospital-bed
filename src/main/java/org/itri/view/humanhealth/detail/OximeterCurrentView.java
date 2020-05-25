package org.itri.view.humanhealth.detail;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.hibernate.Patient;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

public class OximeterCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > hlayout > div")
	private Div oximeterDiv;

	@Wire("window > bs-row > hlayout > div > #hrLabel")
	private Label hrLabel;

	@Wire("window > bs-row > hlayout > div > #heightLabel")
	private Label heightLabel;

	@Wire("window > bs-row > hlayout > div > #lowLabel")
	private Label lowLabel;

	@Wire("window > bs-row > hlayout ")
	private Hlayout hlayout;

	@Wire("window > bs-row > hlayout > textbox")
	private Textbox textboxId;

	@Wire("window > bs-row > hlayout > label")
	private Label oximeterLabel;

	private static String PERSENT_STR = "%";
	private String GRAY_HASH = "#2f2f2f";
	private String PRIMARY_HASH = "#0093f9";
	private String WHITE_HASH = "#ffffff";

	private String heightStr = "100";
	private String lowStr = "90";

	private long patientId = 0;

	@Override
	public void doAfterCompose(Window comp) throws Exception {

		// Component Setting
		super.doAfterCompose(comp);

		// get PatientId & find data by PatientId
		setPatientId(textboxId.getValue());
		String dataStr = getOximeterValueById(getPatientId());
		oximeterLabel.setValue(dataStr + PERSENT_STR);

		hightLightLabel(dataStr);
	}

	@Listen("onTimer = #timer")
	public void updateData() {

		// get PatientId & find data by PatientId
		setPatientId(textboxId.getValue());
		String dataStr = getOximeterValueById(getPatientId());
		oximeterLabel.setValue(dataStr + PERSENT_STR);

		hightLightLabel(dataStr);
	}

	private void hightLightLabel(String dataStr) {

		double data = Double.valueOf(dataStr);
		Double heightData = Double.valueOf(heightStr);
		Double lowData = Double.valueOf(lowStr);

		if (Double.compare(data, heightData) > 0 || Double.compare(data, lowData) < 0) {

			oximeterDiv.setStyle("background-color: " + PRIMARY_HASH);
			hlayout.setStyle("background-color: " + PRIMARY_HASH + "; " + "text-align: center" + ";");

			hrLabel.setStyle("color: " + WHITE_HASH);
			heightLabel.setStyle("color: " + WHITE_HASH);
			lowLabel.setStyle("color: " + WHITE_HASH);
			oximeterLabel.setStyle("color: " + WHITE_HASH);
		} else {
			oximeterDiv.setStyle("background-color: " + GRAY_HASH);
			hlayout.setStyle("background-color: " + GRAY_HASH + "; " + "text-align: center" + ";");

			hrLabel.setStyle("color: " + PRIMARY_HASH);
			heightLabel.setStyle("color: " + PRIMARY_HASH);
			lowLabel.setStyle("color: " + PRIMARY_HASH);
			oximeterLabel.setStyle("color: " + PRIMARY_HASH);

		}
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
