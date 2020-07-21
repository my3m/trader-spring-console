package com.mscode.fruitpal.services;

import com.mscode.fruitpal.model.FruitPalCommodityProvider;

import java.util.List;

public interface IFruitPalCostProvider {
    public List<FruitPalCommodityProvider> getVendors(String commodity);
}
