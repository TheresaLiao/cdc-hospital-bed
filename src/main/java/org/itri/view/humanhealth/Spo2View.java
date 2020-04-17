package org.itri.view.humanhealth;

import org.itri.view.humanhealth.dao.Spo2Dao;
import org.zkoss.admin.util.*;
import org.zkoss.bind.annotation.*;
import org.zkoss.chart.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;

public class Spo2View {

    @Wire("charts#spo2chart")
    Charts chart;

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);

        chart.setModel(Spo2Dao.getRevenueModel());

        chart.getTitle().setX(-20);
        chart.getSubtitle().setX(-20);
        chart.getYAxis().setTitle("Times");
        
        PlotLine plotLine = new PlotLine();
        plotLine.setValue(0);
        plotLine.setWidth(1);
        plotLine.setColor("#808080");
        chart.getPlotOptions().getAreaSpline().setFillOpacity(0.1);
        chart.getPlotOptions().getAreaSpline().getMarker().setEnabled(false);
        chart.getYAxis().addPlotLine(plotLine);
        //chart.getTooltip().setValueSuffix("$");

        Legend legend = chart.getLegend();
        legend.setLayout("vertical");
        legend.setAlign("right");
        legend.setVerticalAlign("middle");
        legend.setBorderWidth(0);
        Util.setupColor(chart);
    }
}
