package org.itri.view.humanhealth.detail;

import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;
import org.itri.view.humanhealth.hibernate.Patient;
import org.itri.view.humanhealth.personal.chart.Imp.PersonInfosDaoHibernateImpl;

public class HeartBeatCurrentView extends SelectorComposer<Window> {

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
	private Label heartBeatLabel;

	private String GRAY_HASH = "#2F2F2F";
	private String GREEN_HASH = "#5CE498";
	private String BLACK_HASH = "#000000";
	private String RED_HASH = "#FF0000";

	private String heightStr = "100";
	private String lowStr = "55";

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
		String dataStr = getHeartBeatValueById(getPatientId());
		heartBeatLabel.setValue(dataStr);

		hightLightLabel(dataStr);
	}

	@Listen("onTimer = #timer")
	public void updateData() {

		// get PatientId & find data by PatientId
		setPatientId(textboxId.getValue());
		String dataStr = getHeartBeatValueById(getPatientId());
		heartBeatLabel.setValue(dataStr);

		hightLightLabel(dataStr);
	}

	private void hightLightLabel(String dataStr) {

		double data = Double.valueOf(dataStr);
		Double heightData = Double.valueOf(heightStr);
		Double lowData = Double.valueOf(lowStr);

		if (Double.compare(data, heightData) > 0 || Double.compare(data, lowData) < 0) {

			heartBeatVbox.setStyle("background-color: " + RED_HASH);
			hbox.setStyle("background-color: " + RED_HASH + ";text-align: center");

			hrLabel.setStyle("color: " + BLACK_HASH);
			heightLabel.setStyle("color: " + BLACK_HASH);
			lowLabel.setStyle("color: " + BLACK_HASH);
			heartBeatLabel.setStyle("color: " + BLACK_HASH);
		} else {
			heartBeatVbox.setStyle("background-color: " + GRAY_HASH);
			hbox.setStyle("background-color: " + GRAY_HASH + ";text-align: center");

			hrLabel.setStyle("color: " + RED_HASH);
			heightLabel.setStyle("color: " + RED_HASH);
			lowLabel.setStyle("color: " + RED_HASH);
			heartBeatLabel.setStyle("color: " + RED_HASH);
		}
	}

	private String getHeartBeatValueById(long patientId) {

		PersonInfosDaoHibernateImpl hqe = new PersonInfosDaoHibernateImpl();
		Patient rowData = hqe.getPatientById(patientId);
		if (rowData != null) {
			return rowData.getRtOximeterRecords().stream().findFirst().get().getHeartRateData();
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
