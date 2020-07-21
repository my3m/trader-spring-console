package com.mscode.fruitpal;

import com.mscode.fruitpal.model.FruitPalCommodityProvider;
import com.mscode.fruitpal.services.IFruitPalCostProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("mock")
public class MockFuelPalCommodityData implements IFruitPalCostProvider {

    @Override
    public List<FruitPalCommodityProvider> getVendors(String commodity) {
        List<FruitPalCommodityProvider> commodityProviders = new ArrayList<>();
        commodityProviders.add(new FruitPalCommodityProvider("MX", "mango", 32.0, 1.24));
        commodityProviders.add(new FruitPalCommodityProvider("BR", "mango", 20.0, 1.42));
        return commodityProviders;
    }
}
