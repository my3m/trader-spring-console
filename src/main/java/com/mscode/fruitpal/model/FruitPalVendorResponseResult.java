package com.mscode.fruitpal.model;

import java.util.ArrayList;
import java.util.List;

public class FruitPalVendorResponseResult {
    private boolean isValidResponse;
    private String errorMessage;
    private final List<FruitPalVendorResponse> result;

    public FruitPalVendorResponseResult() {
        this.result = new ArrayList<>();
    }

    public void addFruitPalVendor(FruitPalVendorResponse response) {
        result.add(response);
    }

    public List<FruitPalVendorResponse> getResult() {
        return result;
    }

    public boolean isValidResponse() {
        return isValidResponse;
    }

    public void setValidResponse(boolean validResponse) {
        isValidResponse = validResponse;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
