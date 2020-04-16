package org.itri.view.humanhealth.dao;

import org.zkoss.admin.util.Util;
import org.zkoss.chart.model.*;

import java.util.*;

/**
 * Data access object
 */
public class HeartBeatDao {
	  private static CategoryModel revenueModel;
	  private static List<HeartBeatInfo> heatBeatList = new LinkedList<>();
	  public static String[] LINE_NAMES = {"Limit", "Current", "Low"};

	    static {
	        revenueModel = new DefaultCategoryModel();
	        for (String month : Util.MONTHS) {
	            revenueModel.setValue(LINE_NAMES[0], month, 88);
	            revenueModel.setValue(LINE_NAMES[1], month, (Util.random.nextInt(50)+60)%120);
	            revenueModel.setValue(LINE_NAMES[2], month, 65);
	        }

	        for (String name : LINE_NAMES) {
	        	HeartBeatInfo heartBeatInfo = new HeartBeatInfo(name);
	        	heartBeatInfo.setQuantity(Util.nextInt(20, 100));
	        	heartBeatInfo.setPrice(Util.nextInt(100,1000) / 10);
	   
	            heatBeatList.add(heartBeatInfo);
	        }
	    }

	    static public CategoryModel getRevenueModel() {
	        return revenueModel;
	    }
}
