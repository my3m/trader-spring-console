package com.mscode.fruitpal.services;

import com.mscode.fruitpal.model.FruitPalCommodityProvider;
import com.mscode.fruitpal.model.FruitPalVendorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("json")
public class FruitPalCostProvider implements IFruitPalCostProvider {

	@Autowired
	public FruitPalCostProvider(FruitPalReader fruitPalReader) {
		this.fruitPalReader = fruitPalReader;
	}

	private final FruitPalReader fruitPalReader;

	public List<FruitPalCommodityProvider> getVendors(String commodity) {
		if(commodity == null || commodity.length() == 0)
			throw new IllegalArgumentException("commodity cannot be empty");
		try
		{
			FruitPalVendorData data = this.fruitPalReader.getFruitPalData();
			List<FruitPalCommodityProvider> result = new ArrayList<>();
			for(FruitPalCommodityProvider fruitPalCommodityProvider : data.getPricing()) {
				if(fruitPalCommodityProvider.getCommodity().equals(commodity))
					result.add(fruitPalCommodityProvider);
			}
			return result;
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Failure to read fruit pal cost data", e);
		}
	}
}
