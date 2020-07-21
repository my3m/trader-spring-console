package com.mscode.fruitpal.model;

public class FruitPalVendorResponse {
	private String countryCode;
	private double totalCost;
	private int tradingVolume;
	private double costPerUnit;
	private double overheadCost;

	public FruitPalVendorResponse() {

	}

	public FruitPalVendorResponse(boolean isValidResponse, String errorMessage, String countryCode, double totalCost,
			int tradingVolume, double costPerUnit, double overheadCost) {
		super();
		this.countryCode = countryCode;
		this.totalCost = totalCost;
		this.tradingVolume = tradingVolume;
		this.costPerUnit = costPerUnit;
		this.overheadCost = overheadCost;
	}

	public String getCountryCode() {
		return countryCode;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public int getTradingVolume() {
		return tradingVolume;
	}
	public double getCostPerUnit() {
		return costPerUnit;
	}
	public double getOverheadCost() {
		return overheadCost;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public void setTradingVolume(int tradingVolume) {
		this.tradingVolume = tradingVolume;
	}

	public void setCostPerUnit(double costPerUnit) {
		this.costPerUnit = costPerUnit;
	}

	public void setOverheadCost(double overheadCost) {
		this.overheadCost = overheadCost;
	}

}
