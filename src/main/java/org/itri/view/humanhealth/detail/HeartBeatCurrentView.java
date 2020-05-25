package org.itri.view.humanhealth.detail;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;
import org.itri.view.humanhealth.hibernate.Patient;

public class HeartBeatCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > hlayout > div")
	private Div heartBeatDiv;

	@Wire("window > bs-row > hlayout > div > #hrLabel")
	private Label hrLabel;

	@Wire("window > bs-row > hlayout > div > #heightLabel")
	private Label heightLabel;

	@Wire("window > bs-row > hlayout > div > #lowLabel")
	private Label lowLabel;

//	@Wire("window > bs-row > vlayout ")
//	private Vlayout vlayout;
//
//	@Wire("window > bs-row > vlayout > textbox")
//	private Textbox textboxId;
//
//	@Wire("window > bs-row > vlayout > label")
//	private Label heartBeatLabel;
	
	@Wire("window > bs-row > hlayout ")
	private Hlayout hlayout;

	@Wire("window > bs-row > hlayout > textbox")
	private Textbox textboxId;

	@Wire("window > bs-row > hlayout > label")
	private Label heartBeatLabel;
	

	private static String BMP_STR = "bmp";
	private String GRAY_HASH = "#2f2f2f";
	private String DANGER_HASH = "#ff4051";
	private String WHITE_HASH = "#ffffff";

	private String heightStr = "100";
	private String lowStr = "55";

	private long patientId = 0;

	@Override
	public void doAfterCompose(Window comp) throws Exception {

		// Component Setting
		super.doAfterCompose(comp);

		// get PatientId & find data by PatientId
		setPatientId(textboxId.getValue());
		String dataStr = getHeartBeatValueById(getPatientId());
		heartBeatLabel.setValue(dataStr + BMP_STR);

		hightLightLabel(dataStr);
	}

	@Listen("onTimer = #timer")
	public void updateData() {

		// get PatientId & find data by PatientId
		setPatientId(textboxId.getValue());
		String dataStr = getHeartBeatValueById(getPatientId());
		heartBeatLabel.setValue(dataStr + BMP_STR);

		hightLightLabel(dataStr);
	}

	private void hightLightLabel(String dataStr) {

		double data = Double.valueOf(dataStr);
		Double heightData = Double.valueOf(heightStr);
		Double lowData = Double.valueOf(lowStr);

		if (Double.compare(data, heightData) > 0 || Double.compare(data, lowData) < 0) {

			heartBeatDiv.setStyle("background-color: " + DANGER_HASH);
			hlayout.setStyle("background-color: " + DANGER_HASH + ";text-align: center");

			hrLabel.setStyle("color: " + WHITE_HASH);
			heightLabel.setStyle("color: " + WHITE_HASH);
			lowLabel.setStyle("color: " + WHITE_HASH);
			heartBeatLabel.setStyle("color: " + WHITE_HASH);
		} else {
			heartBeatDiv.setStyle("background-color: " + GRAY_HASH);
			hlayout.setStyle("background-color: " + GRAY_HASH + ";text-align: center");

			hrLabel.setStyle("color: " + DANGER_HASH);
			heightLabel.setStyle("color: " + DANGER_HASH);
			lowLabel.setStyle("color: " + DANGER_HASH);
			heartBeatLabel.setStyle("color: " + DANGER_HASH);
		}
	}

	private String getHeartBeatValueById(long patientId) {

		PersonInfosDaoHibernateImpl hqe = new PersonInfosDaoHibernateImpl();
		Patient rowData = hqe.getPatientById(patientId);
		if (rowData != null) {
			return rowData.getRtHeartRhythmRecords().stream().findFirst().get().getHeartRateData();
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
