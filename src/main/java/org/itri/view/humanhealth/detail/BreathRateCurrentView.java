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

public class BreathRateCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > hlayout > div")
	private Div breathRateDiv;

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
	private Label breathRateLabel;

	private static String TIMES_STR = "¦¸";

	private String BLACK_HASH = "#000000";
	private String GRAY_HASH = "#2f2f2f";
	private String WHITE_HASH = "#ffffff";

	private String heightStr = "20";
	private String lowStr = "10";

	private long patientId = 0;

	@Override
	public void doAfterCompose(Window comp) throws Exception {

		// Component Setting
		super.doAfterCompose(comp);

		// get PatientId & find data by PatientId
		setPatientId(textboxId.getValue());
		String dataStr = getBreathRateValueById(getPatientId());
		breathRateLabel.setValue(dataStr + TIMES_STR);

		hightLightLabel(dataStr);
	}

	@Listen("onTimer = #timer")
	public void updateData() {

		// get PatientId & find data by PatientId
		setPatientId(textboxId.getValue());
		String dataStr = getBreathRateValueById(getPatientId());
		breathRateLabel.setValue(dataStr + TIMES_STR);

		hightLightLabel(dataStr);

	}

	private void hightLightLabel(String dataStr) {
		double data = Double.valueOf(dataStr);
		Double heightData = Double.valueOf(heightStr);
		Double lowData = Double.valueOf(lowStr);

		if (Double.compare(data, heightData) > 0 || Double.compare(data, lowData) < 0) {

			breathRateDiv.setStyle("background-color: " + WHITE_HASH);
			hlayout.setStyle("background-color: " + WHITE_HASH + "; " + "text-align: center" + ";");

			hrLabel.setStyle("color: " + BLACK_HASH);
			heightLabel.setStyle("color: " + BLACK_HASH);
			lowLabel.setStyle("color: " + BLACK_HASH);
			breathRateLabel.setStyle("color: " + BLACK_HASH);
		} else {
			breathRateDiv.setStyle("background-color: " + GRAY_HASH);
			hlayout.setStyle("background-color: " + GRAY_HASH + "; " + "text-align: center" + ";");

			hrLabel.setStyle("color: " + WHITE_HASH);
			heightLabel.setStyle("color: " + WHITE_HASH);
			lowLabel.setStyle("color: " + WHITE_HASH);
			breathRateLabel.setStyle("color: " + WHITE_HASH);
		}
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
