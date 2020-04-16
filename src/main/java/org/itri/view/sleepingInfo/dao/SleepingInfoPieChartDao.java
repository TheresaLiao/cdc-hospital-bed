package org.itri.view.sleepingInfo.dao;


import org.zkoss.admin.util.Util;
import org.zkoss.chart.model.*;

import java.util.*;

/**
 * Data access object
 */
public class SleepingInfoPieChartDao {
    private static CategoryModel revenueModel;
    private static CategoryModel productImportModel;
    private static List<SleepingInfo> productList = new LinkedList<>();

    public static String[] PRODUCT_NAMES = {"Left sleep", "Right sleep", "Site", "Stand"};

    static {
        revenueModel = new DefaultCategoryModel();
        for (String month : Util.MONTHS) {
            revenueModel.setValue(PRODUCT_NAMES[1], month, Util.random.nextInt(10000));
            revenueModel.setValue(PRODUCT_NAMES[3], month, Util.random.nextInt(10000));
        }

        productImportModel = new DefaultCategoryModel();
        for (String month : Util.MONTHS) {
            productImportModel.setValue(PRODUCT_NAMES[3], month, Util.random.nextInt(10000));
            productImportModel.setValue(PRODUCT_NAMES[2], month, Util.random.nextInt(10000));
        }

        for (String name : PRODUCT_NAMES) {
        	SleepingInfo product = new SleepingInfo(name);
            product.setQuantity(Util.nextInt(20, 100));
            product.setPrice(Util.nextInt(100,1000) / 10);
            productList.add(product);
        }
    }

    static public CategoryModel getRevenueModel() {
        return revenueModel;
    }

    static public List<SleepingInfo> queryProduct(){
        return productList;
    }

    public static ChartsModel getProductImportData() {
        return productImportModel;
    }
}
