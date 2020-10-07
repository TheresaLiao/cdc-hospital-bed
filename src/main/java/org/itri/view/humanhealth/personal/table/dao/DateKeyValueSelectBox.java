package org.itri.view.humanhealth.personal.table.dao;

public class DateKeyValueSelectBox {
	private long value;
	private String text;

	public DateKeyValueSelectBox(long value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public long getValue() {
		return this.value;
	}
}
