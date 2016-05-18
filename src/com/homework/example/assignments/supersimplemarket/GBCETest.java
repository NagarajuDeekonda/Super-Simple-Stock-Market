package com.homework.example.assignments.supersimplemarket;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Test;
import com.homework.example.assignments.supersimplemarket.enums.*;

/** Global Beverage Corporation Exchange Test **/
public class GBCETest {

	@Test
	public void testAllShareIndex() {
        HashMap<String, SimpleData> db = new HashMap<String, SimpleData>();
        db.put("TEA", new SimpleData("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
        db.put("POP", new SimpleData("POP", StockType.COMMON, 8.0, 0.0, 100.0));
        db.put("ALE", new SimpleData("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
        db.put("GIN", new SimpleData("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
        db.put("JOE", new SimpleData("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
        
        for (SimpleData stock: db.values()) {
            // Record some trades
        	for (int i=1; i <= 10; i++) {
        		stock.buy(1, 1.0);
        		stock.sell(1, 1.0);
        	}
        }
        Double GBCEallShareIndex = GBCE.allShareIndex(db);
        assertEquals(1.379729661461215, GBCEallShareIndex, 0.0);
	}

}
