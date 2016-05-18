package com.homework.example.assignments.supersimplemarket;

import java.util.Map;

public class GBCE {

	/** It calculate the GBCE All Share Index for all stocks **/	
	public static Double allShareIndex(Map<String, SimpleData> stocks) {
		Double allShareIndex = 0.0;
		
		for(SimpleData stock: stocks.values()) {
			allShareIndex+=stock.getPrice();
		}
		return Math.pow(allShareIndex, 1.0 / stocks.size());
	}
	
}
