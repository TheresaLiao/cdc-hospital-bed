package org.itri.view.humanhealth.oneperson.detail;

import java.util.List;
import org.itri.view.humanhealth.detail.dao.OximeterRecordViewDaoHibernateImpl;
import org.itri.view.humanhealth.hibernate.RtOximeterRecord;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class OximeterCurrentView extends SelectorComposer<Window> {

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
	private Label oximeterLabel;

	@Wire("window > bs-row > #devStatHbox > #batteryLabel")
	private Label batteryLabel;

	@Wire("window > bs-row > #devStatHbox > vbox > #connectImg")
	private Image connectImg;

	private String GRAY_HASH = "#2F2F2F";
	private String BLACK_HASH = "#000000";
	private String BLUE_HASH = "#73E9FF";

	private String heightStr = "100";
	private String lowStr = "90";

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
		String dataStr = getOximeterValueById(getPatientId());
		oximeterLabel.setValue(dataStr);

		hightLightLabel(dataStr);
	}

	@Listen("onTimer = #timer")
	public void updateData() {

		// get PatientId & find data by PatientId
		setPatientId(textboxId.getValue());
		String dataStr = getOximeterValueById(getPatientId());
		oximeterLabel.setValue(dataStr);

		hightLightLabel(dataStr);
	}

	private void hightLightLabel(String dataStr) {

		double data = Double.valueOf(dataStr);
		Double heightData = Double.valueOf(heightStr);
		Double lowData = Double.valueOf(lowStr);

		if (Double.compare(data, heightData) > 0 || Double.compare(data, lowData) < 0) {

			heartBeatVbox.setStyle("background-color: " + BLUE_HASH);
			curHbox.setStyle("background-color: " + BLUE_HASH + "; " + "text-align: center" + ";");

			hrLabel.setStyle("color: " + BLACK_HASH);
			heightLabel.setStyle("color: " + BLACK_HASH);
			lowLabel.setStyle("color: " + BLACK_HASH);
			oximeterLabel.setStyle("color: " + BLACK_HASH);
		} else {
			heartBeatVbox.setStyle("background-color: " + GRAY_HASH);
			curHbox.setStyle("background-color: " + GRAY_HASH + "; " + "text-align: center" + ";");

			hrLabel.setStyle("color: " + BLUE_HASH);
			heightLabel.setStyle("color: " + BLUE_HASH);
			lowLabel.setStyle("color: " + BLUE_HASH);
			oximeterLabel.setStyle("color: " + BLUE_HASH);

		}
	}

	private String getOximeterValueById(long patientId) {

		OximeterRecordViewDaoHibernateImpl hqe = new OximeterRecordViewDaoHibernateImpl();
		List<RtOximeterRecord> rtOximeterRecordList = hqe.getRtOximeterRecordList(patientId);
		for (RtOximeterRecord item : rtOximeterRecordList) {
			connectImg.setSrc(getConnectStatusIcon(item.getSensor().getSensorDeviceStatus()));
			batteryLabel.setValue(item.getBatteryLevel() + "%");
			return item.getOximeterData();
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
