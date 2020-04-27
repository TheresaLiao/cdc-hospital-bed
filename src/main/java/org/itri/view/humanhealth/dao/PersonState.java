package org.itri.view.humanhealth.dao;

import org.zkoss.admin.ecommerce.dao.Type;

public class PersonState {

	private String name;
	private String heartBeat;
	private String bodyTemperature;
	private String breathRate;
	private Type type;
	private String bedRoom;
	
	private int value;
	private double ratio;
	private Status status;
	private String spo2;
	
	
	public String getSpo2() {
		return spo2;
	}


	public void setSpo2(String spo2) {
		this.spo2 = spo2;
	}
	
	public String getBreathRate() {
		return breathRate;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
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
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
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
