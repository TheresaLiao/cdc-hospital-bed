package org.itri.view.humanhealth.dao;

import org.zkoss.admin.util.Util;
import org.zkoss.chart.model.*;

import java.util.*;

/**
 * Data access object
 */
public class BreathRateDao {
	  private static CategoryModel revenueModel;

	  private static List<BreathRate> heatBeatList = new LinkedList<>();
	  public static String[] LINE_NAMES = {"Emergency", "Warning", "Low"};

	  static {
	        revenueModel = new DefaultCategoryModel();
	        for (String month : Util.MONTHS) {
	            revenueModel.setValue(LINE_NAMES[0], month, 45);
	            revenueModel.setValue(LINE_NAMES[2], month, 30);
	            revenueModel.setValue(LINE_NAMES[1], month, Util.random.nextInt(21));
	        }


	        for (String name : LINE_NAMES) {
	        	BreathRate product = new BreathRate(name);
	            product.setQuantity(Util.nextInt(12, 20));
	            product.setPrice(Util.nextInt(10,21) / 10);
	   
	            heatBeatList.add(product);
	        }
	    }

	    static public CategoryModel getRevenueModel() {
	        return revenueModel;
	    }
	   
}
