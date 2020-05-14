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

public class TemperatureCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > div")
	private Div tempDiv;

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
	private Label temperatureLabel;

	private static String DEGREE_CELSIUS_STR = "¢XC";
	private String GRAY_HASH = "#2f2f2f";
	private String SUCCESS_HASH = "#15CAB4";
	private String WHITE_HASH = "#ffffff";

	private long patientId = 0;

	@Override
	public void doAfterCompose(Window comp) throws Exception {

		// Component Setting
		super.doAfterCompose(comp);
		setPatientId(textboxId.getValue());
		temperatureLabel.setValue(getTemperatureValueById(getPatientId()) + DEGREE_CELSIUS_STR);

		tempDiv.setStyle("background-color: " + GRAY_HASH);
		vlayout.setStyle("background-color: " + GRAY_HASH);

		hrLabel.setStyle("color: " + SUCCESS_HASH);
		heightLabel.setStyle("color: " + SUCCESS_HASH);
		lowLabel.setStyle("color: " + SUCCESS_HASH);
		temperatureLabel.setStyle("color: " + SUCCESS_HASH);
	}

	@Listen("onTimer = #timer")
	public void updateData() {

		setPatientId(textboxId.getValue());
		String data = getTemperatureValueById(getPatientId());
		temperatureLabel.setValue(data + DEGREE_CELSIUS_STR);
		
		tempDiv.setStyle("background-color: " + GRAY_HASH);
		vlayout.setStyle("background-color: " + GRAY_HASH);

		hrLabel.setStyle("color: " + SUCCESS_HASH);
		heightLabel.setStyle("color: " + SUCCESS_HASH);
		lowLabel.setStyle("color: " + SUCCESS_HASH);
		temperatureLabel.setStyle("color: " + SUCCESS_HASH);
		
		
		if (Float.valueOf(data) > 39100 && Float.valueOf(data) < 36) {
		
			tempDiv.setStyle("background-color: " + SUCCESS_HASH);
			vlayout.setStyle("background-color: " + SUCCESS_HASH);

			hrLabel.setStyle("color: " + WHITE_HASH);
			heightLabel.setStyle("color: " + WHITE_HASH);
			lowLabel.setStyle("color: " + WHITE_HASH);
			temperatureLabel.setStyle("color: " + WHITE_HASH);
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

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientIdStr) {
		patientId = Long.parseLong(patientIdStr);
		this.patientId = patientId;
	}

}
