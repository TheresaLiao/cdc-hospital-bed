package org.itri.view.humanhealth.dao;

import org.zkoss.admin.util.Util;
import org.zkoss.chart.model.*;

import java.util.*;

/**
 * Data access object
 */
public class Spo2Dao {
	  private static CategoryModel revenueModel;

	  private static List<Spo2> heatBeatList = new LinkedList<>();
	  public static String[] LINE_NAMES = {"Emergency", "Warning", "current"};

	  static {
	        revenueModel = new DefaultCategoryModel();
	        for (String month : Util.MONTHS) {
	        	revenueModel.setValue(LINE_NAMES[0], month, 93);
	            revenueModel.setValue(LINE_NAMES[1], month, 90);
	            revenueModel.setValue(LINE_NAMES[2], month, Util.random.nextInt(90));
	        }


	        for (String name : LINE_NAMES) {
	        	Spo2 product = new Spo2(name);
	            product.setQuantity(Util.nextInt(50,85));
	            product.setPrice(Util.nextInt(50,85));
	   
	            heatBeatList.add(product);
	        }
	    }

	    static public CategoryModel getRevenueModel() {
	        return revenueModel;
	    }
	   
}
