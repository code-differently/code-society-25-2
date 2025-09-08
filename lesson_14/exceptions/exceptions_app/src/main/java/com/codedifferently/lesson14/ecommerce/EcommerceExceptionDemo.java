package com.codedifferently.lesson14.ecommerce;

import com.codedifferently.lesson14.exceptions.*;

public class EcommerceExceptionDemo {

  public static void main(String[] args) {
    System.out.println("=== E-commerce Exception Demo with Order & EcommerceSystem ===\n");
    
    EcommerceSystem ecommerce = new EcommerceSystem();
    
    try {
      // Setup some products with different characteristics
      ecommerce.addProduct("LAPTOP001", "Gaming Laptop", 3, "ELECTRONICS", true, 1299.99);
      ecommerce.addProduct("SHIRT001", "Cotton T-Shirt", 0, "CLOTHING", true, 19.99); // Out of stock
      ecommerce.addProduct("BOOK001", "Programming Guide", 10, "BOOKS", true, 49.99);
      ecommerce.addProduct("GIFT001", "Gift Card", 100, "DIGITAL", false, 50.00); // Non-refundable
      
      demonstrateExceptions(ecommerce);
      
    } catch (Exception e) {
      System.err.println("Setup error: " + e.getMessage());
    }
  }
  
  private static void demonstrateExceptions(EcommerceSystem ecommerce) {
    
    // 1. Test InvalidProductException
    System.out.println("1. Testing InvalidProductException:");
    try {
      ecommerce.placeOrder("INVALID123", 1);
    } catch (InvalidProductException e) {
      System.err.println("   ❌ " + e.getMessage());
      System.out.println("   ✓ Exception caught - product doesn't exist in system");
    } catch (OutOfStockException e) {
      System.err.println("   Unexpected: " + e.getMessage());
    }
    
    // 2. Test OutOfStockException 
    System.out.println("\n2. Testing OutOfStockException:");
    try {
      ecommerce.placeOrder("SHIRT001", 5); // Only 0 in stock
    } catch (OutOfStockException e) {
      System.err.println("   ❌ " + e.getMessage());
      System.out.println("   ✓ Exception caught - insufficient stock in Product object");
    } catch (InvalidProductException e) {
      System.err.println("   Unexpected: " + e.getMessage());
    }
    
    // 3. Test InvalidCategoryException
    System.out.println("\n3. Testing InvalidCategoryException:");
    try {
      ecommerce.addProduct("TEST001", "Test Product", 10, "INVALID_CATEGORY", true, 99.99);
    } catch (InvalidCategoryException e) {
      System.err.println("   ❌ " + e.getMessage());
      System.out.println("   ✓ Exception caught - category validation in EcommerceSystem");
    }
    
    // 4. Test EmptyCartException
    System.out.println("\n4. Testing EmptyCartException:");
    try {
      String cartOrderId = ecommerce.createCartOrder();
      ecommerce.checkout(cartOrderId); // Empty cart
    } catch (EmptyCartException e) {
      System.err.println("   ❌ " + e.getMessage());
      System.out.println("   ✓ Exception caught - Order.isCartEmpty() returned true");
    }
    
    // 5. Test UnsupportedShippingCountryException
    System.out.println("\n5. Testing UnsupportedShippingCountryException:");
    try {
      String orderId = ecommerce.placeOrder("LAPTOP001", 1);
      ecommerce.setShippingAddress(orderId, "Canada", "ON");
    } catch (UnsupportedShippingCountryException e) {
      System.err.println("   ❌ " + e.getMessage());
      System.out.println("   ✓ Exception caught - shipping validation using Order shipping info");
    } catch (Exception e) {
      System.err.println("   Unexpected: " + e.getMessage());
    }
    
    // 6. Test NonRefundableProductException
    System.out.println("\n6. Testing NonRefundableProductException:");
    try {
      String orderId = ecommerce.placeOrder("GIFT001", 1); // Non-refundable product
      ecommerce.refundOrder(orderId);
    } catch (NonRefundableProductException e) {
      System.err.println("   ❌ " + e.getMessage());
      System.out.println("   ✓ Exception caught - Product.isRefundable() returned false");
    } catch (Exception e) {
      System.err.println("   Unexpected: " + e.getMessage());
    }
    
    // 7. Demonstrate successful operations
    System.out.println("\n7. Successful Operations (no exceptions):");
    try {
      // Valid product order
      String orderId1 = ecommerce.placeOrder("BOOK001", 2);
      System.out.println("   ✓ Order placed: " + ecommerce.checkOrderStatus(orderId1));
      
      // Valid shipping address
      ecommerce.setShippingAddress(orderId1, "US", "CA");
      System.out.println("   ✓ Shipping address set successfully");
      
      // Valid refund
      String orderId2 = ecommerce.placeOrder("BOOK001", 1);
      ecommerce.refundOrder(orderId2);
      System.out.println("   ✓ Refund processed successfully");
      
      // Valid cart operations
      String cartId = ecommerce.createCartOrder();
      ecommerce.addToCart(cartId, "BOOK001");
      ecommerce.checkout(cartId);
      System.out.println("   ✓ Cart checkout successful");
      
    } catch (Exception e) {
      System.err.println("   Unexpected error in successful operations: " + e.getMessage());
    }
    
    System.out.println("\n=== Demo Complete ===");
    System.out.println("\nKey Integration Points:");
    System.out.println("• InvalidProductException: Uses EcommerceSystem.products Map lookup");
    System.out.println("• OutOfStockException: Uses Product.getStock() from Order.getProduct()");
    System.out.println("• InvalidCategoryException: Validates against EcommerceSystem.validCategories");
    System.out.println("• EmptyCartException: Uses Order.isCartEmpty() and Order.getCartItems()");
    System.out.println("• UnsupportedShippingCountryException: Uses Order shipping address fields");
    System.out.println("• NonRefundableProductException: Uses Product.isRefundable() from Order");
  }
}
