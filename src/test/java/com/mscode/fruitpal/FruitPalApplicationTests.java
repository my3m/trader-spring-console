package com.mscode.fruitpal;

import com.mscode.fruitpal.model.FruitPalCommandRequest;
import com.mscode.fruitpal.model.FruitPalVendorResponseResult;
import com.mscode.fruitpal.services.FruitPalCalculationService;
import com.mscode.fruitpal.services.IFruitPalCostProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/*

	Ideally you would want to test all services, filereaders .etc.
	but for simplicity, I am only testing core functionality
 */
@SpringBootTest
class FruitPalApplicationTests {

	private FruitPalCalculationService fruitPalCalculationService;

	@Autowired
	@Qualifier("mock")
	public void setFruitPalCalculationService(IFruitPalCostProvider fruitPalCostProvider) {
		this.fruitPalCalculationService = new FruitPalCalculationService(fruitPalCostProvider);
	}

	@Test
	void contextLoads() {
		//fruitPalCalculationService = new FruitPalCalculationService(new MockFuelPalCommodityData());
	}

	@Test
	public void TEST_EMPTY_INPUT() {
		FruitPalCommandRequest fruitPalCommandRequest = FruitPalCommandLineRunner.parseInputCommand("");
		assertFalse(fruitPalCommandRequest.isValidRequest());
	}

	@Test
	public void TEST_INVALID_INPUT() {
		FruitPalCommandRequest fruitPalCommandRequest = FruitPalCommandLineRunner.parseInputCommand("apple");
		assertFalse(fruitPalCommandRequest.isValidRequest());
	}

	@Test
	public void TEST_INVALID_INPUT2() {
		FruitPalCommandRequest fruitPalCommandRequest = FruitPalCommandLineRunner.parseInputCommand("app31le");
		assertFalse(fruitPalCommandRequest.isValidRequest());
	}

	@Test
	public void TEST_INVALID_INPUT3() {
		FruitPalCommandRequest fruitPalCommandRequest = FruitPalCommandLineRunner.parseInputCommand("apple 2");
		assertFalse(fruitPalCommandRequest.isValidRequest());
	}

	@Test
	public void TEST_INVALID_INPUT4() {
		FruitPalCommandRequest fruitPalCommandRequest = FruitPalCommandLineRunner.parseInputCommand("apple 2 a");
		assertFalse(fruitPalCommandRequest.isValidRequest());
		FruitPalCommandRequest fruitPalCommandRequest2 = FruitPalCommandLineRunner.parseInputCommand("apple f 3");
		assertFalse(fruitPalCommandRequest2.isValidRequest());
	}

	@Test
	public void TEST_VALID_INPUT() {
		FruitPalCommandRequest fruitPalCommandRequest = FruitPalCommandLineRunner.parseInputCommand("mango 54 405");
		assertEquals(fruitPalCommandRequest.isValidRequest(), true);
	}

	@Test
	public void TEST_VALID_INPUT2() {
		FruitPalCommandRequest fruitPalCommandRequest = FruitPalCommandLineRunner.parseInputCommand("coffee 54 405");
		assertEquals(fruitPalCommandRequest.isValidRequest(), true);
	}

	@Test
	public void TEST_CALCULATION1() {
		FruitPalCommandRequest fruitPalCommandRequest = FruitPalCommandLineRunner.parseInputCommand("mango 53 405");
		FruitPalVendorResponseResult fruitPalVendorResponseResult = fruitPalCalculationService.getTradingCost(fruitPalCommandRequest);
		assertEquals(fruitPalCommandRequest.isValidRequest(), true);
		//There are two vendors for "mango" in fake data source
		assertEquals(fruitPalVendorResponseResult.getResult().size(), 2);
	}

	@Test
	public void TEST_SORTED_CALCULATION() {
		FruitPalCommandRequest fruitPalCommandRequest = FruitPalCommandLineRunner.parseInputCommand("mango 53 405");
		FruitPalVendorResponseResult fruitPalVendorResponseResult = fruitPalCalculationService.getTradingCost(fruitPalCommandRequest);
		assertEquals(fruitPalCommandRequest.isValidRequest(), true);
		assertEquals(fruitPalVendorResponseResult.getResult().size(), 2);
		//BR Should be placed at the top, since it costs the most
		assertEquals(fruitPalVendorResponseResult.getResult().get(0).getCountryCode(), "BR");
		assertEquals(fruitPalVendorResponseResult.getResult().get(1).getCountryCode(), "MX");
	}

	@Test
	public void TEST_ACCURACY_SORTED_CALCULATION2() {
		FruitPalCommandRequest fruitPalCommandRequest = FruitPalCommandLineRunner.parseInputCommand("mango 53 405");
		FruitPalVendorResponseResult fruitPalVendorResponseResult = fruitPalCalculationService.getTradingCost(fruitPalCommandRequest);
		assertEquals(fruitPalCommandRequest.isValidRequest(), true);
		assertEquals(fruitPalVendorResponseResult.getResult().size(), 2);
		final double costPerUnitBR = 53.0 + 1.42, costPerUnitMX = 53.0+1.24;
		final double overheadBR = 20.0, overheadMX = 32.0;
		final int tradingVolumeBR = 405;
		assertEquals(fruitPalVendorResponseResult.getResult().get(0).getTotalCost(), costPerUnitBR*tradingVolumeBR + overheadBR);
		assertEquals(fruitPalVendorResponseResult.getResult().get(1).getTotalCost(), costPerUnitMX*tradingVolumeBR+ overheadMX);
	}
}
