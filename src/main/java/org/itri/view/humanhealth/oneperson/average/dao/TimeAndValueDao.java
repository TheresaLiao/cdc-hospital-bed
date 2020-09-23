package org.itri.view.humanhealth.oneperson.average.dao;

import java.util.Date;

public class TimeAndValueDao {

	Date timeKey;
	Double value;

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
