package com.scarcolo.eventour.service.booking;

import java.security.NoSuchAlgorithmException;

import com.stripe.model.Customer;
import com.stripe.model.Order;

// TODO: Auto-generated Javadoc
/**
 * The Interface PaymentService.
 */
public interface PaymentService {
  
  /**
   * Creates the customer.
   *
   * @param descr the descr
   * @param name the name
   * @param surname the surname
   * @param mail the mail
   * @return the customer
   */
  public Customer createCustomer(String descr,String name,String surname,String mail);
  
  /**
   * Charge credit card.
   *
   * @param order the order
   * @param cardNr the card nr
   * @param month the month
   * @param year the year
   * @param cvv the cvv
   * @return the string
   * @throws NoSuchAlgorithmException the no such algorithm exception
   */
  public String chargeCreditCard(Order order,String cardNr,String month,String year,String cvv) throws NoSuchAlgorithmException;
}