package org.itri.view.humanhealth.detail;

import org.itri.view.humanhealth.hibernate.Patient;
import org.itri.view.humanhealth.personal.chart.Imp.PersonInfosDaoHibernateImpl;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

public class BreathRateCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > hbox > vbox")
	private Vbox heartBeatVbox;

	@Wire("window > bs-row > hbox > vbox > #hrLabel")
	private Label hrLabel;

	@Wire("window > bs-row > hbox > vbox > #heightLabel")
	private Label heightLabel;

	@Wire("window > bs-row > hbox > vbox > #lowLabel")
	private Label lowLabel;

	@Wire("window > bs-row > hbox ")
	private Hbox hbox;

	@Wire("window > bs-row > hbox > textbox")
	private Textbox textboxId;

	@Wire("window > bs-row > hbox > label")
	private Label breathRateLabel;

	private String GRAY_HASH = "#2F2F2F";
	private String BLACK_HASH = "#000000";
	private String YELLOW_HASH = "#F8FF70";
	private String WHITE_HASH = "#FFFFFF";

	private String heightStr = "20";
	private String lowStr = "10";

	private long patientId = 0;

	private String BATTERY_WHITE = "resources/image/icon-battery-w.png";
	private String BATTERY_YELLO = "resources/image/icon-battery-y.png";
	private String CONNECT_OK = "resources/image/icon-connect-b-ok.png";
	private String CONNECT_NO = "resources/image/icon-connect-w-no.png";

	@Override
	public void doAfterCompose(Window comp) throws Exception {

		// Component Setting
		super.doAfterCompose(comp);

		// get PatientId & find data by PatientId
		setPatientId(textboxId.getValue());
		String dataStr = getBreathRateValueById(getPatientId());
		breathRateLabel.setValue(dataStr);

		hightLightLabel(dataStr);
	}

	@Listen("onTimer = #timer")
	public void updateData() {

		// get PatientId & find data by PatientId
		setPatientId(textboxId.getValue());
		String dataStr = getBreathRateValueById(getPatientId());
		breathRateLabel.setValue(dataStr);

		hightLightLabel(dataStr);

	}

	private void hightLightLabel(String dataStr) {
		double data = Double.valueOf(dataStr);
		Double heightData = Double.valueOf(heightStr);
		Double lowData = Double.valueOf(lowStr);

		if (Double.compare(data, heightData) > 0 || Double.compare(data, lowData) < 0) {

			heartBeatVbox.setStyle("background-color: " + WHITE_HASH);
			hbox.setStyle("background-color: " + WHITE_HASH + "; " + "text-align: center" + ";");

			hrLabel.setStyle("color: " + BLACK_HASH);
			heightLabel.setStyle("color: " + BLACK_HASH);
			lowLabel.setStyle("color: " + BLACK_HASH);
			breathRateLabel.setStyle("color: " + BLACK_HASH);
		} else {
			heartBeatVbox.setStyle("background-color: " + GRAY_HASH);
			hbox.setStyle("background-color: " + GRAY_HASH + "; " + "text-align: center" + ";");

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
