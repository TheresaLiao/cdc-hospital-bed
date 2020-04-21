package org.itri.converter;



import org.itri.view.humanhealth.dao.Status;
import org.zkoss.bind.*;
import org.zkoss.zk.ui.Component;

import java.util.*;

/**
 * Convert {@link Status} to a font awesome icon class
 */
public class StatusIconConverter implements Converter<String, Status, Component> {
    static private Map<Status, String> iconMap = new HashMap() {{
        put(Status.DegreeRed, "/cdc-hospital-bed/resources/image/MapImages/icon_waring_temperature_r2.png");
        put(Status.DegreeGreen, "/cdc-hospital-bed/resources/image/MapImages/icon_waring_temperature_g.png");
        put(Status.HeartbeatRed, "/cdc-hospital-bed/resources/image/MapImages/icon_waring_heart_r2.png");
        put(Status.HeartbeatGreen, "/cdc-hospital-bed/resources/image/MapImages/icon_waring_heart_g.png");
       
        
      // resources/image/MapImages/icon_indicator_g_01.png
    }};

    @Override
    public String coerceToUi(Status type, Component component, BindContext bindContext) {
        return iconMap.get(type);
    }

    @Override
    public Status coerceToBean(String s, Component component, BindContext bindContext) {
        //no need in our case
        return null;
    }
}
