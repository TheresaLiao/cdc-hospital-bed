package org.itri.view.humanhealth.detail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.itri.view.humanhealth.detail.dao.BreathRateViewDaoHibernateImpl;
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
	
	Calendar cal = Calendar.getInstance();

	public void doAfterCompose(Window comp) throws Exception {
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

		for (int i = -19; i <= 0; i++) {
			series.addPoint(getRtHeartRhythmRecordListList());
		}
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		chart.getSeries().addPoint(getRtHeartRhythmRecordListList(), true, true, true);
	}

	private Point getRtHeartRhythmRecordListList() {
		BreathRateViewDaoHibernateImpl hqe = new BreathRateViewDaoHibernateImpl();
		List<RtHeartRhythmRecord> rtHeartRhythmRecordList = hqe.getRtHeartRhythmRecordList();
		for (RtHeartRhythmRecord tt : rtHeartRhythmRecordList) {
			String data = tt.getHeartRateData();
			Date time = tt.getLastUpdated();
			long patientId = tt.getPatient().getPatientId();
			if (patientId == 1) {
				return new Point(new Date().getTime(), Double.valueOf(data));
			}
		}
		return new Point(new Date().getTime(), 0);
	}
}