package com.demo.model;
import com.demo.model.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin ( origins="http://localhost:4000" )
public class PaymentGatewayController {
   
	@Autowired
    private StripeClient stripeClient;
   
    PaymentGatewayController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }
    
    @PostMapping("/charge")
    public Charge chargeCard(@RequestHeader(value="token") String token, @RequestHeader(value="amount") double amount) throws Exception {
       System.out.println("hhhhhhhhhhhhhh");
    	return this.stripeClient.chargeNewCard(token, amount);
    }
}