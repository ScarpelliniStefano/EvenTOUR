package com.scarcolo.eventour.service.booking;

import java.security.NoSuchAlgorithmException;

import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Order;

public interface PaymentService {
  public Customer createCustomer(String descr,String name,String surname,String mail);
  public String chargeCreditCard(Order order,String cardNr,String month,String year,String cvv) throws NoSuchAlgorithmException;
}