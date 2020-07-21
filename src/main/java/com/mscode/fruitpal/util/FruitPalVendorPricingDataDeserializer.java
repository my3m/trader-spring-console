package com.mscode.fruitpal.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mscode.fruitpal.model.FruitPalCommodityProvider;
import com.mscode.fruitpal.model.FruitPalVendorData;

import java.io.IOException;

public class FruitPalVendorPricingDataDeserializer extends StdDeserializer<FruitPalVendorData> {

        public FruitPalVendorPricingDataDeserializer() {
            this(null);
        }

        public FruitPalVendorPricingDataDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public FruitPalVendorData deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            FruitPalVendorData fruitPalVendorData = new FruitPalVendorData();
            JsonNode node = jp.getCodec().readTree(jp);
            node.get("PRICING").forEach(x-> {
                String country = x.get("COUNTRY").asText();
                String commodity = x.get("COMMODITY").asText();
                double fixedOverhead = Double.parseDouble(x.get("FIXED_OVERHEAD").asText());
                double variableOverhead = Double.parseDouble(x.get("VARIABLE_OVERHEAD").asText());

                fruitPalVendorData.getPricing().add(new FruitPalCommodityProvider(country, commodity, fixedOverhead, variableOverhead));
            });
            return fruitPalVendorData;
        }
    }
