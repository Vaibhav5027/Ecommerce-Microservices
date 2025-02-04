package com.ecommerce.order.serviceimpl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.client.ProductClient;
import com.ecommerce.order.dto.Product;
import com.ecommerce.order.model.Bill;
import com.ecommerce.order.service.BillService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class BillServiceImpl implements BillService{
    private final String location="D://bills";
	
	@Autowired
	private ProductClient client;
	@Override
	public String generateBillNumber(String username) {
		return null;
	}

	@Override
	public Bill createBill(Integer id, Integer quantity) {
	Product product = client.getProduct(id);
		return null;
	}

	@Override
	public Bill getBillById(Long billId) {
		return null;
	}

	@Override
	public List<Bill> getAllBills() {
		return null;
	}

	@Override
	public List<Bill> getBillsByTimestamp(LocalDateTime startTime, LocalDateTime endTime) {
		return null;
	}

	@Override
	public Bill updateBill(Long billId, Integer tableNumber, List<Long> productIds, List<Integer> quantities,
			Double totalAmount, LocalDateTime timestamp, String customerName, Long waiterId, String paymentMethod,
			Double discountAmount, Double taxAmount) {
		return null;
	}

	@Override
	public void deleteBill(Long billId) {
		
	}

	@Override
	public Double calculateTotal(List<Long> productIds, List<Integer> quantities) {
		return null;
	}

	@Override
	public Object generateBillReport(LocalDateTime startTime, LocalDateTime endTime) {
		return null;
	}

	@Override
	public byte[] getPdf(Map<String, Object> requestMap) throws FileNotFoundException, DocumentException {
//		 String filename=generateFileName(requestMap.get("username"));
		
		String data = "Name: " + requestMap.get("name") + "\n" + "ContactNumber: "
				+ requestMap.get("contactNumber") + "\n" + "Email: " + requestMap.get("email") + "\n"
				+ "PaymentMode:" + requestMap.get("paymentMode");
		String filename=generateFileName("xyz");
		Document document=new Document();
		String storeLocation=this.location;
		PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(storeLocation+"//"+filename + ".pdf"));
		document.open();
		setRectanglepPdf(document);
		Paragraph chunk = new Paragraph("Cafe Management System", getFont("Header"));
         chunk.setAlignment(Element.ALIGN_CENTER);
         document.add(chunk);
         Paragraph para=new Paragraph(data +"\n \n",getFont("data"));
         document.add(para);
         
        PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			AddTableHeader(table);
		 document.close();
		return null;
	}

	private void AddTableHeader(PdfPTable table) {
		Stream.of("Name", "Category", "Quantity", "Price", "Sub-total").forEach(columTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columTitle));
			header.setBackgroundColor(BaseColor.YELLOW);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(header);
		});
		
	}

	private void setRectanglepPdf(Document document) throws DocumentException {
		Rectangle rect = new Rectangle(577, 825, 18, 15);
		rect.enableBorderSide(1);
		rect.enableBorderSide(2);
		rect.enableBorderSide(4);
		rect.enableBorderSide(8);
		rect.setBorderColor(BaseColor.BLACK);
		rect.setBorderWidth(1);
		document.add(rect);
		
	}

	private String generateFileName(String string) {
		    
		return "bill"+string +UUID.randomUUID().toString().substring(0, 8);
	}

	private Font getFont(String type) {
		switch (type) {
		case "Header": {
			Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
			fontHeader.setStyle(Font.BOLD);
			return fontHeader;
		}

		case "Data": {
			Font fontData = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
			fontData.setStyle(Font.BOLD);
			return fontData;
		}
		default:
			return new Font();
		}

	}


}
