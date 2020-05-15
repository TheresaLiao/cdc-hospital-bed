package org.itri.view.humanhealth.detail;

import org.itri.view.humanhealth.dao.PersonInfosDaoHibernateImpl;
import org.itri.view.humanhealth.hibernate.Patient;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

public class BreathRateCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > div")
	private Div breathRateDiv;

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
	private Label breathRateLabel;

	private static String TIMES_STR = "¦¸";

	private long patientId = 0;
	private String BLACK_HASH = "#000000";
	private String GRAY_HASH = "#2f2f2f";
	private String WHITE_HASH = "#ffffff";

	@Override
	public void doAfterCompose(Window comp) throws Exception {

		// Component Setting
		super.doAfterCompose(comp);
		setPatientId(textboxId.getValue());
		breathRateLabel.setValue(getBreathRateValueById(getPatientId()) + TIMES_STR);

		breathRateDiv.setStyle("background-color: " + GRAY_HASH);
		vlayout.setStyle("background-color: " + GRAY_HASH);

		hrLabel.setStyle("color: " + WHITE_HASH);
		heightLabel.setStyle("color: " + WHITE_HASH);
		lowLabel.setStyle("color: " + WHITE_HASH);
		breathRateLabel.setStyle("color: " + WHITE_HASH);
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		setPatientId(textboxId.getValue());
		String dataStr = getBreathRateValueById(getPatientId());
		breathRateLabel.setValue(dataStr + TIMES_STR);
		
		breathRateDiv.setStyle("background-color: " + GRAY_HASH);
		vlayout.setStyle("background-color: " + GRAY_HASH);

		hrLabel.setStyle("color: " + WHITE_HASH);
		heightLabel.setStyle("color: " + WHITE_HASH);
		lowLabel.setStyle("color: " + WHITE_HASH);
		breathRateLabel.setStyle("color: " + WHITE_HASH);
		
		double data = Double.valueOf(dataStr);
		Double heightData = Double.valueOf("20");
		Double lowData = Double.valueOf("10");
		
		if (Double.compare(data, heightData) > 0 || Double.compare(data, lowData) < 0) {
		
			breathRateDiv.setStyle("background-color: " + WHITE_HASH);
			vlayout.setStyle("background-color: " + WHITE_HASH);

			hrLabel.setStyle("color: " + BLACK_HASH);
			heightLabel.setStyle("color: " + BLACK_HASH);
			lowLabel.setStyle("color: " + BLACK_HASH);
			breathRateLabel.setStyle("color: " + BLACK_HASH);
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
