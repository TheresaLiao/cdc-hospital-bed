package org.itri.view.humanhealth.dao;

import org.zkoss.admin.ecommerce.dao.Type;

public class PersonState {

	private String name;
	private int heartBeat;
	private double bodyTemperature;
	private int breathRate;
	private Type type;
	private int value;
	private double ratio;
	private Status status;
	
	
	public int getBreathRate() {
		return breathRate;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public void setBreathRate(int breathRate) {
		this.breathRate = breathRate;
	}
	private String bedRoom;
	
	
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
	public int getHeartBeat() {
		return heartBeat;
	}
	public void setHeartBeat(int heartBeat) {
		this.heartBeat = heartBeat;
	}
	public double getBodyTemperature() {
		return bodyTemperature;
	}
	public void setBodyTemperature(double bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}
	
}
