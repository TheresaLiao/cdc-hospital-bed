package org.itri.view.humanhealth.detail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.itri.view.humanhealth.detail.dao.RtOximeterViewDaoHibernateImpl;
import org.itri.view.humanhealth.hibernate.OximeterRecord;
import org.itri.view.humanhealth.hibernate.RtOximeterRecord;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Options;
import org.zkoss.chart.PlotLine;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class OximeterView extends SelectorComposer<Component> {

	private long patientId = 0;

	@Wire
	private Charts chart;

	@Wire("#textboxId")
	private Textbox textboxId;

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
		chart.getYAxis().setTitle("mmHg");
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
		series.setName("Oximeter");

		setPatientId(textboxId.getValue());

		// init point
		List<Point> histData = getOximeterRecordList(getPatientId());
		for (Point p : histData) {
			series.addPoint(p);
		}
		if (histData.size() == 0) {
			System.out.println("no history data in oximeter");
			for (int i = -19; i <= 0; i++) {

				Point nowPoint = getRtOximeterRecordList(getPatientId());
				nowPoint.setX(new Date().getTime() + i * 1000);
				series.addPoint(nowPoint);
			}
		}
	}

	@Listen("onTimer = #timer")
	public void updateData() {
		setPatientId(textboxId.getValue());
		chart.getSeries().addPoint(getRtOximeterRecordList(getPatientId()), true, true, true);
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientIdStr) {

		patientId = Long.parseLong(patientIdStr);
		this.patientId = patientId;
	}

	// Get history data
	private List<Point> getOximeterRecordList(long patientId) {
		RtOximeterViewDaoHibernateImpl hqe = new RtOximeterViewDaoHibernateImpl();
		List<OximeterRecord> oximeterRecordList = hqe.getOximeterRecordList(patientId);

		int i = oximeterRecordList.size() * (-1);
		List<Point> resp = new ArrayList<Point>();
		for (OximeterRecord tt : oximeterRecordList) {
			i++;
			String data = tt.getOximeterData();
			Date time = tt.getTimeCreated();
			resp.add(new Point(time.getTime() + i * 1000, Double.valueOf(data)));
		}
		return resp;
	}

	// Get real time data
	private Point getRtOximeterRecordList(long patientId) {
		RtOximeterViewDaoHibernateImpl hqe = new RtOximeterViewDaoHibernateImpl();
		List<RtOximeterRecord> oximeterRecordList = hqe.getRtOximeterRecordList(patientId);
		for (RtOximeterRecord tt : oximeterRecordList) {
			String data = tt.getOximeterData();
			Date time = tt.getLastUpdated();
			return new Point(time.getTime(), Double.valueOf(data));

		}
		return new Point(new Date().getTime(), 0);
	}
}