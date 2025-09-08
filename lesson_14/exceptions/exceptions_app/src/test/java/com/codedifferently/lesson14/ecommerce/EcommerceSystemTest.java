package com.codedifferently.lesson14.ecommerce;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codedifferently.lesson14.exceptions.EmptyCartException;
import com.codedifferently.lesson14.exceptions.InvalidCategoryException;
import com.codedifferently.lesson14.exceptions.InvalidProductException;
import com.codedifferently.lesson14.exceptions.NonRefundableProductException;
import com.codedifferently.lesson14.exceptions.OutOfStockException;
import com.codedifferently.lesson14.exceptions.PartialRefundNotAllowedException;
import com.codedifferently.lesson14.exceptions.SplitPaymentNotSupportedException;
import com.codedifferently.lesson14.exceptions.UnsupportedPaymentMethodException;
import com.codedifferently.lesson14.exceptions.UnsupportedShippingCountryException;

class EcommerceSystemTest {

  EcommerceSystem ecommerceSystem;

  @BeforeEach
  void setUp() {
    ecommerceSystem = new EcommerceSystem();
  }

  @Test
  void testAddProduct() {

    ecommerceSystem.addProduct("1", "Laptop");
  }

  @Test
  void testPlaceOrder() throws Exception {
    
    ecommerceSystem.addProduct("1", "Laptop", 5, "ELECTRONICS", true, 1000.0);
 
    String orderId = ecommerceSystem.placeOrder("1", 1);

  
    String actual = ecommerceSystem.checkOrderStatus(orderId);
    assertThat(actual).isEqualTo("Order ID: " + orderId + ", Product: Laptop, Quantity: 1");
  }

  @Test
  void testPlaceOrder_productDoesNotExist() throws Exception {
  
    assertThatThrownBy(() -> ecommerceSystem.placeOrder("1", 1))
        .isInstanceOf(InvalidProductException.class)
        .hasMessageContaining("Product with ID '1' does not exist or is invalid.");

  
    assertThatThrownBy(() -> ecommerceSystem.placeOrder("34", 1))
        .isInstanceOf(InvalidProductException.class)
        .hasMessageContaining("Product with ID '34' does not exist or is invalid.");
  }

  @Test
  void testCancelOrder() throws Exception {
    
    ecommerceSystem.addProduct("1", "Laptop", 5, "ELECTRONICS", true, 1000.0);
    String orderId = ecommerceSystem.placeOrder("1", 1);
    ecommerceSystem.cancelOrder(orderId);
  }

  @Test
  void testCheckOrderStatus_orderDoesNotExist() {
    assertThatThrownBy(() -> ecommerceSystem.checkOrderStatus("1"))
        .isInstanceOf(InvalidProductException.class)
        .hasMessageContaining("Order not found: 1");


    assertThatThrownBy(() -> ecommerceSystem.checkOrderStatus("33"))
        .isInstanceOf(InvalidProductException.class)
        .hasMessageContaining("Order not found: 33");
  }

  @Test
  void testCheckOrderStatus_orderCancelled() throws Exception {

    ecommerceSystem.addProduct("58", "Laptop", 5, "ELECTRONICS", true, 1000.0);
    String orderId = ecommerceSystem.placeOrder("58", 1);
    ecommerceSystem.cancelOrder(orderId);

    assertThatThrownBy(() -> ecommerceSystem.checkOrderStatus(orderId))
        .isInstanceOf(InvalidProductException.class)
        .hasMessageContaining("Order not found: " + orderId);
  }

  @Test
  void testAddToCartAndCheckout() throws InvalidCategoryException, InvalidProductException, OutOfStockException, EmptyCartException {
    ecommerceSystem.addProduct("1", "Book", 5, "BOOKS", true, 10.0);
    String cartOrderId = ecommerceSystem.createCartOrder();
    ecommerceSystem.addToCart(cartOrderId, "1");
    assertThat(ecommerceSystem.getOrder(cartOrderId).getCartItems().size()).isEqualTo(1);
    ecommerceSystem.checkout(cartOrderId);
  }

  @Test
  void testCheckoutEmptyCartThrows() throws InvalidCategoryException {
    String cartOrderId = ecommerceSystem.createCartOrder();
    assertThatThrownBy(() -> ecommerceSystem.checkout(cartOrderId))
      .isInstanceOf(EmptyCartException.class);
  }

  @Test
  void testRefundNonRefundableProductThrows() throws InvalidCategoryException, InvalidProductException, OutOfStockException {
    ecommerceSystem.addProduct("2", "Digital Music", 2, "DIGITAL", false, 5.0);
    String orderId = ecommerceSystem.placeOrder("2", 1);
    assertThatThrownBy(() -> ecommerceSystem.refundOrder(orderId))
      .isInstanceOf(NonRefundableProductException.class);
  }

  @Test
  void testRefundRefundableProductRestoresStock() throws InvalidCategoryException, InvalidProductException, OutOfStockException, NonRefundableProductException {
    ecommerceSystem.addProduct("3", "Shirt", 2, "CLOTHING", true, 20.0);
    String orderId = ecommerceSystem.placeOrder("3", 1);
    ecommerceSystem.refundOrder(orderId);
    assertThat(ecommerceSystem.getProduct("3").getStock()).isEqualTo(2);
  }

  @Test
  void testSetShippingAddressInvalidCountryThrows() throws InvalidCategoryException, InvalidProductException, OutOfStockException {
    ecommerceSystem.addProduct("4", "Laptop", 1, "ELECTRONICS", true, 1000.0);
    String orderId = ecommerceSystem.placeOrder("4", 1);
    assertThatThrownBy(() -> ecommerceSystem.setShippingAddress(orderId, "Canada", "ON"))
      .isInstanceOf(UnsupportedShippingCountryException.class);
  }

  @Test
  void testSetShippingAddressInvalidStateThrows() throws InvalidCategoryException, InvalidProductException, OutOfStockException {
    ecommerceSystem.addProduct("5", "Book", 1, "BOOKS", true, 15.0);
    String orderId = ecommerceSystem.placeOrder("5", 1);
    assertThatThrownBy(() -> ecommerceSystem.setShippingAddress(orderId, "US", "XX"))
      .isInstanceOf(UnsupportedShippingCountryException.class);
  }

  @Test
  void testOutOfStockException() throws InvalidCategoryException, InvalidProductException, OutOfStockException {
    ecommerceSystem.addProduct("6", "Pen", 1, "GENERAL", true, 2.0);
    ecommerceSystem.placeOrder("6", 1);
    assertThatThrownBy(() -> ecommerceSystem.placeOrder("6", 1))
      .isInstanceOf(OutOfStockException.class);
  }

  @Test
  void testUnsupportedPaymentMethodException() {
    assertThatThrownBy(() -> ecommerceSystem.payWithMethod("BITCOIN"))
      .isInstanceOf(UnsupportedPaymentMethodException.class)
      .hasMessageContaining("Payment method 'BITCOIN' is not supported.");
  }

  @Test
  void testSplitPaymentNotSupportedException() {
    assertThatThrownBy(() -> ecommerceSystem.splitPayment("CREDIT_CARD", "CASH"))
      .isInstanceOf(SplitPaymentNotSupportedException.class)
      .hasMessageContaining("Split payments between multiple payment methods are not supported.");
  }

  @Test
  void testPartialRefundNotAllowedException() {
    java.util.List<String> missingItems = java.util.Arrays.asList("item1", "item2");
    assertThatThrownBy(() -> ecommerceSystem.refundPartialOrder(missingItems))
      .isInstanceOf(PartialRefundNotAllowedException.class)
      .hasMessageContaining("Partial refunds are not allowed. Missing items for complete refund: item1, item2");
  }
}
