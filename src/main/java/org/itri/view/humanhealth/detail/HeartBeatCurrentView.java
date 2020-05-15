package org.itri.view.humanhealth.detail;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;
import org.itri.view.humanhealth.hibernate.Patient;

public class HeartBeatCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > div")
	private Div heartBeatDiv;

	@Wire("window > bs-row > div > #hrLabel")
	private Label hrLabel;

	@Wire("window > bs-row > div > #heightLabel")
	private Label heightLabel;

	@Wire("window > bs-row > div > #lowLabel")
	private Label lowLabel;

	@Wire("window > bs-row > vlayout ")
	private Vlayout vlayout;

	@Wire("window > bs-row > vlayout > textbox")
	private Textbox textboxId;

	@Wire("window > bs-row > vlayout > label")
	private Label heartBeatLabel;

	private static String BMP_STR = "bmp";
	private String GRAY_HASH = "#2f2f2f";
	private String DANGER_HASH = "#ff4051";
	private String WHITE_HASH = "#ffffff";

	private long patientId = 0;

	@Override
	public void doAfterCompose(Window comp) throws Exception {

		// Component Setting
		super.doAfterCompose(comp);
		setPatientId(textboxId.getValue());
		heartBeatLabel.setValue(getHeartBeatValueById(getPatientId()) + BMP_STR);

		heartBeatDiv.setStyle("background-color: " + GRAY_HASH);
		vlayout.setStyle("background-color: " + GRAY_HASH);

		hrLabel.setStyle("color: " + DANGER_HASH);
		heightLabel.setStyle("color: " + DANGER_HASH);
		lowLabel.setStyle("color: " + DANGER_HASH);
		heartBeatLabel.setStyle("color: " + DANGER_HASH);
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		setPatientId(textboxId.getValue());
		String dataStr = getHeartBeatValueById(getPatientId());
		heartBeatLabel.setValue(dataStr + BMP_STR);

		heartBeatDiv.setStyle("background-color: " + GRAY_HASH);
		vlayout.setStyle("background-color: " + GRAY_HASH);

		hrLabel.setStyle("color: " + DANGER_HASH);
		heightLabel.setStyle("color: " + DANGER_HASH);
		lowLabel.setStyle("color: " + DANGER_HASH);
		heartBeatLabel.setStyle("color: " + DANGER_HASH);

		double data = Double.valueOf(dataStr);
		Double heightData = Double.valueOf("100");
		Double lowData = Double.valueOf("55");

		if (Double.compare(data, heightData) > 0 || Double.compare(data, lowData) < 0) {

			heartBeatDiv.setStyle("background-color: " + DANGER_HASH);
			vlayout.setStyle("background-color: " + DANGER_HASH);

			hrLabel.setStyle("color: " + WHITE_HASH);
			heightLabel.setStyle("color: " + WHITE_HASH);
			lowLabel.setStyle("color: " + WHITE_HASH);
			heartBeatLabel.setStyle("color: " + WHITE_HASH);
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
