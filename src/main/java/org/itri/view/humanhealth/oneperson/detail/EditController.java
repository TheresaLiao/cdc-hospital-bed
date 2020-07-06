package org.itri.view.humanhealth.oneperson.detail;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

public class EditController {

	private DateKeyValueSelectBox selectedDate;
	private List<DateKeyValueSelectBox> dateList = new ArrayList<DateKeyValueSelectBox>();

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init() {
		// ��椺�e
		DateKeyValueSelectBox item1 = new DateKeyValueSelectBox("threeMin", "3����");
		DateKeyValueSelectBox item2 = new DateKeyValueSelectBox("fiveMin", "5����");
		DateKeyValueSelectBox item3 = new DateKeyValueSelectBox("oneHour", "1�p��");
		DateKeyValueSelectBox item4 = new DateKeyValueSelectBox("threeHour", "3�p��");
		DateKeyValueSelectBox item5 = new DateKeyValueSelectBox("halfDay", "12�p�� ");
		DateKeyValueSelectBox item6 = new DateKeyValueSelectBox("oneDay", "24�p�� ");
		dateList.add(item1);
		dateList.add(item2);
		dateList.add(item3);
		dateList.add(item4);
		dateList.add(item5);
		dateList.add(item6);
		selectedDate = item1;
	}

	@Command
	public void dateSeleted() {
		System.out.println(selectedDate.getValue()); // value
		System.out.println(selectedDate.getText()); // text
		// System.out.println(dateWin.getId());
		// Events.postEvent("refreshPage", dateWin, selectedDate.getValue());
	}

	public DateKeyValueSelectBox getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(DateKeyValueSelectBox selectedDate) {
		this.selectedDate = selectedDate;
	}

	public List<DateKeyValueSelectBox> getDateList() {
		return dateList;
	}

	public void seDateList(List<DateKeyValueSelectBox> selectedDate) {
		this.dateList = selectedDate;
	}
}
