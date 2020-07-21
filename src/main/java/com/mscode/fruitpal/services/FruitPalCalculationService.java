package com.mscode.fruitpal.services;

import com.mscode.fruitpal.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/*
 * This service deals with calculation service
 * ideally we can have multiple implementations of a interface
 * & based on region, or any criteria, inject the right one
 * I am leaving it out for simplicity
 */
@Service
public class FruitPalCalculationService {
	
	IFruitPalCostProvider fruitPalCostProvider;

	@Autowired
	public FruitPalCalculationService(@Qualifier("json") IFruitPalCostProvider fruitPalCostProvider) {
		super();
		this.fruitPalCostProvider = fruitPalCostProvider;
	}

	public FruitPalVendorResponseResult getTradingCost(FruitPalCommandRequest fruitPalCommandRequest) {
		if(fruitPalCommandRequest == null)
			throw new NullPointerException("fruitPalCommandRequest");

		FruitPalVendorResponseResult result = new FruitPalVendorResponseResult();
		try
		{
			List<FruitPalCommodityProvider> vendors = fruitPalCostProvider.getVendors(fruitPalCommandRequest.getFruit());
			for(FruitPalCommodityProvider vendor : vendors) {
				FruitPalVendorResponse fruitPalVendorResponse = new FruitPalVendorResponse();
				double costPerUnit = fruitPalCommandRequest.getPrice() + vendor.getVariableOverhead();
				double totalCost = vendor.getFixedOverhead() + fruitPalCommandRequest.getTradingVolume() * costPerUnit;
				fruitPalVendorResponse.setCountryCode(vendor.getCountryCode());
				fruitPalVendorResponse.setTotalCost(totalCost);
				fruitPalVendorResponse.setTradingVolume(fruitPalCommandRequest.getTradingVolume());
				fruitPalVendorResponse.setOverheadCost(vendor.getFixedOverhead());
				fruitPalVendorResponse.setCostPerUnit(costPerUnit);
				result.addFruitPalVendor(fruitPalVendorResponse);
			}
		}
		catch (Exception e) {
			result.setValidResponse(false);
			result.setErrorMessage(e.getMessage());
		}
		//Sort from highest-lowest
		Collections.sort(result.getResult(), (a, b)-> a.getTotalCost() <= b.getTotalCost() ? 1 : -1);
		return result;
	}
}
