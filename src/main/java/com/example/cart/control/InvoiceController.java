package com.example.cart.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.IOUtils;
import com.example.cart.model.Cart;
import com.example.cart.model.ChargeRequest;
import com.example.cart.model.Invoice;
import com.example.cart.model.Item;
import com.example.cart.model.OrderDetail;
import com.example.cart.model.User;
import com.example.cart.pdfmailgenerator.MailGenerator;
import com.example.cart.pdfmailgenerator.PdfGenerator;
import com.example.cart.pdfmailgenerator.SMSService;
import com.example.cart.model.ChargeRequest.Currency;
import com.example.cart.service.CartService;
import com.example.cart.service.InvoiceService;
import com.example.cart.service.ItemService;
import com.example.cart.service.PaymentService;
import com.example.cart.service.StorageService;
import com.example.cart.service.StripeService;
import com.example.cart.service.UserService;
import com.itextpdf.text.DocumentException;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.mock.web.MockMultipartFile;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

	private static final long serialVersionUID = 1L;

	public static final String SUCCESS_URL = "invoice/success";
	public static final String CANCEL_URL = "invoice/success";

	@Value("${STRIPE_PUBLIC_KEY}")
	private String stripePublicKey;

	@Autowired
	private StripeService paymentsService;

	public StripeService getPaymentsService() {
		return paymentsService;
	}

	public void setPaymentsService(StripeService paymentsService) {
		this.paymentsService = paymentsService;
	}
	
	@Autowired
	private ItemService itemService;
	
	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

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

	@Autowired
	private CartService cartService;

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	@Autowired
	private StorageService storageService;

	public StorageService getStorageService() {
		return storageService;
	}

	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}

	@RequestMapping(value = "success", method = RequestMethod.GET)
	public String successPayment() {
		return "success";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addInvoice() {
		Item item1 = new Item("item1", 500, "url1", 2, "A");
		Item item2 = new Item("item2", 600, "url2", 2, "B");
		User user = userService.getUser(1);
		List<Item> itemList = new ArrayList<>();
		itemList.add(item1);
		itemList.add(item2);
		Invoice invoice = new Invoice();
		Date date = new Date(System.currentTimeMillis());
		invoice.setDate(date);
		user.addInvoice(invoice);
		userService.updateUser(user);
		invoiceService.saveInvoiceAndItems(invoice, itemList);
		return "welcome";
	}

	@RequestMapping(value = "charge", method = RequestMethod.POST)
	public String authorize_payment(ChargeRequest chargeRequest, HttpServletRequest theRequest,
			HttpServletResponse theResponse, Model model) throws StripeException {

		User user = (User) theRequest.getSession().getAttribute("CurrentUser");
		
		List<Cart> cartItems = cartService.getItemsByUserId(user.getId());

		System.out.println(1);

		System.out.println(cartItems.toString());

		List<Item> products = new ArrayList<>();

		Iterator<Cart> itr = cartItems.iterator();

		int sum = 0;

		System.out.println(2);

		while (itr.hasNext()) {

			Cart cart = itr.next();
			
			sum = sum + cart.getItem_totalamount();

		for(int i=0;i<cart.getItem_quantity();i++) {
			System.out.println(cart.toString());
			System.out.println("inside loop");
			System.out.println(cart.getItem_totalamount());
			System.out.println(cart.toString());
			System.out.println(sum);
			int newQuantity = cart.getItem_total_quantity() - cart.getItem_quantity();
			Item item = new Item(cart.getItem_name(), cart.getItem_price(), cart.getImage_url(),
					newQuantity, cart.getCategory());
			System.out.println(item.toString());
			item.setId(cart.getItem_id());
			itemService.updateItem(item);
			System.out.println(item.toString());
			products.add(item);
			System.out.println(products.toString());
			}

		}

		System.out.println(3);

//		User user = (User) theRequest.getSession().getAttribute("CurrentUser");

		float tax = (sum * 30) / 100;

		float totalCost = sum + tax + 50;

		System.out.println(4);

		int shippingCost = 50;

		System.out.println(5);

		Date date = new Date(System.currentTimeMillis());

		int total = (int) totalCost;

		int finalAmount = (int) (totalCost * 100);

		Invoice invoice = new Invoice(date, sum, shippingCost, (int) tax, total);

		user.addInvoice(invoice);

		userService.updateUser(user);

		System.out.println(6);

		OrderDetail orderDetail = new OrderDetail(products, "" + sum, "" + shippingCost, "" + tax, "" + totalCost);

		Invoice generatedInvoice = invoiceService.saveInvoiceAndItems(invoice, products);

		System.out.println(7);

		chargeRequest.setDescription("Example charge");
		chargeRequest.setCurrency(Currency.INR);

		System.out.println("Order Detail amount === " + orderDetail.getTotal());

		chargeRequest.setAmount("" + finalAmount);
		Charge charge = paymentsService.charge(chargeRequest);
		model.addAttribute("id", charge.getId());
		model.addAttribute("status", charge.getStatus());
		model.addAttribute("chargeId", charge.getId());
		model.addAttribute("balance_transaction", charge.getBalanceTransaction());

		PdfGenerator pdfGenerator = new PdfGenerator();

		MailGenerator mailGenerator = new MailGenerator();

		SMSService smsService = new SMSService();

		try {
			String pdfResult = pdfGenerator.getPDF(invoice, user, cartItems);

			if (pdfResult == "success") {
				File file = new File(
						"/Users/mahadevan/Documents/shopping-cart-project-pdf/Invoice" + invoice.getId() + ".pdf");
				FileInputStream input = new FileInputStream(file);
				MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
						IOUtils.toByteArray(input));
				storageService.uploadFile(multipartFile);
				if (mailGenerator.generateMail(user, invoice)) {
					smsService.sendSMS(user, invoice);
					cartService.deleteItemsByUserId(user.getId());
				}
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "result";
	}

	@RequestMapping(value = "myorders", method = RequestMethod.GET)
	public String myorders(HttpServletRequest theRequest) {

		User user = (User) theRequest.getSession().getAttribute("CurrentUser");

		List<Invoice> invoiceList = userService.getInvoices(user.getId());

		System.out.println(invoiceList.toString());
		
		Map<Integer,Map<String,Integer>> hashm = new HashMap<Integer,Map<String,Integer>>();
		
		Iterator<Invoice> invoiceIterator = invoiceList.iterator();
		
		while(invoiceIterator.hasNext()) {
			
			Invoice invoiceItem = invoiceIterator.next();
			
			Map<String,Integer> itemMap = new HashMap<>();
			
			List<Item> itemsList = invoiceItem.getItems();
			
			for (Item i : itemsList) {
	            Integer j = itemMap.get(i.getItem_name());
	            itemMap.put(i.getItem_name(), (j == null) ? 1 : j + 1);
	        }
			
			hashm.put(invoiceItem.getId(), itemMap);
		}
		
		Iterator<Invoice> invoiceIterator2 = invoiceList.iterator();
		
		while(invoiceIterator2.hasNext()) {
			System.out.println(hashm.get(invoiceIterator2.next().getId()));
		}
		
		theRequest.setAttribute("invoiceList", invoiceList);
		
		theRequest.setAttribute("invoiceItems", hashm);

		return "myorders";
	}

	@RequestMapping(value = "getPdf/{id}", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> getPdf(@PathVariable(value = "id") int id) throws MalformedURLException {

		String fileName = "Invoice"+id+".pdf";
		
		byte[] data = storageService.downloadFile(fileName);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity
					.ok()
					.contentLength(data.length)
					.header("Content-type", "application/octet-stream")
					.header("Content-disposition", "attachment; filename=\""+fileName+"\"")
					.body(resource);

	}

	@ExceptionHandler(StripeException.class)
	public String handleError(Model model, StripeException ex) {
		model.addAttribute("error", ex.getMessage());
		return "result";
	}
}
