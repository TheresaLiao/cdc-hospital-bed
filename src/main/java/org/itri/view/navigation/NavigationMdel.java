package org.itri.view.navigation;

public class NavigationMdel {
	public static final String DASHBOARD_FLOORSET_ZUL = "/floor/floorSet.zul";
    public static final String DASHBOARD_HUAMANHEALTH_ZUL = "/humanHeath/huamanHealth.zul";
    
    public static final String BLANK_ZUL = "/blank.zul";

    private String contentUrl = DASHBOARD_HUAMANHEALTH_ZUL;

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
