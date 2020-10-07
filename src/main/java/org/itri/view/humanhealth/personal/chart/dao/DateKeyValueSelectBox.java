package org.itri.view.humanhealth.personal.chart.dao;

public class DateKeyValueSelectBox {
	private String value;
	private String text;

	public DateKeyValueSelectBox(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public String getValue() {
		return this.value;
	}
}
