package org.itri.view.navigation;

import java.util.*;

public class NavDao {
    private static List<Menu> menuList = new LinkedList<>();

    static{
        initMenus();
    }

    static public void initMenus(){
        Menu menuFloorSet = new Menu("floorSet", "z-icon-home");
        menuFloorSet.setPath(NavigationMdel.DASHBOARD_FLOORSET_ZUL);
        menuList.add(menuFloorSet);
        
        Menu menuHuamanHealth = new Menu("huamanHealth", "z-icon-home");
        menuHuamanHealth.setPath(NavigationMdel.DASHBOARD_HUAMANHEALTH_ZUL);
        menuList.add(menuHuamanHealth);
    }

    static public List<Menu> queryMenu(){
        return menuList;
    }
}
