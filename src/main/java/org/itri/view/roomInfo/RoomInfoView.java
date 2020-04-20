package org.itri.view.roomInfo;

import org.itri.view.roomInfo.dao.*;
import org.zkoss.bind.annotation.Init;
import java.util.List;

public class RoomInfoView {
    private List<RoomInfo> roomInfoList;

    @Init
    public void init(){
    	roomInfoList = RoomInfoDao.queryRoomInfo();
    }

    public List<RoomInfo> getRoomInfoList() {
        return roomInfoList;
    }
}
