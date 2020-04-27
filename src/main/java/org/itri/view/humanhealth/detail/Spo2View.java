package org.itri.view.humanhealth.detail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.itri.view.humanhealth.detail.dao.RtOximeterViewDaoHibernateImpl;
import org.itri.view.humanhealth.detail.dao.TemperatureViewDaoHibernateImpl;
import org.itri.view.humanhealth.hibernate.OximeterRecord;
import org.itri.view.humanhealth.hibernate.RtOximeterRecord;
import org.itri.view.humanhealth.hibernate.RtTempPadRecord;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Options;
import org.zkoss.chart.PlotLine;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class Spo2View extends SelectorComposer<Window> {
	@Wire
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

		chart.getYAxis().setTitle("Value");
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
		series.setName("Oximeter data");

		for (int i = -19; i <= 0; i++) {
			series.addPoint(getOximeterRecordList());
		}
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		chart.getSeries().addPoint(getOximeterRecordList(), true, true, true);
	}

	private Point getOximeterRecordList() {
		RtOximeterViewDaoHibernateImpl hqe = new RtOximeterViewDaoHibernateImpl();
		List<RtOximeterRecord> oximeterRecordList = hqe.getRtOximeterRecordList();
		for (RtOximeterRecord tt : oximeterRecordList) {
			String data = tt.getOximeterData();
			// Date time = tt.getTimeCreated()
			long patientId = tt.getPatient().getPatientId();
			if (patientId == 1) {
				return new Point(new Date().getTime(), Double.valueOf(data));
			}
		}
		return new Point(new Date().getTime(), 0);
	}
}