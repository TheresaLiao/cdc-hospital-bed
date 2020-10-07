package org.itri.view.navigation;

import java.util.*;

public class NavDao {
	private static List<Menu> menuList = new LinkedList<>();

	static {
		initMenus();
	}

	static public void initMenus() {


		Menu menuHuamanHealth = new Menu("房間總結資訊", "z-icon-home");
		menuHuamanHealth.setPath(NavigationMdel.DASHBOARD_HUMANHEALTH_ZUL);
		menuList.add(menuHuamanHealth);

		Menu menuHuamanChartSet = new Menu("較為嚴重病人", "z-icon-home");
		menuHuamanChartSet.setPath(NavigationMdel.DASHBOARD_HUMANCHARTSET_ZUL);
		menuList.add(menuHuamanChartSet);

		Menu menuHuamanPersonChart = new Menu("病人個別資訊", "z-icon-home");
		menuHuamanPersonChart.setPath(NavigationMdel.DASHBOARD_HUMANCHARTSET_PERSON_ZUL);
		menuList.add(menuHuamanPersonChart);

		Menu menuHuamanPersonalTable = new Menu("個人集合資訊", "z-icon-home");
		menuHuamanPersonalTable.setPath(NavigationMdel.DASHBOARD_HUMANTABLE_PERSON_ZUL);
		menuList.add(menuHuamanPersonalTable);
	}

	static public List<Menu> queryMenu() {
		return menuList;
	}
}
