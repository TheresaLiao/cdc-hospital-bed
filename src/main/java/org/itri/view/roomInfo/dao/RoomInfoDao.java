package org.itri.view.roomInfo.dao;


import org.zkoss.admin.util.Util;
import java.util.*;

/**
 * Data access object
 */
public class RoomInfoDao {

    private static List<RoomInfo> roomInfoList = new LinkedList<>();
    static {
       

    	 for (int i = 0 ; i < 9 ; i++){
            RoomInfo roomInfo = new RoomInfo("Lee John "+String.valueOf(i));
            
            roomInfo.setBodyTemperature(39.5);
            roomInfo.setHeartBeat(118);
            roomInfo.setBreathRate(20);
            roomInfo.setBedRoom("20" + String.valueOf(i+1)+"-1");
            
            roomInfo.setQuantity(Util.nextInt(20, 100));
            roomInfo.setPrice(Util.nextInt(100,1000) / 10);
            roomInfoList.add(roomInfo);
        }
    }


    static public List<RoomInfo> queryRoomInfo(){
        return roomInfoList;
    }

}
