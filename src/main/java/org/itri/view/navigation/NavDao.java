package org.itri.view.navigation;

import java.util.*;

public class NavDao {
    private static List<Menu> menuList = new LinkedList<>();

    static{
        initMenus();
    }

    static public void initMenus(){
        Menu menuFloorSet = new Menu("樓層管理", "z-icon-home");
        menuFloorSet.setPath(NavigationMdel.DASHBOARD_FLOORSET_ZUL);
        menuList.add(menuFloorSet);
        
        Menu menuHuamanHealth = new Menu("房間資訊", "z-icon-home");
        menuHuamanHealth.setPath(NavigationMdel.DASHBOARD_HUMANHEALTH_ZUL);
        menuList.add(menuHuamanHealth);
        
        Menu menuHuamanChartSet = new Menu("個人資訊", "z-icon-home");
        menuHuamanChartSet.setPath(NavigationMdel.DASHBOARD_HUMANCHARTSET_ZUL);
        menuList.add(menuHuamanChartSet);
    }

    static public List<Menu> queryMenu(){
        return menuList;
    }
}
