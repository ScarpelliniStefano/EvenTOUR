package com.scarcolo.eventour.service.booking;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.Order;

// TODO: Auto-generated Javadoc
/**
 * The Class PaymentServiceImpl.
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

  /** The Constant TEST_STRIPE_SECRET_KEY. */
  private static final String TEST_STRIPE_SECRET_KEY = "sk_test_51K2dpcKiyOVupiQ2eQxIxl3vv8lotwkIHY9kP4Z2aBcQYKDXQTKIQskbt50JjdvIFfkTtmnO0Xw4hpZcrq0T5i1p00xB2HSQwC";
  
  /**
   * Instantiates a new payment service implementation.
   */
  public PaymentServiceImpl() {
    Stripe.apiKey = TEST_STRIPE_SECRET_KEY;
  }
  
  /**
   * Creates the customer.
   *
   * @param descr the description of transaction
   * @param name the name of user
   * @param surname the surname of user
   * @param mail the mail of user
   * @return the customer
   */
  public Customer createCustomer(String descr,String name,String surname,String mail) {
	
	  Map<String, Object> customerMap = new HashMap<String, Object>();
      customerMap.put("description", "payment");
      customerMap.put("email", "test@example.com");
      customerMap.put("payment_method", "pm_card_visa"); // obtained via Stripe.js

      try {
          Customer customer = Customer.create(customerMap);
          return customer;
      } catch (StripeException e) {
          e.printStackTrace();
      }
      return null;
    	
  }

  /**
   * Charge credit card.
   *
   * @param order the order
   * @param cardNr the card number
   * @param month the month
   * @param year the year
   * @param cvv the cvv
   * @return the string
   * @throws NoSuchAlgorithmException the no such algorithm exception
   */
  public String chargeCreditCard(Order order,String cardNr,String month,String year,String cvv) throws NoSuchAlgorithmException {
	  if(cardNr.startsWith("400000380000")&&cvv.contains("2")) {
				MessageDigest md = MessageDigest.getInstance("MD5");
				String stringa=""+cardNr+month+year+cvv+Math.floor(Math.random()*1000);
				if(Charset.isSupported("CP1252"))
					md.update(stringa.getBytes(Charset.forName("CP1252")));
				else
					md.update(stringa.getBytes(Charset.forName("ISO-8859-1")));

				byte[]bytes = md.digest();
				StringBuilder str = new StringBuilder();
				for(int i = 0; i < bytes.length; i++)
					str.append(Integer.toHexString( ( bytes[i] & 0xFF ) | 0x100 ).substring(1, 3));
				
		  return str.toString();
	  }else {
		  return "cb5e100e5a9a3e7f6d1fd97512215282"; //hash of "error" string
	  }

	/*		STRIP PROPS AND SETTINGS FOR A REAL PAYMENT
    // Stripe requires the charge amount to be in cents
    int chargeAmountCents = (int) order.getAmount().doubleValue() * 100;

    Customer user = order.getCustomerObject();
    System.out.println(user);
	Map<String, Object> chargeParams = new HashMap<String, Object>();
	chargeParams.put("amount", chargeAmountCents);
	chargeParams.put("currency", "eur");
	chargeParams.put("description", "amount");		
	chargeParams.put("customer", user.getId());
	PaymentMethodDetails pmd=new PaymentMethodDetails();
	Map<String, Object> map = new HashMap<>();
	map.put("number", cardNr);
	map.put("exp_month", month);
	map.put("exp_year", year);
	map.put("cvc", cvv);
	Map<String, Object> cardMap = new HashMap<>();
	cardMap.put("card", map);
	
			
	try {
	  // Submit charge to credit card 
		Token token = Token.create(cardMap);
		Customer cust = user;
		Map<String, Object> source = new HashMap<>();
		source.put("source", token.getId());
		cust.getSources().create(source);
		Map<String, Object> mapC = new HashMap<>();
		map.put("amount", chargeAmountCents);
		map.put("currency", "eur");
		map.put("customer", cust.getId());
		Charge charge = Charge.create(mapC);
		System.out.println(charge);
		return charge;
    } catch (CardException e) {
	  // Transaction was declined
	  System.out.println("Status is: " + e.getCode());
	  System.out.println("Message is: " + e.getMessage());
	} catch (StripeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	return null;*/
	  
  }
 
}


