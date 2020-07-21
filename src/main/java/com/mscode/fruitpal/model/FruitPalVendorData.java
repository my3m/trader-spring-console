package com.mscode.fruitpal.model;

import java.util.ArrayList;
import java.util.List;

public class FruitPalVendorData {
    public List<FruitPalCommodityProvider> pricing;

    public FruitPalVendorData() {
        this.pricing = new ArrayList<>();
    }

    public FruitPalVendorData(List<FruitPalCommodityProvider> pricing) {
        this.pricing = pricing;
    }

    public List<FruitPalCommodityProvider> getPricing() {
        return pricing;
    }

    public void setPricing(List<FruitPalCommodityProvider> pricing) {
        this.pricing = pricing;
    }
}
