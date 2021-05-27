package com.example.cart.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cart.model.ChargeRequest;
import com.example.cart.model.ChargeRequest.Currency;
import com.example.cart.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Controller
public class CheckoutController {

	 	@Autowired
	    private StripeService paymentsService;

	    @PostMapping("/charge")
	    public String charge(ChargeRequest chargeRequest, Model model)
	      throws StripeException {
	        chargeRequest.setDescription("Example charge");
	        chargeRequest.setCurrency(Currency.EUR);
	        Charge charge = paymentsService.charge(chargeRequest);
	        model.addAttribute("id", charge.getId());
	        model.addAttribute("status", charge.getStatus());
	        model.addAttribute("chargeId", charge.getId());
	        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
	        return "result";
	    }

	    @ExceptionHandler(StripeException.class)
	    public String handleError(Model model, StripeException ex) {
	        model.addAttribute("error", ex.getMessage());
	        return "result";
	    }
}