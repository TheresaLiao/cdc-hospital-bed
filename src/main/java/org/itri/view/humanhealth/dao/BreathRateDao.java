package org.itri.view.humanhealth.dao;

import org.zkoss.admin.util.Util;
import org.zkoss.chart.model.*;

import java.util.*;

/**
 * Data access object
 */
public class BreathRateDao {
	  private static CategoryModel revenueModel;
//	  private static CategoryModel productImportModel;
	  private static List<BreathRate> heatBeatList = new LinkedList<>();

	 
	    public static String[] LINE_NAMES = {"Limit", "Current", "Low2"};

	    static {
	        revenueModel = new DefaultCategoryModel();
	        for (String month : Util.MONTHS) {
	        
	            revenueModel.setValue(LINE_NAMES[0], month, 88);
	            revenueModel.setValue(LINE_NAMES[1], month, Util.random.nextInt(110));
	            revenueModel.setValue(LINE_NAMES[2], month, 75);
	        }

//	        productImportModel = new DefaultCategoryModel();
//	        for (String month : Util.MONTHS) {
//	            productImportModel.setValue(LINE_NAMES[0], month, Util.random.nextInt(10000));
//	            productImportModel.setValue(LINE_NAMES[1], month, Util.random.nextInt(10000));
//	            productImportModel.setValue(LINE_NAMES[2], month, Util.random.nextInt(10000));
//	        }

	        for (String name : LINE_NAMES) {
	        	BreathRate product = new BreathRate(name);
	            product.setQuantity(Util.nextInt(20, 100));
	            product.setPrice(Util.nextInt(100,1000) / 10);
	   
	            heatBeatList.add(product);
	        }
	    }

	    static public CategoryModel getRevenueModel() {
	        return revenueModel;
	    }
	    
//	    static public List<BreathRate> queryHeartBeat(){
//	        return heatBeatList;
//	    }
//
//	    public static ChartsModel getProductImportData() {
//	        return productImportModel;
//	    }
}
