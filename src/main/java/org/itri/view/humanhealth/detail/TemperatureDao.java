package org.itri.view.humanhealth.detail;

import org.zkoss.admin.util.Util;
import org.zkoss.chart.model.*;

import java.util.*;

/**
 * Data access object
 */
public class TemperatureDao {
	  private static CategoryModel revenueModel;
	  public static String[] LINE_NAMES = {"Emergency", "Warning", "current"};

	  static {
	        revenueModel = new DefaultCategoryModel();
	        for (String month : Util.MONTHS) {
	        	revenueModel.setValue(LINE_NAMES[0], month, 93);
	            revenueModel.setValue(LINE_NAMES[1], month, 90);
	            revenueModel.setValue(LINE_NAMES[2], month, Util.random.nextInt(90));
	        }
	    }

	    static public CategoryModel getRevenueModel() {
	        return revenueModel;
	    }
	   
}
