package com.mscode.fruitpal;

import java.util.Scanner;
import com.mscode.fruitpal.model.*;
import com.mscode.fruitpal.services.FruitPalCalculationService;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class FruitPalCommandLineRunner implements CommandLineRunner, ApplicationContextAware {

	private final static String DELIMITER = " ";
	private final static String INPUT_FORMAT = "<fruit> <cost> <quantity>";
	private final static String SAMPLE_INPUT = "fruitpal mango 53 405";

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		String command = "";
		System.out.println("Please enter input");
		System.out.println();
		System.out.printf("Example: %s", INPUT_FORMAT);
		System.out.println();

		while(!(command = scanner.nextLine()).equals("exit")) {
			System.out.print("> fruitpal ");
			System.out.println();
			FruitPalCommandRequest fruitPalCommandRequest = parseInputCommand(command);
			if(fruitPalCommandRequest.isValidRequest()) {
				FruitPalVendorResponseResult fruitPalCommandResponse =
						applicationContext.getBean(FruitPalCalculationService.class)
						.getTradingCost(fruitPalCommandRequest);
				if(fruitPalCommandResponse.getResult().isEmpty()) {
					System.out.println("> there is no vendor with that commodity");
				}
				else {
					String formattedResponse = FruitPalCommandResponsePrinter.getFormattedResponse(fruitPalCommandResponse);
					System.out.println(formattedResponse);
				}
			}
			else {
				System.out.println(fruitPalCommandRequest.getErrorMessage());
			}
		}
		scanner.close();
	}
	
	public static FruitPalCommandRequest parseInputCommand(String input) {
		FruitPalCommandRequest request = new FruitPalCommandRequest();
		if(input.length() == 0) {
			request.setValidRequest(false);
			request.setErrorMessage("Empty command");
			return request;
		}
		String[] parts = input.split(DELIMITER);
		if(parts.length != 3) {
			request.setValidRequest(false);
			request.setErrorMessage(String.format("Invalid args. Make sure to provide valid format %s", SAMPLE_INPUT));
			return request;
		}
		String commodity = parts[0];
		for(char chr : commodity.toCharArray()) {
			if(!Character.isLetter(chr)) {
				request.setValidRequest(false);
				request.setErrorMessage("Only chars allowed in <fruit>");
				return request;
			}
		}
		int cost = 0;
		for(char chr : parts[1].toCharArray()) {
			if(!Character.isDigit(chr)) {
				request.setValidRequest(false);
				request.setErrorMessage("Only numbers allowed in <cost>");
				return request;
			}
			cost = cost*10 + (chr - '0');
		}
		int tradingVolume = 0;
		for(char chr : parts[2].toCharArray()) {
			if(!Character.isDigit(chr)) {
				request.setValidRequest(false);
				request.setErrorMessage("Only numbers allowed in <quantity>");
				return request;
			}
			tradingVolume = tradingVolume*10 + (chr - '0');
		}		
		return new FruitPalCommandRequest(commodity, cost, tradingVolume);
	}
}
