package org.itri.view.humanhealth.detail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.itri.view.humanhealth.detail.dao.BreathRateViewDaoHibernateImpl;
import org.itri.view.humanhealth.hibernate.HeartRhythmRecord;
import org.itri.view.humanhealth.hibernate.RtHeartRhythmRecord;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Options;
import org.zkoss.chart.PlotLine;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class HeartBeatView extends SelectorComposer<Window> {

	@Wire("charts#heartBeatChart")
	Charts chart;

	public void doAfterCompose(Window comp) throws Exception {

		// Component Setting
		super.doAfterCompose(comp);
		Options options = new Options();
		options.getGlobal().setUseUTC(false);
		chart.setOptions(options);
		chart.setAnimation(true);
		chart.setBackgroundColor("black");
		chart.getXAxis().setType("datetime");
		chart.getXAxis().setTickPixelInterval(150);
		chart.getYAxis().setTitle("¦¸");
		PlotLine plotLine = new PlotLine();
		plotLine.setValue(0);
		plotLine.setWidth(1);
		plotLine.setColor("#808080");
		chart.getYAxis().addPlotLine(plotLine);
		chart.getTooltip().setHeaderFormat("<b>{series.name}</b><br/>");
		chart.getTooltip().setPointFormat("{point.x:%Y-%m-%d %H:%M:%S}<br>{point.y}");
		chart.getLegend().setEnabled(false);
		chart.getExporting().setEnabled(false);
		Series series = chart.getSeries();
		series.setName("Heart Beat data");

		// init point
		List<Point> histData = getHeartRhythmRecordList();
		for (Point p : histData) {
			series.addPoint(p);
		}
		if (histData.size() == 0) {
			System.out.println("no history data in heart beat");
			for (int i = -19; i <= 0; i++) {
				
				Point nowPoint = getRtHeartRhythmRecordList();
				nowPoint.setX(new Date().getTime() + i * 1000);
				series.addPoint(nowPoint);
			}
		}
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		chart.getSeries().addPoint(getRtHeartRhythmRecordList(), true, true, true);
	}

	// Get history data
	private List<Point> getHeartRhythmRecordList() {
		BreathRateViewDaoHibernateImpl hqe = new BreathRateViewDaoHibernateImpl();
		List<HeartRhythmRecord> heartRhythmRecordList = hqe.getHeartRhythmRecordList();

		List<Point> resp = new ArrayList<Point>();

		for (HeartRhythmRecord tt : heartRhythmRecordList) {
			String data = tt.getHeartRateData();
			Date time = tt.getTimeCreated();
			resp.add(new Point(time.getTime(), Double.valueOf(data)));
		}
		return resp;
	}

	// Get real time data
	private Point getRtHeartRhythmRecordList() {
		BreathRateViewDaoHibernateImpl hqe = new BreathRateViewDaoHibernateImpl();
		List<RtHeartRhythmRecord> rtHeartRhythmRecordList = hqe.getRtHeartRhythmRecordList();
		for (RtHeartRhythmRecord tt : rtHeartRhythmRecordList) {
			String data = tt.getHeartRateData();
			Date time = tt.getLastUpdated();

			return new Point(new Date().getTime(), Double.valueOf(data));

		}
		return new Point(new Date().getTime(), 0);
	}

}