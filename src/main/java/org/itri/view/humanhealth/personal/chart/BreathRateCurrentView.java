package org.itri.view.humanhealth.personal.chart;

import java.util.List;

import org.itri.view.humanhealth.hibernate.HeartRhythmRecord;
import org.itri.view.humanhealth.hibernate.RtHeartRhythmRecord;
import org.itri.view.humanhealth.personal.chart.Imp.BreathRateViewDaoHibernateImpl;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class BreathRateCurrentView extends SelectorComposer<Window> {

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
	private Label breathRateLabel;

	@Wire("window > bs-row > #devStatHbox > #batteryLabel")
	private Label batteryLabel;

	@Wire("window > bs-row > #devStatHbox > vbox > #connectImg")
	private Image connectImg;

	private String GRAY_HASH = "#2F2F2F";
	private String BLACK_HASH = "#000000";
	private String YELLOW_HASH = "#F8FF70";
	private String WHITE_HASH = "#FFFFFF";

	private Double heightData = new Double(20);
	private Double lowData = new Double(10);

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
	
		if (Double.compare(data, heightData) > 0 || Double.compare(data, lowData) < 0) {

			heartBeatVbox.setStyle("background-color: " + WHITE_HASH);
			curHbox.setStyle("background-color: " + WHITE_HASH + "; " + "text-align: center" + ";");

			hrLabel.setStyle("color: " + BLACK_HASH);
			heightLabel.setStyle("color: " + BLACK_HASH);
			lowLabel.setStyle("color: " + BLACK_HASH);
			breathRateLabel.setStyle("color: " + BLACK_HASH);
		} else {
			heartBeatVbox.setStyle("background-color: " + GRAY_HASH);
			curHbox.setStyle("background-color: " + GRAY_HASH + "; " + "text-align: center" + ";");

			hrLabel.setStyle("color: " + WHITE_HASH);
			heightLabel.setStyle("color: " + WHITE_HASH);
			lowLabel.setStyle("color: " + WHITE_HASH);
			breathRateLabel.setStyle("color: " + WHITE_HASH);
		}
	}

	// Get Breath Rate current data per 1 sec
	private String getBreathRateValueById(long patientId) {

		BreathRateViewDaoHibernateImpl hqe = new BreathRateViewDaoHibernateImpl();
		List<RtHeartRhythmRecord> rtHeartRhythmRecordList = hqe.getRtHeartRhythmRecordList(patientId);
		for (RtHeartRhythmRecord item : rtHeartRhythmRecordList) {
			connectImg.setSrc(getConnectStatusIcon(item.getSensor().getSensorDeviceStatus()));
			batteryLabel.setValue(item.getBatteryLevel() + "%");
			return item.getBreathData();
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
