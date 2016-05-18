package com.homework.example.assignments.supersimplemarket;

import java.util.*;
import com.homework.example.assignments.supersimplemarket.enums.*;

public class SimpleData {
	
	private String symbol;
	private StockType type;
	private Double lastDividend;
	private Double fixedDividend;
	private Double parValue;
    private TreeMap<Date, TradeMarket> trades;    
	
    /** Calculate the Volume Weighted Stock Price 	**/	
	public Double calculateVolumeWeightedStockPrice() {
		Date now = new Date();
		// for Date 15 minutes ago
		Date startTime = new Date(now.getTime() - (15 * 60 * 1000));
		// To get trades for the last 15 minutes
		SortedMap<Date, TradeMarket> trades = this.trades.tailMap(startTime);
		// Calculate Volume Weighted Stock Price
		Double volumeWeigthedStockPrice = 0.0;
		Integer totalQuantity = 0;
		for (TradeMarket trade: trades.values()) {
			totalQuantity += trade.getQuantity();
			volumeWeigthedStockPrice += trade.getPrice() * trade.getQuantity();
		}
		return volumeWeigthedStockPrice/totalQuantity;
	}	

	public SimpleData(String symbol, StockType type, Double lastDividend, Double fixedDividend, Double parValue) {
		this.setSymbol(symbol);
		this.setType(type);
		this.setLastDividend(lastDividend);
		this.setFixedDividend(fixedDividend);
		this.setParValue(parValue);
		this.trades = new TreeMap<Date, TradeMarket>();
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", type=" + type + ", lastDividend="+ lastDividend + ", fixedDividend=" + fixedDividend + ", parValue=" + parValue + "]";
	}

	/** Calculate the dividend based on the specified price **/	 
	public Double dividend(Double price) {
		switch(this.getType()) {
			case COMMON:
				return this.getLastDividend()/price;
			case PREFERRED:
				return this.getFixedDividend()*this.getParValue()/price;
			default:
				return 0.0;
		}
	}	

	/** Calculate P/E Ratio based on the specified price **/	
	public Double PERatio(Double price) {
		return price/this.getLastDividend();
	}
	
	/** Buy stock, specifying quantity and price **/
	public void buy(Integer quantity, Double price) {
		TradeMarket trade = new TradeMarket(TradeType.BUY, quantity, price);
		this.trades.put(new Date(), trade);
	}
	
	/** Sell stock, specifying quantity and price **/	
	public void sell(Integer quantity, Double price) {
		TradeMarket trade = new TradeMarket(TradeType.SELL, quantity, price);
		this.trades.put(new Date(), trade);		
	}	

	/** Return the current price of the stock based on the last trade price	**/	
	public Double getPrice() {
		if (this.trades.size() > 0) {
			// It uses the last trade price as price
			return this.trades.lastEntry().getValue().getPrice();
		} else {
			// No trades = price 0? :)
			return 0.0;
		}
	}	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public StockType getType() {
		return type;
	}

	public void setType(StockType type) {
		this.type = type;
	}

	public Double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	public TreeMap<Date, TradeMarket> getTrades() {
		return trades;
	}

	public void setTrades(TreeMap<Date, TradeMarket> trades) {
		this.trades = trades;
	}
	
}
