package com.mscode.fruitpal.model;

public class FruitPalCommandRequest {
	private String fruit;
	private double price;
	private int tradingVolume;
	private boolean isValidRequest;
	private String errorMessage;
	
	public FruitPalCommandRequest() {
		
	}
	
	public FruitPalCommandRequest(String fruit, double price, int tradingVolume) {
		this.fruit = fruit;
		this.price = price;
		this.tradingVolume = tradingVolume;
		this.isValidRequest = true;
	}
	
	public int getTradingVolume() {
		return tradingVolume;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getFruit() {
		return fruit;
	}

	public boolean isValidRequest() {
		return isValidRequest;
	}

	public void setValidRequest(boolean isValidRequest) {
		this.isValidRequest = isValidRequest;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
