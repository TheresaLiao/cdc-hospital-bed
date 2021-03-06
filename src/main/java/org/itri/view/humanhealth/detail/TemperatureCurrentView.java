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

public class TemperatureCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > hbox > vbox")
	private Vbox heartBeatVbox;

	@Wire("window > bs-row > hbox > vbox > #tempLabel")
	private Label tempLabel;

	@Wire("window > bs-row > hbox > vbox > #heightLabel")
	private Label heightLabel;

	@Wire("window > bs-row > hbox > vbox > #lowLabel")
	private Label lowLabel;

	@Wire("window > bs-row > hbox ")
	private Hbox hbox;

	@Wire("window > bs-row > hbox > textbox")
	private Textbox textboxId;

	@Wire("window > bs-row > hbox > label")
	private Label temperatureLabel;

	private String GRAY_HASH = "#2F2F2F";
	private String BLACK_HASH = "#000000";
	private String GREEN_HASH = "#5CE498";

	private String heightStr = "39";
	private String lowStr = "36";

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
		String dataStr = getTemperatureValueById(getPatientId());
		temperatureLabel.setValue(dataStr);

		hightLightLabel(dataStr);
	}

	@Listen("onTimer = #timer")
	public void updateData() {

		// get PatientId & find data by PatientId
		setPatientId(textboxId.getValue());
		String dataStr = getTemperatureValueById(getPatientId());
		temperatureLabel.setValue(dataStr);

		hightLightLabel(dataStr);
	}

	private void hightLightLabel(String dataStr) {
		double data = Double.valueOf(dataStr);
		Double heightData = Double.valueOf(heightStr);
		Double lowData = Double.valueOf(lowStr);

		if (Double.compare(data, heightData) > 0 || Double.compare(data, lowData) < 0) {

			heartBeatVbox.setStyle("background-color: " + GREEN_HASH);
			hbox.setStyle("background-color: " + GREEN_HASH + "; " + "text-align: center" + ";");

			tempLabel.setStyle("color: " + BLACK_HASH);
			heightLabel.setStyle("color: " + BLACK_HASH);
			lowLabel.setStyle("color: " + BLACK_HASH);
			temperatureLabel.setStyle("color: " + BLACK_HASH);
		} else {
			heartBeatVbox.setStyle("background-color: " + GRAY_HASH);
			hbox.setStyle("background-color: " + GRAY_HASH + "; " + "text-align: center" + ";");

			tempLabel.setStyle("color: " + GREEN_HASH);
			heightLabel.setStyle("color: " + GREEN_HASH);
			lowLabel.setStyle("color: " + GREEN_HASH);
			temperatureLabel.setStyle("color: " + GREEN_HASH);
		}
	}

	private String getTemperatureValueById(long patientId) {

		PersonInfosDaoHibernateImpl hqe = new PersonInfosDaoHibernateImpl();
		Patient rowData = hqe.getPatientById(patientId);
		if (rowData != null) {
			return rowData.getRtTempPadRecords().stream().findFirst().get().getBodyTempData();
		}
		System.out.println("patientId :" + patientId + " can't find.");
		return "NULL";
	}

	private String getBatteryPersent(String batteryLevel) {

		// volt top : 4.2 , bottom: 3.65
		double top = 4.2;
		double bottom = 3.65;
		double defaultData = 1;

		double gap = top - bottom;
		double value = Float.valueOf(batteryLevel);
		if (value < bottom) {
			return String.valueOf(defaultData);
		}

		double data = (value - bottom) / gap;
		return String.valueOf(data * 100);
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientIdStr) {
		patientId = Long.parseLong(patientIdStr);
		this.patientId = patientId;
	}

}
