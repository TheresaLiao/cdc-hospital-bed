package org.itri.view.humanhealth;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class PersonInfoHightLightView extends SelectorComposer<Window> {

	// heartBeat Component
	@Wire("#heartBeatDiv")
	private Div heartBeatDiv;
	@Wire("#heartBeatDiv > label")
	private Label heartBeatLabel;
	@Wire("#heartBeatDiv > textbox")
	private Textbox heartBeatStatusText;

	// oximeter Component
	@Wire("#oximeterDiv")
	private Div oximeterDiv;
	@Wire("#oximeterDiv > label")
	private Label oximeterLabel;
	@Wire("#oximeterDiv > textbox")
	private Textbox oximeterStatusTextbox;

	// breathRate Component
	@Wire("#breathRateDiv")
	private Div breathRateDiv;
	@Wire("#breathRateDiv > label")
	private Label breathRateLabel;
	@Wire("#breathRateDiv > textbox")
	private Textbox breathStatusTextbox;

	// temp Component
	@Wire("#tempDiv")
	private Div tempDiv;
	@Wire("#tempDiv > label")
	private Label tempLabel;
	@Wire("#tempDiv > textbox")
	private Textbox bodyTempStatusTextbox;

	// EWS Component
	@Wire("#ewsDiv")
	private Div ewsDiv;
	@Wire("#ewsDiv > label")
	private Label ewsLabel;
	@Wire("#ewsDiv > textbox")
	private Textbox ewsTextbox;

	private static int earlyWarningScore = 7;

	private String BLACK_HASH = "#000000";
	private String GREEN_HASH = "#5CE498";
	private String BLUE_HASH = "#73E9FF";
	private String YELLOW_HASH = "#F8FF70";
	private String RED_HASH = "#FF0000";
	private String WHITE_HASH = "#FFFFFF";

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setContentStyle("background: " + BLACK_HASH);

		// heartBeat
		if (heartBeatStatusText.getValue().equals("W")) {
			heartBeatDiv.setStyle("background-color: " + RED_HASH);
			heartBeatLabel.setStyle("color: " + BLACK_HASH);
		} else {
			heartBeatDiv.setStyle("background-color: " + BLACK_HASH);
			heartBeatLabel.setStyle("color: " + RED_HASH);
		}

		// oximeter
		if (oximeterStatusTextbox.getValue().equals("W")) {
			oximeterDiv.setStyle("background-color: " + BLUE_HASH);
			oximeterLabel.setStyle("color: " + BLACK_HASH);
		} else {
			oximeterDiv.setStyle("background-color: " + BLACK_HASH);
			oximeterLabel.setStyle("color: " + BLUE_HASH);
		}

		// breath
		if (breathStatusTextbox.getValue().equals("W")) {
			breathRateDiv.setStyle("background-color: " + WHITE_HASH);
			breathRateLabel.setStyle("color: " + BLACK_HASH);
		} else {
			breathRateDiv.setStyle("background-color: " + BLACK_HASH);
			breathRateLabel.setStyle("color: " + WHITE_HASH);
		}

		// bodyTemp
		if (bodyTempStatusTextbox.getValue().equals("W")) {
			tempDiv.setStyle("background-color: " + GREEN_HASH);
			tempLabel.setStyle("color: " + BLACK_HASH);
		} else {
			tempDiv.setStyle("background-color: " + BLACK_HASH);
			tempLabel.setStyle("color: " + GREEN_HASH);
		}

		// EWS
		int totalNewsScore = Integer.valueOf(ewsTextbox.getValue());
		if (totalNewsScore > earlyWarningScore) {
			ewsDiv.setStyle("background-color: " + WHITE_HASH);
			ewsLabel.setStyle("color: " + BLACK_HASH);
		} else {
			ewsDiv.setStyle("background-color: " + BLACK_HASH);
			ewsLabel.setStyle("color: " + WHITE_HASH);
		}
	}

	@Listen("onTimer = #timer")
	public void updateData() {

		if (heartBeatStatusText.getValue().equals("W")) {
			heartBeatDiv.setStyle("background-color: " + RED_HASH);
			heartBeatLabel.setStyle("color: " + BLACK_HASH);
		} else {
			heartBeatDiv.setStyle("background-color: " + BLACK_HASH);
			heartBeatLabel.setStyle("color: " + RED_HASH);
		}

		if (oximeterStatusTextbox.getValue().equals("W")) {
			oximeterDiv.setStyle("background-color: " + BLUE_HASH);
			oximeterLabel.setStyle("color: " + BLACK_HASH);
		} else {
			oximeterDiv.setStyle("background-color: " + BLACK_HASH);
			oximeterLabel.setStyle("color: " + BLUE_HASH);
		}

		if (breathStatusTextbox.getValue().equals("W")) {
			breathRateDiv.setStyle("background-color: " + WHITE_HASH);
			breathRateLabel.setStyle("color: " + BLACK_HASH);
		} else {
			breathRateDiv.setStyle("background-color: " + BLACK_HASH);
			breathRateLabel.setStyle("color: " + WHITE_HASH);
		}

		if (bodyTempStatusTextbox.getValue().equals("W")) {
			tempDiv.setStyle("background-color: " + GREEN_HASH);
			tempLabel.setStyle("color: " + BLACK_HASH);
		} else {
			tempDiv.setStyle("background-color: " + BLACK_HASH);
			tempLabel.setStyle("color: " + GREEN_HASH);
		}

		// EWS
		int totalNewsScore = Integer.valueOf(ewsTextbox.getValue());
		if (totalNewsScore > earlyWarningScore) {
			ewsDiv.setStyle("background-color: " + WHITE_HASH);
			ewsLabel.setStyle("color: " + BLACK_HASH);
		} else {
			ewsDiv.setStyle("background-color: " + BLACK_HASH);
			ewsLabel.setStyle("color: " + WHITE_HASH);
		}
	}
}
