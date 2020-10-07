package org.itri.view.humanhealth.personal.chart;

import java.util.List;

import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import org.itri.view.humanhealth.hibernate.RtOximeterRecord;
import org.itri.view.humanhealth.personal.chart.Imp.OximeterRecordViewDaoHibernateImpl;

public class HeartBeatCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > #curHbox > vbox")
	private Vbox heartBeatVbox;

	@Wire("window > bs-row > #curHbox > vbox > #hrLabel")
	private Label hrLabel;

	@Wire("window > bs-row > #curHbox > vbox > #heightLabel")
	private Label heightLabel;

	@Wire("window > bs-row > #curHbox > vbox > #lowLabel")
	private Label lowLabel;

	@Wire("window > bs-row > #curHbox ")
	private Hbox curHbox;

	@Wire("window > bs-row > #curHbox > textbox")
	private Textbox textboxId;

	@Wire("window > bs-row > #curHbox > label")
	private Label heartBeatLabel;

	@Wire("window > bs-row > #devStatHbox > #batteryLabel")
	private Label batteryLabel;

	@Wire("window > bs-row > #devStatHbox > vbox > #connectImg")
	private Image connectImg;

	private String GRAY_HASH = "#2F2F2F";
	private String GREEN_HASH = "#5CE498";
	private String BLACK_HASH = "#000000";
	private String RED_HASH = "#FF0000";

	private Double heightData = new Double(100);
	private Double lowData = new Double(55);

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

		// show hight Light
		if (Double.compare(data, heightData) > 0 || Double.compare(data, lowData) < 0) {

			heartBeatVbox.setStyle("background-color: " + RED_HASH);
			curHbox.setStyle("background-color: " + RED_HASH + ";text-align: center");

			hrLabel.setStyle("color: " + BLACK_HASH);
			heightLabel.setStyle("color: " + BLACK_HASH);
			lowLabel.setStyle("color: " + BLACK_HASH);
			heartBeatLabel.setStyle("color: " + BLACK_HASH);
		} else {
			heartBeatVbox.setStyle("background-color: " + GRAY_HASH);
			curHbox.setStyle("background-color: " + GRAY_HASH + ";text-align: center");

			hrLabel.setStyle("color: " + RED_HASH);
			heightLabel.setStyle("color: " + RED_HASH);
			lowLabel.setStyle("color: " + RED_HASH);
			heartBeatLabel.setStyle("color: " + RED_HASH);
		}
	}

	private String getHeartBeatValueById(long patientId) {

		OximeterRecordViewDaoHibernateImpl hqe = new OximeterRecordViewDaoHibernateImpl();
		List<RtOximeterRecord> rtOximeterRecordList = hqe.getRtOximeterRecordList(patientId);
		for (RtOximeterRecord item : rtOximeterRecordList) {
			connectImg.setSrc(getConnectStatusIcon(item.getSensor().getSensorDeviceStatus()));
			batteryLabel.setValue(item.getBatteryLevel() + "%");
			return item.getHeartRateData();
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

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientIdStr) {
		patientId = Long.parseLong(patientIdStr);
		this.patientId = patientId;
	}
}
