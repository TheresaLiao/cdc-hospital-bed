package org.itri.view.humanhealth.dao;

import org.zkoss.admin.ecommerce.dao.Type;

public class PersonState {

	private long id;
	private String name;
	private String heartBeat;
	private String bodyTemperature;
	private String breathRate;
	private Type type;
	private String bedRoom;
	private String oximeter;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String totalStatus;

	public String getTotalStatus() {
		return totalStatus;
	}

	public void setTotalStatus(String totalStatus) {
		this.totalStatus = totalStatus;
	}

	public String getBreathRate() {
		return breathRate;
	}

	public String getOximeter() {
		return oximeter;
	}

	public void setOximeter(String oximeter) {
		this.oximeter = oximeter;
	}

	public void setBreathRate(String breathRate) {
		this.breathRate = breathRate;
	}

	public Type getType() {
		return type;
	}

	public String getBedRoom() {
		return bedRoom;
	}

	public void setBedRoom(String bedRoom) {
		this.bedRoom = bedRoom;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeartBeat() {
		return heartBeat;
	}

	public void setHeartBeat(String heartBeat) {
		this.heartBeat = heartBeat;
	}

	public String getBodyTemperature() {
		return bodyTemperature;
	}

	public void setBodyTemperature(String bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

}
