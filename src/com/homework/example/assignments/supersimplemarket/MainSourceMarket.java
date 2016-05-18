package com.homework.example.assignments.supersimplemarket;

import java.util.*;
	
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import com.homework.example.assignments.supersimplemarket.enums.StockType;

public class MainSourceMarket 
	{
		private static Log log = LogFactory.getLog(MainSourceMarket.class);
		
	    @Bean
	    Map<String, SimpleData> Db() {
	        HashMap<String, SimpleData> db = new HashMap<String, SimpleData>();
	        db.put("TEA", new SimpleData("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
	        db.put("POP", new SimpleData("POP", StockType.COMMON, 8.0, 0.0, 100.0));
	        db.put("ALE", new SimpleData("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
	        db.put("GIN", new SimpleData("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
	        db.put("JOE", new SimpleData("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
	        return db;
	    }

public static void main( String[] args )
	{
	     try {
	            
	    	 ApplicationContext context = new AnnotationConfigApplicationContext(MainSourceMarket.class);
	            
	            // Dividend and P/E Ratio Calc	            
	    		Map<String, SimpleData> db = context.getBean("Db", Map.class);
	            for (SimpleData stock: db.values()) {
	            	log.debug( stock.getSymbol() + " Dividend: " + stock.dividend(9.1));
	            	log.debug( stock.getSymbol() + " P/E Ratio: " + stock.PERatio(9.1));
	            	
	                // Record some sample trades
	            	for (int i=1; i <= 10; i++) {
	            		Random r = new Random();
	            		Integer rangeMin = 1;
	            		Integer rangeMax = 10;
	            		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	            		stock.buy(i, randomValue);
	            		log.debug( stock.getSymbol() + " Bought " + i + " Shares at £" + randomValue);
	            		randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	            		stock.sell(i, randomValue);
	            		log.debug( stock.getSymbol() + " Sold " + i + " Shares at £" + randomValue);
	            		Thread.sleep(1000);
	            	}
	            	log.debug( stock.getSymbol() + " Price: £" + stock.getPrice());
	            	log.debug( stock.getSymbol() + " VolumeWeightedStockPrice: £" + stock.calculateVolumeWeightedStockPrice());
	            }
	            Double GBCEallShareIndex = GBCE.allShareIndex(db);
	            
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
	    }
}