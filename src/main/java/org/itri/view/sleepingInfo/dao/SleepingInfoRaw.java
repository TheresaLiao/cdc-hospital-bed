package org.itri.view.sleepingInfo.dao;

import java.util.Date;

public class SleepingInfoRaw {
    private String owner;
    private String task;
    private String project;
    private Date completedTime;
    private Status status = Status.HOLD;
    private Date dueDate;
    
    private String post;
    private int count;
    private Date totalTime;    
    public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Date totalTime) {
		this.totalTime = totalTime;
	}

	

	public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(Date completedTime) {
        this.completedTime = completedTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
