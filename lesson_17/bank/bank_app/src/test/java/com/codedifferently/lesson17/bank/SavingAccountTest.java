package com.codedifferently.lesson17.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codedifferently.lesson17.bank.exceptions.InsufficientFundsException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SavingAccountTest {

  private SavingAccount savingAccount;
  private String accountNumber;
  private Set<AccountOwner> owners;
  private Customer customer;
  private double initialBalance;

  @BeforeEach
  void setUp() {
    accountNumber = "SAV-123456";
    customer = new Customer(UUID.randomUUID(), "John Doe");
    owners = new HashSet<>();
    owners.add(customer);
    initialBalance = 1000.0;
    savingAccount = new SavingAccount(accountNumber, owners, initialBalance);
  }

  @Test
  void testConstructor() {
    assertEquals(accountNumber, savingAccount.getAccountNumber());
    assertEquals(owners, savingAccount.getOwners());
    assertEquals(initialBalance, savingAccount.getBalance());
    assertFalse(savingAccount.isClosed());
  }

  @Test
  void testGetAccountNumber() {
    assertEquals(accountNumber, savingAccount.getAccountNumber());
  }

  @Test
  void testGetOwners() {
    assertEquals(owners, savingAccount.getOwners());
    assertTrue(savingAccount.getOwners().contains(customer));
  }

  @Test
  void testDeposit() {
    double depositAmount = 250.0;
    savingAccount.deposit(depositAmount);
    assertEquals(initialBalance + depositAmount, savingAccount.getBalance());
  }

  @Test
  void testDepositNegativeAmount() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> savingAccount.deposit(-100.0));
    assertEquals("Deposit amount must be positive", exception.getMessage());
  }

  @Test
  void testDepositZeroAmount() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> savingAccount.deposit(0.0));
    assertEquals("Deposit amount must be positive", exception.getMessage());
  }

  @Test
  void testDepositToClosedAccount() {
    savingAccount.withdraw(initialBalance); // Withdraw all money
    savingAccount.closeAccount();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> savingAccount.deposit(100.0));
    assertEquals("Cannot deposit to a closed account", exception.getMessage());
  }

  @Test
  void testWithdraw() throws InsufficientFundsException {
    double withdrawAmount = 250.0;
    savingAccount.withdraw(withdrawAmount);
    assertEquals(initialBalance - withdrawAmount, savingAccount.getBalance());
  }

  @Test
  void testWithdrawInsufficientFunds() {
    InsufficientFundsException exception =
        assertThrows(InsufficientFundsException.class, () -> savingAccount.withdraw(2000.0));
    assertEquals("Account does not have enough funds for withdrawal", exception.getMessage());
  }

  @Test
  void testWithdrawNegativeAmount() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> savingAccount.withdraw(-100.0));
    assertEquals("Withdrawal amount must be positive", exception.getMessage());
  }

  @Test
  void testWithdrawZeroAmount() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> savingAccount.withdraw(0.0));
    assertEquals("Withdrawal amount must be positive", exception.getMessage());
  }

  @Test
  void testWithdrawFromClosedAccount() {
    savingAccount.withdraw(initialBalance); // Withdraw all money
    savingAccount.closeAccount();

    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> savingAccount.withdraw(0.01));
    assertEquals("Cannot withdraw from a closed account", exception.getMessage());
  }

  @Test
  void testWithdrawExactBalance() throws InsufficientFundsException {
    savingAccount.withdraw(initialBalance);
    assertEquals(0.0, savingAccount.getBalance());
  }

  @Test
  void testGetBalance() {
    assertEquals(initialBalance, savingAccount.getBalance());

    // Test balance after deposit
    savingAccount.deposit(500.0);
    assertEquals(initialBalance + 500.0, savingAccount.getBalance());

    // Test balance after withdrawal
    savingAccount.withdraw(200.0);
    assertEquals(initialBalance + 500.0 - 200.0, savingAccount.getBalance());
  }

  @Test
  void testCloseAccount() {
    savingAccount.withdraw(initialBalance); // Withdraw all money first
    savingAccount.closeAccount();
    assertTrue(savingAccount.isClosed());
  }

  @Test
  void testCloseAccountWithPositiveBalance() {
    IllegalStateException exception =
        assertThrows(IllegalStateException.class, () -> savingAccount.closeAccount());
    assertEquals("Cannot close account with a positive balance", exception.getMessage());
  }

  @Test
  void testIsClosed() {
    assertFalse(savingAccount.isClosed());

    savingAccount.withdraw(initialBalance); // Withdraw all money
    savingAccount.closeAccount();
    assertTrue(savingAccount.isClosed());
  }

  @Test
  void testEquals() {
    // Test equality with same account number
    SavingAccount sameAccount = new SavingAccount(accountNumber, owners, 500.0);
    assertTrue(savingAccount.equals(sameAccount));

    // Test inequality with different account number
    SavingAccount differentAccount = new SavingAccount("SAV-999999", owners, initialBalance);
    assertFalse(savingAccount.equals(differentAccount));

    // Test inequality with null
    assertNotEquals(savingAccount, null);

    // Test inequality with different type
    assertNotEquals(savingAccount, "Not an account");

    // Test reflexivity
    assertTrue(savingAccount.equals(savingAccount));
  }

  @Test
  void testHashCode() {
    assertEquals(accountNumber.hashCode(), savingAccount.hashCode());

    // Test that equal objects have equal hash codes
    SavingAccount sameAccount = new SavingAccount(accountNumber, owners, 500.0);
    assertEquals(savingAccount.hashCode(), sameAccount.hashCode());
  }

  @Test
  void testToString() {
    String expected =
        "SavingsAccount{accountNumber='"
            + accountNumber
            + "', balance="
            + initialBalance
            + ", isActive=true}";
    assertEquals(expected, savingAccount.toString());
  }

  @Test
  void testToStringWhenClosed() {
    savingAccount.withdraw(initialBalance); // Withdraw all money
    savingAccount.closeAccount();

    String expected =
        "SavingsAccount{accountNumber='" + accountNumber + "', balance=0.0, isActive=false}";
    assertEquals(expected, savingAccount.toString());
  }

  @Test
  void testConstructorWithZeroBalance() {
    SavingAccount zeroBalanceAccount = new SavingAccount("SAV-000000", owners, 0.0);
    assertEquals(0.0, zeroBalanceAccount.getBalance());
    assertFalse(zeroBalanceAccount.isClosed());
  }

  @Test
  void testConstructorWithNegativeBalance() {
    SavingAccount negativeBalanceAccount = new SavingAccount("SAV-NEGATIVE", owners, -100.0);
    assertEquals(-100.0, negativeBalanceAccount.getBalance());
    assertFalse(negativeBalanceAccount.isClosed());
  }

  @Test
  void testMultipleDepositsAndWithdrawals() {
    double expectedBalance = initialBalance;

    // Multiple deposits
    savingAccount.deposit(100.0);
    expectedBalance += 100.0;
    assertEquals(expectedBalance, savingAccount.getBalance());

    savingAccount.deposit(250.0);
    expectedBalance += 250.0;
    assertEquals(expectedBalance, savingAccount.getBalance());

    // Multiple withdrawals
    savingAccount.withdraw(50.0);
    expectedBalance -= 50.0;
    assertEquals(expectedBalance, savingAccount.getBalance());

    savingAccount.withdraw(200.0);
    expectedBalance -= 200.0;
    assertEquals(expectedBalance, savingAccount.getBalance());
  }

  @Test
  void testMultipleOwners() {
    Business business = new Business(UUID.randomUUID(), "Test Business", "12-3456789");
    Customer customer2 = new Customer(UUID.randomUUID(), "Jane Smith");

    Set<AccountOwner> multipleOwners = new HashSet<>();
    multipleOwners.add(customer);
    multipleOwners.add(business);
    multipleOwners.add(customer2);

    SavingAccount multiOwnerAccount = new SavingAccount("SAV-MULTI", multipleOwners, 1000.0);

    assertEquals(3, multiOwnerAccount.getOwners().size());
    assertTrue(multiOwnerAccount.getOwners().contains(customer));
    assertTrue(multiOwnerAccount.getOwners().contains(business));
    assertTrue(multiOwnerAccount.getOwners().contains(customer2));
  }
}
