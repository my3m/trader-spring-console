package com.mscode.fruitpal.services;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mscode.fruitpal.model.FruitPalVendorData;
import com.mscode.fruitpal.util.FruitPalConfig;
import com.mscode.fruitpal.util.FruitPalVendorPricingDataDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

@Service
public class FruitPalReader {

    @Autowired
    private FruitPalConfig config;

    public FruitPalVendorData getFruitPalData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String path = config.getDatasource();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(FruitPalVendorData.class, new FruitPalVendorPricingDataDeserializer());
        objectMapper.registerModule(module);

        FruitPalVendorData commodityData = objectMapper.readValue(new File(path), FruitPalVendorData.class);
        return commodityData;
    }
}
