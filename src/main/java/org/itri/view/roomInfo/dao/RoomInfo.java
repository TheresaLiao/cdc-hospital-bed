package org.itri.view.roomInfo.dao;

public class RoomInfo {

    private int quantity;
    private double price;
    
    private String name;
	private String bedRoom;
	private int heartBeat;
	private double bodyTemperature;
	private int breathRate;
	
	
	public String getBedRoom() {
		return bedRoom;
	}

	public void setBedRoom(String bedRoom) {
		this.bedRoom = bedRoom;
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

	public int getBreathRate() {
		return breathRate;
	}

	public void setBreathRate(int breathRate) {
		this.breathRate = breathRate;
	}



    public RoomInfo(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return quantity * price;
    }
}
