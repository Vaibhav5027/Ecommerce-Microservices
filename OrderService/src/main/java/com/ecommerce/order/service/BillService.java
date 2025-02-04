package com.ecommerce.order.service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.ecommerce.order.model.Bill;
import com.itextpdf.text.DocumentException;

public interface BillService {
   
	String generateBillNumber(String username);
	//Bill createBill(List<Long> productIds, List<Integer> quantities, Double totalAmount, LocalDateTime timestamp, String customerName, String paymentMethod, Double discountAmount);
  
	Bill createBill(Integer id,Integer quantity);
    Bill getBillById(Long billId);

    List<Bill> getAllBills();


    List<Bill> getBillsByTimestamp(LocalDateTime startTime, LocalDateTime endTime);

    Bill updateBill(Long billId, Integer tableNumber, List<Long> productIds, List<Integer> quantities, Double totalAmount, LocalDateTime timestamp, String customerName, Long waiterId, String paymentMethod, Double discountAmount, Double taxAmount);

    void deleteBill(Long billId);

    Double calculateTotal(List<Long> productIds, List<Integer> quantities); // Assuming product service provides prices

    // Define the format of the bill report (e.g., as a String, a DTO, etc.)
    Object generateBillReport(LocalDateTime startTime, LocalDateTime endTime); 
    
    byte[] getPdf(Map<String,Object> requestMap) throws FileNotFoundException, DocumentException;


    
   
	
}
