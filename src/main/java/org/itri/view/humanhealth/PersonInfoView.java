package org.itri.view.humanhealth;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

public class PersonInfoView extends SelectorComposer<Window> {

	@Wire("div#personInfo-7")
	Div div;

	@Listen("onTimer = #timer1234")
	public void updateData() {
	}
}
