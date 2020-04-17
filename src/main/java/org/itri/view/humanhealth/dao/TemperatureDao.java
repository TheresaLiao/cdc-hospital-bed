package org.itri.view.humanhealth.dao;

import org.zkoss.admin.util.Util;
import org.zkoss.chart.model.*;

import java.util.*;

/**
 * Data access object
 */
public class TemperatureDao {
	  private static CategoryModel revenueModel;

	  private static List<Temperature> heatBeatList = new LinkedList<>();
	  public static String[] LINE_NAMES = {"Emergency", "Warning", "current"};

	  static {
	        revenueModel = new DefaultCategoryModel();
	        for (String month : Util.MONTHS) {
	        	revenueModel.setValue(LINE_NAMES[0], month, 93);
	            revenueModel.setValue(LINE_NAMES[1], month, 90);
	            revenueModel.setValue(LINE_NAMES[2], month, Util.random.nextInt(90));
	        }


	        for (String name : LINE_NAMES) {
	        	Temperature product = new Temperature(name);
	            product.setQuantity(Util.nextInt(50,85));
	            product.setPrice(Util.nextInt(50,85));
	   
	            heatBeatList.add(product);
	        }
	    }

	    static public CategoryModel getRevenueModel() {
	        return revenueModel;
	    }
	   
}
