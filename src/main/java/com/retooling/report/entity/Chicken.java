package com.retooling.report.entity;

import java.util.Date;

public class Chicken {
	
	private String chickenId;
	private String farmId;
	private String state;
	private Date creationDate;

	public Chicken() {
		super();
	}
	
	public Chicken(String chickenId, String farmId, String state, Date creationDate) {
		super();
		this.chickenId = chickenId;
		this.farmId = farmId;
		this.state = state;
		this.creationDate = creationDate;
	}
	
	public String getChickenId() {
		return chickenId;
	}
	public void setChickenId(String chickenId) {
		this.chickenId = chickenId;
	}

	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Chicken [chickenId=" + chickenId + ", farmId=" + farmId + ", state=" + state + ", creationDate="
				+ creationDate + "]";
	}
	
}
