package org.itri.view.humanhealth.personal.table.dao;

import java.util.Date;

public class TimeAndValueDao {

	Date timeKey;
	Double value;

	public TimeAndValueDao() {

	}

	public TimeAndValueDao(Date timeKey, Double value) {
		this.timeKey = timeKey;
		this.value = value;
	}

	public Date getTimeKey() {
		return timeKey;
	}

	public void setTimeKey(Date timeKey) {
		this.timeKey = timeKey;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
