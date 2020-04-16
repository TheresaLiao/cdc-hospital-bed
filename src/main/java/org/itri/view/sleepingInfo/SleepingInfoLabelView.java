package org.itri.view.sleepingInfo;


import org.itri.view.sleepingInfo.dao.*;

import org.zkoss.zul.ListModelList;

public class SleepingInfoLabelView {
    private ListModelList<SleepingInfo> progressSummary;

    public SleepingInfoLabelView(){
        progressSummary = new ListModelList<>(SleepingInfoDao.getProgressSummary());
    }

    public ListModelList<SleepingInfo> getProgressSummary() {
        return progressSummary;
    }
}
