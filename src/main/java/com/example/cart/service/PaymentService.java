package com.example.cart.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.cart.model.Cart;
import com.example.cart.model.OrderDetail;
import com.paypal.api.payments.*;
import com.paypal.base.rest.*;

@Service
public class PaymentService {
//	private static final String CLIENT_ID = "ARTzz-GfqA911QKM7W5QRLXFqG1QBh7c1GBIixSMUOnlVpDa2uC5UDvUCkaEdD0dHhrRIq-HIY-h_HyR";
//    private static final String CLIENT_SECRET = "EGD9V2oCBm7w7XiuK8GkkokT79WHrRHzfFDWIbSUiRQ9IEWxqSuqa4zx54bPj8EvU_xY-qCIy2YjvQxU";
//    private static final String MODE = "live";
    
    private static final String CLIENT_ID = "AQorWhI_YW3Fa9MpP2xV7GkvSzLHWI_JrRd8hZO8sDXl1QmS-GDf99dNvitWJJY1vivEuR3-4hbuTJVf";
    private static final String CLIENT_SECRET = "EDrSTxle3uROkcKyf_85h0ARhu5_C8mtbnpzwQncu1DdXbLNG8oCNM8nuP8ec696yhS3x7tO2c2GoDVX";
   private static final String MODE = "sandbox";
 
    private String cart_quantity;
    
    public String authorizePayment(OrderDetail orderDetail, int quantity)        
            throws PayPalRESTException {       
 
    	this.cart_quantity = ""+quantity;
    	
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);
         
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");
 
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
 
        Payment approvedPayment = requestPayment.create(apiContext);
 
        return getApprovalLink(approvedPayment);
 
    }
    
    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
         
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("William")
                 .setLastName("Peterson")
                 .setEmail("william@perterson@gmail.com");
         
        payer.setPayerInfo(payerInfo);
         
        return payer;
    }
    
    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8000/item/dashboard");
        redirectUrls.setReturnUrl("http://localhost:8000/item/dashboard");
         
        return redirectUrls;
    }
    
    private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {
        Details details = new Details();
//        details.setShipping("0");
//        details.setSubtotal("0");
//        details.setTax("0");
        
        System.out.println(orderDetail.getShipping());
        System.out.println(orderDetail.getSubtotal());
        System.out.println(orderDetail.getTax());
        System.out.println(orderDetail.getTotal());
     
        Amount amount = new Amount();
        amount.setCurrency("INR");
        amount.setTotal("1");
        amount.setDetails(details);
     
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Shopping_cart_items");
         
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
         
        Item item = new Item();
        item.setCurrency("INR");
        item.setName("Shopping_Cart_Items");
        item.setPrice("1");
        item.setTax("0");
        item.setQuantity("1");
         
        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);
     
        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);  
         
        return listTransaction;
    }
    
    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
         
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }      
         
        return approvalLink;
    }
    
    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }
    
    public Payment executePayment(String paymentId, String payerId)
            throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
     
        Payment payment = new Payment().setId(paymentId);
     
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
     
        return payment.execute(apiContext, paymentExecution);
    }
}
