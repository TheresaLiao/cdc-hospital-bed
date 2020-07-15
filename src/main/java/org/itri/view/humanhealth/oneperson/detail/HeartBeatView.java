package org.itri.view.humanhealth.oneperson.detail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.itri.view.humanhealth.detail.dao.OximeterRecordViewDaoHibernateImpl;
import org.itri.view.humanhealth.hibernate.OximeterRecord;
import org.itri.view.humanhealth.hibernate.RtOximeterRecord;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Options;
import org.zkoss.chart.PlotLine;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class HeartBeatView extends SelectorComposer<Window> {

	private long patientId = 0;

	private String GREEN_HASH = "#5CE498";
	private String GRAY_HASH = "#808080";
	private String BLACK_HASH = "#000000";
	private String RED_HASH = "#FF0000";

	@Wire
	private Charts chart;

	@Wire("#textboxId")
	private Textbox textboxId;

	@Wire("#textboxHisDate")
	private Textbox textboxHisDate;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		// Component Setting
		super.doAfterCompose(comp);

		// set PatientId
		setPatientId(textboxId.getValue());

		// set charts
		Options options = new Options();
		options.getGlobal().setUseUTC(false);
		chart.setOptions(options);
		chart.setAnimation(true);
		chart.setBackgroundColor("black");
		chart.getXAxis().setType("datetime");
		chart.getXAxis().setTickPixelInterval(150);
		chart.getYAxis().setTitle("");
		PlotLine plotLine = new PlotLine();
		plotLine.setValue(0);
		plotLine.setWidth(1);
		plotLine.setColor(GRAY_HASH);
		chart.getYAxis().addPlotLine(plotLine);
		chart.getTooltip().setHeaderFormat("<b>{series.name}</b><br/>");
		chart.getTooltip().setPointFormat("{point.x:%Y-%m-%d %H:%M:%S}<br>{point.y}");
		chart.getLegend().setEnabled(false);
		chart.getExporting().setEnabled(false);
		Series series = chart.getSeries();

		series.setName("Heart Beat data");
		setPatientId(textboxId.getValue());
		chart.setColors(RED_HASH);

		chart.getXAxis().setLineColor(BLACK_HASH);
		chart.setAlignTicks(false);

		// init point
		List<Point> histData = getHeartRhythmRecordList(getPatientId());
		for (Point p : histData) {
			series.addPoint(p);
		}

		if (histData.size() == 0) {
			System.out.println("no history data in heart beat");
			for (int i = -19; i <= 0; i++) {
				Point nowPoint = getRtHeartRhythmRecordList(getPatientId());
				nowPoint.setX(new Date().getTime() + i * 1000);
				nowPoint.setColor(RED_HASH);
				series.addPoint(nowPoint);
			}
		}
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		setPatientId(textboxId.getValue());
		Point nowPoint = getRtHeartRhythmRecordList(getPatientId());
		chart.getSeries().addPoint(nowPoint, true, true, true);
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientIdStr) {
		patientId = Long.parseLong(patientIdStr);
		this.patientId = patientId;
	}

	// Get history data
	private List<Point> getHeartRhythmRecordList(long patientId) {

		OximeterRecordViewDaoHibernateImpl hqe = new OximeterRecordViewDaoHibernateImpl();
		List<OximeterRecord> oximeterRecordList = hqe.getOximeterRecordByDateList(patientId, getHisDate());

		List<Point> resp = new ArrayList<Point>();
		for (OximeterRecord item : oximeterRecordList) {
			resp.add(new Point(item.getTimeCreated().getTime(), Double.valueOf(item.getHeartRateData())));
		}

		return resp;
	}

	// Get real time data
	private Point getRtHeartRhythmRecordList(long patientId) {

		OximeterRecordViewDaoHibernateImpl hqe = new OximeterRecordViewDaoHibernateImpl();
		List<RtOximeterRecord> rtOximeterRecordList = hqe.getRtOximeterRecordList(patientId);
		for (RtOximeterRecord tt : rtOximeterRecordList) {
			String data = tt.getHeartRateData();
			Date time = tt.getLastUpdated();
			return new Point(time.getTime(), Double.valueOf(data));
		}
		return new Point(new Date().getTime(), 0);
	}

	private Calendar getHisDate() {
		String value = textboxHisDate.getValue();
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		
		if (value.equals(SelectBoxDao.THREE_MIN)) {
			calendar.add(Calendar.MINUTE, -3);
		} else if (value.equals(SelectBoxDao.FIVE_MIN)) {
			calendar.add(Calendar.MINUTE, -5);
		} else if (value.equals(SelectBoxDao.ONE_HOUR)) {
			calendar.add(Calendar.HOUR, -1);
		} else if (value.equals(SelectBoxDao.THREE_HOUR)) {
			calendar.add(Calendar.HOUR, -3);
		} else if (value.equals(SelectBoxDao.HALF_DAY)) {
			calendar.add(Calendar.HOUR, -12);
		} else if (value.equals(SelectBoxDao.ONE_DAY)) {
			calendar.add(Calendar.DATE, -1);
		} else {
			// default
			calendar.add(Calendar.MINUTE, -3);
		}
		return calendar;
	}

}