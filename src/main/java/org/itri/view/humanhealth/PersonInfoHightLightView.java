package org.itri.view.humanhealth;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class PersonInfoHightLightView extends SelectorComposer<Window> {

	@Wire("#heartBeatDiv")
	private Div heartBeatDiv;

	@Wire("#heartBeatDiv > label")
	private Label heartBeatLabel;

	@Wire("#heartBeatDiv > textbox")
	private Textbox heartBeatStatusText;

	@Wire("#oximeterDiv")
	private Div oximeterDiv;

	@Wire("#oximeterDiv > label")
	private Label oximeterLabel;

	@Wire("#oximeterDiv > textbox")
	private Textbox oximeterStatusTextbox;

	@Wire("#breathRateDiv")
	private Div breathRateDiv;

	@Wire("#breathRateDiv > label")
	private Label breathRateLabel;

	@Wire("#breathRateDiv > textbox")
	private Textbox breathStatusTextbox;

	@Wire("#tempDiv")
	private Div tempDiv;

	@Wire("#tempDiv > label")
	private Label tempLabel;

	@Wire("#tempDiv > textbox")
	private Textbox bodyTempStatusTextbox;

	private String BLACK_HASH = "#000000";
	private String DANGER_HASH = "#ff4051";
	private String PRIMARY_HASH = "#0093f9";
	private String WHITE_HASH = "#ffffff";
	private String SUCCESS_HASH = "#15CAB4";

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setContentStyle("background: " + BLACK_HASH);

		if (heartBeatStatusText.getValue().equals("W")) {
			heartBeatDiv.setStyle("background-color: " + DANGER_HASH);
			heartBeatLabel.setStyle("color: " + BLACK_HASH);
		} else {
			heartBeatDiv.setStyle("background-color: " + BLACK_HASH);
			heartBeatLabel.setStyle("color: " + DANGER_HASH);
		}

		if (oximeterStatusTextbox.getValue().equals("W")) {
			oximeterDiv.setStyle("background-color: " + PRIMARY_HASH);
			oximeterLabel.setStyle("color: " + BLACK_HASH);
		} else {
			oximeterDiv.setStyle("background-color: " + BLACK_HASH);
			oximeterLabel.setStyle("color: " + PRIMARY_HASH);
		}

		if (breathStatusTextbox.getValue().equals("W")) {
			breathRateDiv.setStyle("background-color: " + WHITE_HASH);
			breathRateLabel.setStyle("color: " + BLACK_HASH);
		} else {
			breathRateDiv.setStyle("background-color: " + BLACK_HASH);
			breathRateLabel.setStyle("color: " + WHITE_HASH);
		}

		if (bodyTempStatusTextbox.getValue().equals("W")) {
			tempDiv.setStyle("background-color: " + SUCCESS_HASH);
			tempLabel.setStyle("color: " + BLACK_HASH);
		} else {
			tempDiv.setStyle("background-color: " + BLACK_HASH);
			tempLabel.setStyle("color: " + SUCCESS_HASH);
		}
	}

	@Listen("onTimer = #timer")
	public void updateData() {

		if (heartBeatStatusText.getValue().equals("W")) {
			heartBeatDiv.setStyle("background-color: " + DANGER_HASH);
			heartBeatLabel.setStyle("color: " + BLACK_HASH);
		} else {
			heartBeatDiv.setStyle("background-color: " + BLACK_HASH);
			heartBeatLabel.setStyle("color: " + DANGER_HASH);
		}

		if (oximeterStatusTextbox.getValue().equals("W")) {
			oximeterDiv.setStyle("background-color: " + PRIMARY_HASH);
			oximeterLabel.setStyle("color: " + BLACK_HASH);
		} else {
			oximeterDiv.setStyle("background-color: " + BLACK_HASH);
			oximeterLabel.setStyle("color: " + PRIMARY_HASH);
		}

		if (breathStatusTextbox.getValue().equals("W")) {
			breathRateDiv.setStyle("background-color: " + WHITE_HASH);
			breathRateLabel.setStyle("color: " + BLACK_HASH);
		} else {
			breathRateDiv.setStyle("background-color: " + BLACK_HASH);
			breathRateLabel.setStyle("color: " + WHITE_HASH);
		}

		if (bodyTempStatusTextbox.getValue().equals("W")) {
			tempDiv.setStyle("background-color: " + SUCCESS_HASH);
			tempLabel.setStyle("color: " + BLACK_HASH);
		} else {
			tempDiv.setStyle("background-color: " + BLACK_HASH);
			tempLabel.setStyle("color: " + SUCCESS_HASH);
		}

	}
}
