package org.itri.view.humanhealth.detail;

import org.zkoss.zhtml.H2;
import org.zkoss.zhtml.Label;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class HeartBeatCurrentView extends SelectorComposer<Window> {

	private long patientId = 0;

	@Wire("window > bs-row > div > #h2")
	private H2 h2;

	@Wire("window > bs-row > div > label")
	private Label label;

	@Wire("window > bs-row > div > textbox")
	private Textbox test;

//	@Wire("#textboxId")
//	private Textbox textboxId;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		System.out.println("doAfterCompose");
		// Component Setting
		super.doAfterCompose(comp);
//		System.out.println(test.getValue());
//		System.out.println(label);
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		System.out.println("updateData");
//		System.out.println(test.getValue());
//		System.out.println(label);
	}
}
