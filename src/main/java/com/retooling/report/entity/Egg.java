package com.retooling.report.entity;

import java.util.Date;

public class Egg {
	
	private String eggId;
	private String farmId;
	private String state;
	private Date creationDate;

	public Egg() {
		super();
	}
	
	public Egg(String eggId, String farmId, String state, Date creationDate) {
		super();
		this.eggId = eggId;
		this.farmId = farmId;
		this.state = state;
		this.creationDate = creationDate;
	}
	
	public String getEggId() {
		return eggId;
	}
	public void setEggId(String eggId) {
		this.eggId = eggId;
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
		return "Egg [eggId=" + eggId + ", farmId=" + farmId + ", state=" + state + ", creationDate=" + creationDate
				+ "]";
	}
	
}
