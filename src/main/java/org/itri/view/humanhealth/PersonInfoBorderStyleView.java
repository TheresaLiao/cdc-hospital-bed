package org.itri.view.humanhealth;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;

public class PersonInfoBorderStyleView extends SelectorComposer<Div> {

	static int earlyWarningScore = 7;

	@Wire("div")
	private Div div;

	@Wire("div > textbox")
	private Textbox totalNewsScoreTextbox;

	@Override
	public void doAfterCompose(Div comp) throws Exception {
		super.doAfterCompose(comp);

		int totalNewsScore = Integer.valueOf(totalNewsScoreTextbox.getValue());

		if (totalNewsScore > earlyWarningScore) {
			comp.setStyle(" border-top-color: #ff0000; " + "border-right-color: #ff0000;"
					+ "border-bottom-color: #ff0000; " + "border-left-color: #ff0000; " + "border-width: 8px;");
		} else {
			comp.setStyle(" border-top-color: #d9d9d9; " + "border-right-color: #d9d9d9;"
					+ "border-bottom-color: #d9d9d9; " + "border-left-color: #d9d9d9; " + "border-width: 1px;");
		}

	}

}
