package org.itri.view.humanhealth.detail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.itri.view.humanhealth.detail.dao.TemperatureViewDaoHibernateImpl;
import org.itri.view.humanhealth.hibernate.RtTempPadRecord;
import org.itri.view.humanhealth.hibernate.TempPadRecord;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Options;
import org.zkoss.chart.PlotLine;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;

public class TemperatureView extends SelectorComposer<Component> {
	@Wire
	private Charts chart;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// Component Setting
		super.doAfterCompose(comp);
		Options options = new Options();
		options.getGlobal().setUseUTC(false);
		chart.setOptions(options);
		chart.setAnimation(true);
		chart.setBackgroundColor("black");
		chart.getXAxis().setType("datetime");
		chart.getXAxis().setTickPixelInterval(150);
		chart.getYAxis().setTitle("C");
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
		series.setName("Temperature data");

		// init point
		List<Point> histData = getTempPadRecordList();
		for (Point p : histData) {
			series.addPoint(p);
		}
		if (histData.size() == 0) {
			System.out.println("no history data in temp");
			for (int i = -19; i <= 0; i++) {
				Point nowPoint = getRtTempPadRecordList();
				nowPoint.setX(new Date().getTime() + i * 1000);
				series.addPoint(nowPoint);
			}
		}
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		chart.getSeries().addPoint(getRtTempPadRecordList(), true, true, true);
	}

	// Get history data
	private List<Point> getTempPadRecordList() {
		TemperatureViewDaoHibernateImpl hqe = new TemperatureViewDaoHibernateImpl();
		List<TempPadRecord> tempPadRecordList = hqe.getTempPadRecordList();

		int i = tempPadRecordList.size() * (-1);
		List<Point> resp = new ArrayList<Point>();
		for (TempPadRecord tt : tempPadRecordList) {
			String data = tt.getBodyTempData();
			Date time = tt.getTimeCreated();
			resp.add(new Point(time.getTime() + i * 1000, Double.valueOf(data)));
		}
		return resp;
	}

	// Get real time data
	private Point getRtTempPadRecordList() {

		TemperatureViewDaoHibernateImpl hqe = new TemperatureViewDaoHibernateImpl();
		List<RtTempPadRecord> rtTempPadRecordList = hqe.getRtTempPadRecordList();

		for (RtTempPadRecord tt : rtTempPadRecordList) {
			String data = tt.getBodyTempData();
			Date time = tt.getLastUpdated();
			return new Point(new Date().getTime(), Double.valueOf(data));

		}
		return new Point(new Date().getTime(), 0);
	}

}
