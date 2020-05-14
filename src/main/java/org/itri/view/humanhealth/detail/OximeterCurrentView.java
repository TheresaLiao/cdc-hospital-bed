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

public class OximeterCurrentView extends SelectorComposer<Window> {

	@Wire("window > bs-row > div")
	private Div oximeterDiv;

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
	private Label oximeterLabel;

	private static String PERSENT_STR = "%";
	private String GRAY_HASH = "#2f2f2f";
	private String PRIMARY_HASH = "#0093f9";
	private String WHITE_HASH = "#ffffff";

	private long patientId = 0;

	@Override
	public void doAfterCompose(Window comp) throws Exception {

		// Component Setting
		super.doAfterCompose(comp);
		setPatientId(textboxId.getValue());
		oximeterLabel.setValue(getOximeterValueById(getPatientId()) + PERSENT_STR);

		oximeterDiv.setStyle("background-color: " + GRAY_HASH);
		vlayout.setStyle("background-color: " + GRAY_HASH);

		hrLabel.setStyle("color: " + PRIMARY_HASH);
		heightLabel.setStyle("color: " + PRIMARY_HASH);
		lowLabel.setStyle("color: " + PRIMARY_HASH);
		oximeterLabel.setStyle("color: " + PRIMARY_HASH);
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		setPatientId(textboxId.getValue());
		String data = getOximeterValueById(getPatientId());
		oximeterLabel.setValue(data+ PERSENT_STR);

		oximeterDiv.setStyle("background-color: " + GRAY_HASH);
		vlayout.setStyle("background-color: " + GRAY_HASH);

		hrLabel.setStyle("color: " + PRIMARY_HASH);
		heightLabel.setStyle("color: " + PRIMARY_HASH);
		lowLabel.setStyle("color: " + PRIMARY_HASH);
		oximeterLabel.setStyle("color: " + PRIMARY_HASH);
		
		if (Float.valueOf(data) > 100 && Float.valueOf(data) < 90) {
	
			oximeterDiv.setStyle("background-color: " + PRIMARY_HASH);
			vlayout.setStyle("background-color: " + PRIMARY_HASH);

			hrLabel.setStyle("color: " + WHITE_HASH);
			heightLabel.setStyle("color: " + WHITE_HASH);
			lowLabel.setStyle("color: " + WHITE_HASH);
			oximeterLabel.setStyle("color: " + WHITE_HASH);
		}
	}

	private String getOximeterValueById(long patientId) {

		PersonInfosDaoHibernateImpl hqe = new PersonInfosDaoHibernateImpl();
		Patient rowData = hqe.getPatientById(patientId);
		if (rowData != null) {
			return rowData.getRtOximeterRecords().stream().findFirst().get().getOximeterData();
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
