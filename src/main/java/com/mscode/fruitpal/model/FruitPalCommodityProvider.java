package com.mscode.fruitpal.model;

/*
      "COUNTRY":"MX",
      "COMMODITY":"mango",
      "FIXED_OVERHEAD":"32.00",
      "VARIABLE_OVERHEAD":"1.24"
 */
public class FruitPalCommodityProvider {
	private String countryCode;
	private String commodity;
	private double fixedOverhead;
	private double variableOverhead;

	public FruitPalCommodityProvider() {

	}

	public FruitPalCommodityProvider(String countryCode, String commodity, double fixedOverhead, double variableOverhead) {
		this.countryCode = countryCode;
		this.commodity = commodity;
		this.fixedOverhead = fixedOverhead;
		this.variableOverhead = variableOverhead;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getCommodity() {
		return commodity;
	}

	public double getFixedOverhead() {
		return fixedOverhead;
	}

	public double getVariableOverhead() {
		return variableOverhead;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public void setFixedOverhead(double fixedOverhead) {
		this.fixedOverhead = fixedOverhead;
	}

	public void setVariableOverhead(double variableOverhead) {
		this.variableOverhead = variableOverhead;
	}
}
