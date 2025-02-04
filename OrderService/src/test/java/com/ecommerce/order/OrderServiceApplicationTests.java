package com.ecommerce.order;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.order.service.BillService;
import com.itextpdf.text.DocumentException;

@SpringBootTest
class OrderServiceApplicationTests {

	  
		@Autowired
	    private BillService billService;
		
		
		@Test
		public void getPdf() throws FileNotFoundException, DocumentException {
			 Map<String, Object> requestMap = new HashMap<>();
		        requestMap.put("username", "xyz");
		        
		        byte[] pdf = billService.getPdf(requestMap);
		}
}
