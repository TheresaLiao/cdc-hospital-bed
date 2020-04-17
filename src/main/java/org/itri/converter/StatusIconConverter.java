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
        put(Status.DegreeRed, "z-icon-group");
        put(Status.DegreeGreen, "z-icon-list");
        put(Status.HeartbeatRed, "z-icon-phone");
        put(Status.HeartbeatGreen, "z-icon-tasks");
       
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
