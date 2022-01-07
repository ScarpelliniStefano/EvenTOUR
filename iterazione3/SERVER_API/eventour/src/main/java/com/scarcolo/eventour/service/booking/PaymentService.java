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
   * @param descr the description of transaction
   * @param name the name of user
   * @param surname the surname of user
   * @param mail the mail of user
   * @return the customer
   */
  public Customer createCustomer(String descr,String name,String surname,String mail);
  
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
  public String chargeCreditCard(Order order,String cardNr,String month,String year,String cvv) throws NoSuchAlgorithmException;
}