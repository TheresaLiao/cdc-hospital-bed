package org.itri.view.humanhealth.detail.control;

import org.zkoss.chart.Charts;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

public class humanChartsEvent extends SelectorComposer {
	
	@Wire
	Window mainWindow; 

	@Wire
	Button humanChartDateBtn;
	
	@Wire("charts#temperatureChart")
	Charts chart;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		
		super.doAfterCompose(comp);
		
		EventListener actionListener = new SerializableEventListener() {
			private static final long serialVersionUID = 1L;

			public void onEvent(Event event) throws Exception {
				System.out.println(event.getName());
				System.out.println("test");
				
//				if(chart.isInvalidated()) {
//					System.out.print(chart.getTitle());
//				}
			}
		};
		
		humanChartDateBtn.addEventListener(Events.ON_CLICK, actionListener);
	}

}
