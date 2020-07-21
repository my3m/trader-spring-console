package com.mscode.fruitpal.model;

import java.util.List;

public class FruitPalCommandResponsePrinter {

	public static String getFormattedResponse(FruitPalVendorResponseResult fruitPalVendorResponseResult) {
		if(fruitPalVendorResponseResult == null)
			throw new NullPointerException("No fruitpal command response.");
		StringBuilder sb = new StringBuilder();
		List<FruitPalVendorResponse> vendors = fruitPalVendorResponseResult.getResult();
		for(int i = 0; i < vendors.size(); i++) {
			FruitPalVendorResponse fruitPalVendorResponse = vendors.get(i);
			sb.append(String.format("< %s %.2f | (%.2f*%d)+%.0f",
					fruitPalVendorResponse.getCountryCode(),
					fruitPalVendorResponse.getTotalCost(),
					fruitPalVendorResponse.getCostPerUnit(),
					fruitPalVendorResponse.getTradingVolume(),
					fruitPalVendorResponse.getOverheadCost()));
			if(i + 1 < vendors.size())
				sb.append("\r\n");
		}
		return sb.toString();
	}
}
