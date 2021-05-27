package com.example.cart.pdfmailgenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.dao.UserDAOImpl;
import com.example.cart.model.Cart;
import com.example.cart.model.Invoice;
import com.example.cart.model.Item;
import com.example.cart.model.User;
import com.example.cart.service.InvoiceService;
import com.example.cart.service.UserService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.bytebuddy.asm.Advice.This;

@Service
public class PdfGenerator {
	
	@Autowired
	private InvoiceService invoiceService;

	public InvoiceService getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	
	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getPDF(Invoice invoice,User user,List<Cart> cartItems) throws RemoteException,IOException, TransformerException,TransformerConfigurationException,FileNotFoundException, DocumentException {
		// TODO Auto-generated method stub
//		Date date = new Date();
//		Invoice invoice = invoiceService.getInvoice(invoiceId);
//		Set<Item> itemList = invoice.getItems();
////		OrderService ordService = new OrderService(date,500,2000);
//		ordService.setItems(itemList);
//		UserDAOImpl userDAOimp = UserDAOImpl.getUserDaoImpl();
//		UserDTO user = userDAOimp.findByName(name);
//		ordService.setUserDTO(user);
//		InvoiceDTO invoiceDetails = (InvoiceDTO) session.getAttribute("InvoiceDetails");
//		System.out.println("Invoice Details ===== "+invoiceDetails.getInvoiceid());
//		ItemMasterDAOImpl itemMasterDAO = ItemMasterDAOImpl.getItemMasterDAO();
		
		System.out.println("Generating PDF");
		
		List<Item> itemList = invoice.getItems();
		return createPDF(invoice,itemList,user,cartItems);
	}
	
	

//	public void getExcel() throws IOException {
//		// TODO Auto-generated method stub
//		Date date = new Date();
//		HttpSession session=request.getSession();
//		List<Item> itemList = (List<Item>) session.getAttribute("itemList");
//		OrderService ordService = new OrderService(date,500,2000);
//		ordService.setItems(itemList);
//		String uname = (String) session.getAttribute("uname");
//		UserDAOImpl userDAOimp = UserDAOImpl.getUserDaoImpl();
//		UserDTO user = userDAOimp.findByName(uname);
//		ordService.setUserDTO(user);
//		InvoiceDTO invoiceDetails = (InvoiceDTO) session.getAttribute("InvoiceDetails");
//		ItemMasterDAOImpl itemMasterDAO = ItemMasterDAOImpl.getItemMasterDAO();
//		List<Item> itemList2 = itemMasterDAO.findByInvoiceID(invoiceDetails.getInvoiceid());
//		try {
//			printExcel(invoiceDetails,itemList2);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		System.out.println("Hello there");
//	}

//	public boolean generatePdfAndExcel() {
//		// TODO Auto-generated method stub
//		try {
//			getPDF(0, null);
//			return true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return false;
//		} 
//	}
	
	
	public static String createPDF (Invoice invoiceDTO,List<Item> invoiceItemList,User user,List<Cart> cartItems){
		Invoice orderdet = invoiceDTO;
//	for(OrderService orderdet:OrderService) {
		String pdfFilename = "/Users/mahadevan/Documents/shopping-cart-project-pdf/Invoice"+invoiceDTO.getId()+".pdf";
		try {

			OutputStream file = new FileOutputStream(new File(pdfFilename));
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();
			//Inserting Image in PDF
			Image image = Image.getInstance ("/Users/mahadevan/Documents/shopping-cart-project/shopping-cart/src/main/resources/static/images/shopping-cart.jpeg");//Header Image
			image.scaleAbsolute(64f, 72f);//image width,height 
			document.add(image);

			PdfPTable irdTable = new PdfPTable(2);
			irdTable.addCell(getIRDCell("Invoice No"));
			irdTable.addCell(getIRDCell("Invoice Date"));
			irdTable.addCell(getIRDCell(""+orderdet.getId()+"")); // pass invoice number
			irdTable.addCell(getIRDCell(""+orderdet.getDate().toString()+"")); // pass invoice date				


			PdfPTable irhTable = new PdfPTable(3);
			irhTable.setWidthPercentage(100);


			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("Invoice", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			PdfPCell invoiceTable = new PdfPCell (irdTable);
			invoiceTable.setBorder(0);
			irhTable.addCell(invoiceTable);
			document.add(irhTable);

			FontSelector fs = new FontSelector();
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD);
			fs.addFont(font);

//			CompanyDetails booking_details = orderdet.getCompanyDetails();
			
			Paragraph name = new Paragraph("Shop Cart");
			name.setIndentationLeft(5);
			Paragraph contact = new Paragraph("Shop Cart street address");
			contact.setIndentationLeft(5);
			Paragraph address = new Paragraph("600002");
			address.setIndentationLeft(5);
			Paragraph phone_number = new Paragraph("1234567890");
			phone_number.setIndentationLeft(5);
			Paragraph email_address = new Paragraph("shopcart@gmail.com");
			email_address.setIndentationLeft(5);

			document.add(name);
			document.add(contact);
			document.add(address);		
			document.add(phone_number);
			document.add(email_address);


			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			//			document.add(bill);
			
			
//			BillingDetails billing_details = orderdet.getBillingDetails();
//			
//			ShippingDetails shipping_details = orderdet.getShippingDetails();

			PdfPTable addresstablehead = new PdfPTable(2);
			addresstablehead.setWidthPercentage(100);
			addresstablehead.addCell(getPhrase("BILL TO",Element.ALIGN_LEFT));
			addresstablehead.addCell(getPhrase("SHIP TO",Element.ALIGN_LEFT));
			document.add(addresstablehead);
			document.add(new Paragraph(" "));
			PdfPTable addresstable = new PdfPTable(2);
			addresstable.setWidthPercentage(100);
			addresstable.addCell(getCell(""+user.getUsername()+"", PdfPCell.ALIGN_LEFT));
			addresstable.addCell(getCell(""+user.getUsername()+"", PdfPCell.ALIGN_LEFT));
			addresstable.addCell(getCell(""+user.getAddress1()+"", PdfPCell.ALIGN_LEFT));
			addresstable.addCell(getCell(""+user.getAddress1()+"", PdfPCell.ALIGN_LEFT));
			addresstable.addCell(getCell(""+user.getAddress2()+"", PdfPCell.ALIGN_LEFT));
			addresstable.addCell(getCell(""+user.getAddress2()+"", PdfPCell.ALIGN_LEFT));
			addresstable.addCell(getCell(""+user.getPhonenumber()+"", PdfPCell.ALIGN_LEFT));
			addresstable.addCell(getCell(""+user.getPhonenumber()+"", PdfPCell.ALIGN_LEFT));
			addresstable.addCell(getCell(""+user.getEmail()+"", PdfPCell.ALIGN_LEFT));
			document.add(addresstable);

			PdfPTable billTable = new PdfPTable(4); //one page contains 15 records 
			billTable.setWidthPercentage(100);
			//			billTable.setWidths(new float[] { 1, 2,5,2,1,2 });
			billTable.setWidths(new float[] { 1, 6,4,4 });
			billTable.setSpacingBefore(30.0f);
			billTable.addCell(getBillHeaderCell("Id"));
			billTable.addCell(getBillHeaderCell("Name"));
			billTable.addCell(getBillHeaderCell("Quantity"));
			billTable.addCell(getBillHeaderCell("Total"));
			
//			int total = 0;
//			
//			for(Item item:itemList) {
//				total = (int) (total + item.getPrice());
//			}
			
			for(Cart cartItem:cartItems) {
				
				billTable.addCell(getBillRowCell(""+cartItem.getId()+""));
				billTable.addCell(getBillRowCell(""+cartItem.getItem_name()+""));
				billTable.addCell(getBillRowCell(""+cartItem.getItem_quantity()+""));
				billTable.addCell(getBillRowCell(""+cartItem.getItem_totalamount()+""));
				
			}

			PdfPTable validity = new PdfPTable(1);
			validity.setWidthPercentage(100);
			validity.addCell(getValidityCell(" "));
			validity.addCell(getValidityCell(" "));
			validity.addCell(getValidityCell(" "));
			validity.addCell(getValidityCell(" "));		    
			PdfPCell summaryL = new PdfPCell (validity);
			summaryL.setColspan (3);
			summaryL.setPadding (1.0f);	        
			summaryL.setBorder(0);
			billTable.addCell(summaryL);
			
//			OrderedItems orderedItems = orderdet.getOrderedItems();
			PdfPTable accounts = new PdfPTable(2);
			accounts.setWidthPercentage(100);
//			accounts.addCell(getAccountsCell("Discount (10%)"));
//			accounts.addCell(getAccountsCellR(""+orderedItems.tax+""));
			accounts.addCell(getAccountsCell("Subtotal"));
			accounts.addCell(getAccountsCellR(""+orderdet.getSubtotal()+""));
			accounts.addCell(getAccountsCell("Tax"));
			accounts.addCell(getAccountsCellR("30%"));		
			accounts.addCell(getAccountsCell("Total Tax"));
			accounts.addCell(getAccountsCellR(""+orderdet.getTax()+""));	
			accounts.addCell(getAccountsCell("Shipping / Handling"));
			accounts.addCell(getAccountsCellR(""+orderdet.getShipping()+""));	
			accounts.addCell(getAccountsCell("Total"));
			accounts.addCell(getAccountsCellR(""+orderdet.getTotal()+""));
//			accounts.addCell(getAccountsCell("Balance"));
//			accounts.addCell(getAccountsCellR(""+orderdet.getBalance()+""));	
			PdfPCell summaryR = new PdfPCell (accounts);
			summaryR.setColspan (3);         
			billTable.addCell(summaryR);  	

			document.add(billTable);

			document.close();

			file.close();

			System.out.println("Pdf created successfully..");
			
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public static String printExcel(Invoice invoiceDTO,List<Item> invoiceItemList) throws IOException {
//		int rowCount = 0;
//		String excelFilePath = "/Users/mahadevan/eclipse-workspace/ShoppingCart4.0/WebContent/resources/Invoice.xls";
//		FileInputStream inputStream2 = new FileInputStream(new File(excelFilePath));
//		HSSFWorkbook workbook2 = new HSSFWorkbook(inputStream2);
//
//		int index;
//		HSSFSheet newSheet3 = workbook2.getSheet("InvoiceDetails");
//		if(newSheet3 != null) {
//			index = workbook2.getSheetIndex(newSheet3);
//			workbook2.removeSheetAt(index);
//		}
//
//		HSSFSheet newSheet = workbook2.createSheet("InvoiceDetails");
//
//		HSSFSheet newSheet2 = workbook2.getSheet("Sheet1");
//		if(newSheet2 !=  null) {
//			index = workbook2.getSheetIndex(newSheet2);
//			workbook2.removeSheetAt(index);
//		}
//		
//		int i = 0;
////		for(OrderDetails orderdet:orderDetails) {	
//			
//			
//			Object[][] OrderHead = {
//					{"Order No "+(++i)},
//			};
//			
//			for (Object[] aBook : OrderHead) {
//				Row row = newSheet.createRow(rowCount++);
//
//				int columnCount = 0;
//
//				for (Object field : aBook) {
//					Cell cell = row.createCell(columnCount++);
//					if (field instanceof String) {
//						cell.setCellValue((String) field);
//					} else if (field instanceof Integer) {
//						cell.setCellValue((Integer) field);
//					}
//				}
//
//			} 
//			rowCount++;
//			
////			CompanyDetails booking_details = orderdet.getCompanyDetails();
//			
//			Object[][] bookComments = {
//					{"Company Name", "Shop Cart"},
//					{"Street Address", "Shop Cart street address"},
//					{"City, State and Zip Code","600002"},
//					{"Phone number", "1234567890"},
//					{"Email address","shopcart@gmail.com"}
//			};
//
//			for (Object[] aBook : bookComments) {
//				Row row = newSheet.createRow(rowCount++);
//
//				int columnCount = 0;
//
//				for (Object field : aBook) {
//					Cell cell = row.createCell(columnCount++);
//					if (field instanceof String) {
//						cell.setCellValue((String) field);
//					} else if (field instanceof Integer) {
//						cell.setCellValue((Integer) field);
//					}
//				}
//
//			}       
//			rowCount++;
//			
////			BillingDetails billing_details = orderdet.getBillingDetails();
//			
////			UserDTO user = invoiceDTO.getUser();
//			UserDAOImpl userDAO = UserDAOImpl.getUserDaoImpl();
//			UserDTO user = userDAO.findById(invoiceDTO.getCustomer_id());
//
//			Object[][] billingDetails = {
//					{"Billing Details"},
//					{"Contact Name", ""+user.getUsername()+""},
//					{"Client Company Name", "Company Name"},
//					{"Address", "Billing Address"},
//					{"Phone", "1112223333"},
//					{"Email",""+user.getUsername()+"@email.com"}
//			};
//
//			for (Object[] aBook : billingDetails) {
//				Row row = newSheet.createRow(rowCount++);
//
//				int columnCount = 0;
//
//				for (Object field : aBook) {
//					Cell cell = row.createCell(columnCount++);
//					if (field instanceof String) {
//						cell.setCellValue((String) field);
//					} else if (field instanceof Integer) {
//						cell.setCellValue((Integer) field);
//					}
//				}
//
//			}  
//
//			rowCount++;
//			
////			ShippingDetails shipping_details = orderdet.getShippingDetails();
//			
////			UserDTO user2 = invoiceDTO.getUser();
////			UserDAOImpl userDAO2 = UserDAOImpl.getUserDaoImpl();
//			UserDTO user2 = userDAO.findById(invoiceDTO.getCustomer_id());
//
//			Object[][] shippingDetails = {
//					{"Shipping Details"},
//					{"Contact Name/ Dept", ""+user2.getUsername()+""},
//					{"Client Company Name","Company Name"},
//					{"Address", "Shipping Address"},
//					{"Phone", "234242342"}
//			};
//
//			for (Object[] aBook : shippingDetails) {
//				Row row = newSheet.createRow(rowCount++);
//
//				int columnCount = 0;
//
//				for (Object field : aBook) {
//					Cell cell = row.createCell(columnCount++);
//					if (field instanceof String) {
//						cell.setCellValue((String) field);
//					} else if (field instanceof Integer) {
//						cell.setCellValue((Integer) field);
//					}
//				}
//
//			}
//			
//			rowCount++;
//			
//			Object[][] shoppingItemsHead = {
//					{"Description","QTY","Limit Price","Total"}
//			};
//
//			for (Object[] aBook : shoppingItemsHead) {
//				Row row = newSheet.createRow(rowCount++);
//
//				int columnCount = 0;
//
//				for (Object field : aBook) {
//					Cell cell = row.createCell(columnCount++);
//					if (field instanceof String) {
//						cell.setCellValue((String) field);
//					} else if (field instanceof Integer) {
//						cell.setCellValue((Integer) field);
//					}
//				}
//
//			}
//			ArrayList<Item> itemList = (ArrayList<Item>) invoiceItemList;
//
//			for(Item item:itemList) {
//				
//					Object[][] shopItems = {
//						{""+item.getId()+"",""+item.getItem_name()+"",""+item.getCategory()+"",""+item.getPrice()+""}
//					};
//					for (Object[] aBook : shopItems) {
//						Row row = newSheet.createRow(rowCount++);
//
//						int columnCount = 0;
//
//						for (Object field : aBook) {
//							Cell cell = row.createCell(columnCount++);
//							if (field instanceof String) {
//								cell.setCellValue((String) field);
//							} else if (field instanceof Integer) {
//								cell.setCellValue((Integer) field);
//							}
//						}
//
//					}
//			}
////			rowCount++;
//			
////			Object[][] shoppingItems = {
////					{"Description","QTY","Limit Price","Total"},
////					{"Toy", "2","50.00","45.00"},
////					{"Pen", "4","60.00","55.00"},
////					{"Pencil", "3","75.00","70.00"}
////			};
//
////			OrderedItems orderedItems = orderdet.getOrderedItems();
//			
////			System.out.println(orderdet.shipping_cost);
//			
//			int total = 0;
//			
//			for(Item item:itemList) {
//				total = (int) (total + item.getPrice());
//			}
//			
//			
//			rowCount++;
//			Object[][] totalAmount = {
//					{"Subtotal",total},
//					{"Tax rate","30%"},
//					{"Total tax","1000"},
//					{"Shipping / Handling","500"}
////					{"Balance Due",invoiceDTO.getBalance()}
//			};
//
//			for (Object[] aBook : totalAmount) {
//				Row row = newSheet.createRow(rowCount++);
//
//				int columnCount = 0;
//
//				for (Object field : aBook) {
//					Cell cell = row.createCell(columnCount++);
//					if (field instanceof String) {
//						cell.setCellValue((String) field);
//					} else if (field instanceof Integer) {
//						cell.setCellValue((Integer) field);
//					}
//				}
//
//			}
//			
////			rowCount = rowCount + 3;
//			
//			FileOutputStream outputStream = new FileOutputStream("/Users/mahadevan/eclipse-workspace/ShoppingCart4.0/WebContent/resources/Invoice.xls");
//			workbook2.write(outputStream);
//			workbook2.close();
//			outputStream.close();
//			
//			return "excel.success";
//        
//		}

	private static PdfPCell getIRHCell(String text, int alignment) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
		/*	font.setColor(BaseColor.GRAY);*/
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setPadding(5);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private static PdfPCell getIRDCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph(text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		return cell;
	}

	private static PdfPCell getBillHeaderCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
		font.setColor(BaseColor.BLACK);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setBackgroundColor(BaseColor.RED);
		return cell;
	}

	private static PdfPCell getBillRowCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setBorderWidthBottom(1f);
		cell.setBorderWidthTop(0);
		return cell;
	}

	private static PdfPCell getBillFooterCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthTop(0);
		return cell;
	}

	private static PdfPCell getValidityCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);		
		cell.setBorder(0);
		return cell;
	}

	private static PdfPCell getAccountsCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);		
		cell.setBorderWidthRight(0);
		cell.setBorderWidthTop(0);
		cell.setPadding (5.0f);
		return cell;
	}
	private static PdfPCell getAccountsCellR(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);		
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
		cell.setPadding (5.0f);
		cell.setPaddingRight(20.0f);
		return cell;
	}

	private static PdfPCell getdescCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);	
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setBorder(0);
		return cell;
	}

	private static PdfPCell getCell(String text, int alignment) {
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setPadding(3);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPaddingLeft(10);
		cell.setPaddingRight(10);
		return cell;
	}

	private static PdfPCell getPhrase(String text, int alignment) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD);
		fs.addFont(font);
		Phrase bill = fs.process(text); 
		PdfPCell cell = new PdfPCell(bill);
		cell.setPadding(0);
		//    cell.setPaddingLeft(-10);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}
	
}
