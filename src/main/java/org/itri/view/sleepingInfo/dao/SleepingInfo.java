package org.itri.view.sleepingInfo.dao;

public class SleepingInfo {
    private double persent;
	
	private String title;
    private int incompleted;
    private int completed;
    
    private String name;
    private int quantity;
    private double price;

    
    public SleepingInfo(String name){
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

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   

    public int getIncompleted() {
        return incompleted;
    }


	public double getPersent() {
		return persent;
	}

	public void setPersent(double persent) {
		this.persent = persent;
	}

	public void setIncompleted(int incompleted) {
        this.incompleted = incompleted;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
    
    public double getAmount() {
        return quantity * price;
    }
}

