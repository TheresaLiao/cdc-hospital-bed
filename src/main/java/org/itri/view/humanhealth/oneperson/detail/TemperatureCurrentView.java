package org.itri.view.humanhealth.oneperson.detail;

import java.text.DecimalFormat;
import java.util.List;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.detail.dao.OximeterRecordViewDaoHibernateImpl;
import org.itri.view.humanhealth.detail.dao.TemperatureViewDaoHibernateImpl;
import org.itri.view.humanhealth.hibernate.Patient;
import org.itri.view.humanhealth.hibernate.RtOximeterRecord;
import org.itri.view.humanhealth.hibernate.RtTempPadRecord;
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

	@Wire("window > bs-row > #curHbox > vbox")
	private Vbox heartBeatVbox;

	@Wire("window > bs-row > #curHbox > vbox > #tempLabel")
	private Label tempLabel;

	@Wire("window > bs-row > #curHbox > vbox > #heightLabel")
	private Label heightLabel;

	@Wire("window > bs-row > #curHbox > vbox > #lowLabel")
	private Label lowLabel;

	@Wire("window > bs-row > #curHbox ")
	private Hbox curHbox;

	@Wire("window > bs-row > #curHbox > textbox")
	private Textbox textboxId;

	@Wire("window > bs-row > #curHbox > label")
	private Label temperatureLabel;

	@Wire("window > bs-row > #devStatHbox > #batteryLabel")
	private Label batteryLabel;

	@Wire("window > bs-row > #devStatHbox > vbox > #connectImg")
	private Image connectImg;

	private String GRAY_HASH = "#2F2F2F";
	private String BLACK_HASH = "#000000";
	private String GREEN_HASH = "#5CE498";

	private String heightStr = "39";
	private String lowStr = "36";

	private long patientId = 0;

	private String CONNECT_OK = "resources/image/icon2-connect-b-ok.png";
	private String CONNECT_NO = "resources/image/icon2-connect-b-no.png";

	private String deviceConnectionErrorNum = "3";

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
			curHbox.setStyle("background-color: " + GREEN_HASH + "; " + "text-align: center" + ";");

			tempLabel.setStyle("color: " + BLACK_HASH);
			heightLabel.setStyle("color: " + BLACK_HASH);
			lowLabel.setStyle("color: " + BLACK_HASH);
			temperatureLabel.setStyle("color: " + BLACK_HASH);
		} else {
			heartBeatVbox.setStyle("background-color: " + GRAY_HASH);
			curHbox.setStyle("background-color: " + GRAY_HASH + "; " + "text-align: center" + ";");

			tempLabel.setStyle("color: " + GREEN_HASH);
			heightLabel.setStyle("color: " + GREEN_HASH);
			lowLabel.setStyle("color: " + GREEN_HASH);
			temperatureLabel.setStyle("color: " + GREEN_HASH);
		}
	}

	private String getTemperatureValueById(long patientId) {

		TemperatureViewDaoHibernateImpl hqe = new TemperatureViewDaoHibernateImpl();
		List<RtTempPadRecord> rtTempPadRecordList = hqe.getRtTempPadRecordList(patientId);
		for (RtTempPadRecord item : rtTempPadRecordList) {
			connectImg.setSrc(getConnectStatusIcon(item.getSensor().getSensorDeviceStatus()));
			batteryLabel.setValue(getBatteryPersent(item.getBatteryLevel()) + "%");
			return item.getBodyTempData();
		}
		System.out.println("patientId :" + patientId + " can't find.");
		return "NULL";
	}

	private String getConnectStatusIcon(String deviceStatus) {

		if (deviceStatus.equals(deviceConnectionErrorNum)) {
			return CONNECT_OK;
		}
		return CONNECT_NO;
	}

	private int getBatteryPersent(String batteryLevel) {

		// volt top : 4.2 , bottom: 3.65
		double top = 4.2;
		double bottom = 3.65;
		double defaultData = 1;

		double gap = top - bottom;
		double value = Float.valueOf(batteryLevel);
		if (value < bottom) {
			return (int) (defaultData);
		}

		double data = (value - bottom) / gap;
		return (int) (data * 100);
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientIdStr) {
		patientId = Long.parseLong(patientIdStr);
		this.patientId = patientId;
	}

}
