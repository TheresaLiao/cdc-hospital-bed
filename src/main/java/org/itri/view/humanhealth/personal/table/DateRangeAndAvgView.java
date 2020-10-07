package org.itri.view.humanhealth.personal.table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Date;

import org.itri.view.humanhealth.hibernate.OximeterRecord;
import org.itri.view.humanhealth.personal.table.dao.TimeAndValueDao;

public class DateRangeAndAvgView {

//	SELECT
//		* 
//	FROM
//		"oximeter_record" 
//	WHERE
//		patient_id = 1 AND time_created >= '2020-09-15' AND time_created < '2020-09-16'
//	ORDER BY
//		time_created ASC

	private Date strDate = new Date();
	private Date endDate = new Date();

	private void dailyAvg() {
	}

	static Date formateByMonth(Date orgDate) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, orgDate.getYear());
		c.set(Calendar.DAY_OF_MONTH, orgDate.getMonth());
		return c.getTime();
	}

	private void monthlyAvg() {
	}

	static Date formateByDay(Date orgDate) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, orgDate.getYear());
		c.set(Calendar.DAY_OF_MONTH, orgDate.getMonth());
		c.set(Calendar.DATE, orgDate.getDay());
		return c.getTime();
	}

	private void hourlyAvg() {
	}

	static Date formateByHour(Date orgDate) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, orgDate.getYear());
		c.set(Calendar.DAY_OF_MONTH, orgDate.getMonth());
		c.set(Calendar.DATE, orgDate.getDay());
		c.set(Calendar.HOUR_OF_DAY, orgDate.getHours());
		return c.getTime();
	}

	

	

}
