package org.itri.view.navigation;

public class NavigationMdel {
	// public static final String DASHBOARD_FLOORSET_ZUL = "/floor/floorSet.zul";
	public static final String DASHBOARD_HUMANHEALTH_ZUL = "/humanHealth/huamanHealth.zul";
	public static final String DASHBOARD_HUMANCHARTSET_ZUL = "/humanHealthDetail/humanChartSet.zul";

	public static final String BLANK_ZUL = "/blank.zul";

	private String contentUrl = DASHBOARD_HUMANHEALTH_ZUL;

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
}
