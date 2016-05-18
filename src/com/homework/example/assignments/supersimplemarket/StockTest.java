package com.homework.example.assignments.supersimplemarket;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Test;
import com.homework.example.assignments.supersimplemarket.enums.*;

/** Different Stock Test **/
public class StockTest {

	@Test
	public void testDividend() {
		SimpleData stockALE = new SimpleData("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		SimpleData stockGIN = new SimpleData("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0);
        // for Common
		Double dividendALE = stockALE.dividend(1.0);
		Double expectedDividendALE = stockALE.getLastDividend()/1.0;
		assertEquals(expectedDividendALE, dividendALE, 0.0);
		// for Preferred
		Double dividendGIN = stockGIN.dividend(1.0);
		Double expectedDividendGIN = stockGIN.getFixedDividend()*stockGIN.getParValue()/1.0;
		assertEquals(expectedDividendGIN, dividendGIN, 0.0);
	}

	@Test
	public void testPERatio() {
		SimpleData stockALE = new SimpleData("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
        Double peRatioALE = stockALE.PERatio(1.0);
        Double expectedPeRatioALE = 1.0/stockALE.getLastDividend();
        assertEquals(expectedPeRatioALE, peRatioALE, 0.0);
	}

	@Test
	public void testBuy() {
		SimpleData stockALE = new SimpleData("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.buy(1, 10.0);
		assertEquals(10.0, stockALE.getPrice(), 0.0);
	}

	@Test
	public void testSell() {
		SimpleData stockALE = new SimpleData("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.sell(1, 10.0);
		assertEquals(10.0, stockALE.getPrice(), 0.0);
	}

	@Test
	public void testGetPrice() {
		SimpleData stockALE = new SimpleData("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.sell(1, 10.0);
		stockALE.buy(1, 11.0);
		assertEquals(11.0, stockALE.getPrice(), 0.0);
	}

	@Test
	public void testCalculateVolumeWeightedStockPrice() {
		SimpleData stockALE = new SimpleData("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		stockALE.sell(2, 10.0);
		stockALE.buy(2, 10.0);		
		Double volumeWeightedStockPrice = stockALE.calculateVolumeWeightedStockPrice();
		assertEquals(10.0, volumeWeightedStockPrice, 0.0);
		Date now = new Date();
		// for Date 20 minutes ago calculations
		Date startTime = new Date(now.getTime() - (20 * 60 * 1000));
		stockALE.getTrades().put(startTime, new TradeMarket(TradeType.BUY, 1, 20.0));		
		volumeWeightedStockPrice = stockALE.calculateVolumeWeightedStockPrice();
		assertEquals(10.0, volumeWeightedStockPrice, 0.0);
	}
}
