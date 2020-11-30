package org.itri.view.navigation;

import java.util.*;

public class NavDao {
	private static List<Menu> menuList = new LinkedList<>();

	static {
		initMenus();
	}

	static public void initMenus() {

		Menu menuHuamanHealth = new Menu("�ж��`����T", "z-icon-home");
		menuHuamanHealth.setPath(NavigationMdel.DASHBOARD_HUMANHEALTH_ZUL);
		menuList.add(menuHuamanHealth);

		Menu menuHuamanChartSet = new Menu("�����Y���f�H", "z-icon-home");
		menuHuamanChartSet.setPath(NavigationMdel.DASHBOARD_HUMANCHARTSET_ZUL);
		menuList.add(menuHuamanChartSet);

		Menu menuHuamanPersonChart = new Menu("�f�H�ӧO��T", "z-icon-home");
		menuHuamanPersonChart.setPath(NavigationMdel.DASHBOARD_HUMANCHARTSET_PERSON_ZUL);
		menuList.add(menuHuamanPersonChart);

//		Menu menuHuamanPersonalTable = new Menu("�ӤH���X��T", "z-icon-home");
//		menuHuamanPersonalTable.setPath(NavigationMdel.DASHBOARD_HUMANTABLE_PERSON_ZUL);
//		menuList.add(menuHuamanPersonalTable);
//
//		Menu menuFeelingQuest = new Menu("�ӤH�ݨ�", "z-icon-home");
//		menuFeelingQuest.setPath(NavigationMdel.DASHBOARD_FEELING_QUEST_ZUL);
//		menuList.add(menuFeelingQuest);
	}

	static public List<Menu> queryMenu() {
		return menuList;
	}
}
