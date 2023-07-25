package com.retooling.report.entity;

public class CurrentStatusReport {
	
	private String farmId;
	private String farmName;
	private double farmMoney;
	private long chickenLimit;
	private long eggLimit;
	private long eggsCount;
	private long chickensCount;

	public CurrentStatusReport() {
		super();
	}

	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}
	
	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}
	
	public double getFarmMoney() {
		return farmMoney;
	}

	public void setFarmMoney(double farmMoney) {
		this.farmMoney = farmMoney;
	}

	public long getChickenLimit() {
		return chickenLimit;
	}

	public void setChickenLimit(long chickenLimit) {
		this.chickenLimit = chickenLimit;
	}

	public long getEggLimit() {
		return eggLimit;
	}

	public void setEggLimit(long eggLimit) {
		this.eggLimit = eggLimit;
	}
	
	public long getEggsCount() {
		return eggsCount;
	}

	public void setEggsCount(long eggsCount) {
		this.eggsCount = eggsCount;
	}

	public long getChickensCount() {
		return chickensCount;
	}

	public void setChickensCount(long chickensCount) {
		this.chickensCount = chickensCount;
	}

	@Override
	public String toString() {
		return "CurrentStatusFarm [farmId=" + farmId + ", farmName=" + farmName + ", farmMoney=" + farmMoney
				+ ", chickenLimit=" + chickenLimit + ", eggLimit=" + eggLimit + ", eggsCount=" + eggsCount
				+ ", chickensCount=" + chickensCount + "]";
	}

}
