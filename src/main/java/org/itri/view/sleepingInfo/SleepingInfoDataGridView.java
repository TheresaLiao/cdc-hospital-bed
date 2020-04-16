package org.itri.view.sleepingInfo;


import org.itri.view.sleepingInfo.dao.*;
import org.zkoss.zul.ListModelList;

public class SleepingInfoDataGridView {
    private ListModelList<SleepingInfoRaw> activityList;

    public SleepingInfoDataGridView(){
        activityList = new ListModelList<>(SleepingInfoDao.getRecentActivityList());
    }

    public ListModelList<SleepingInfoRaw> getActivityList() {
        return activityList;
    }
}
