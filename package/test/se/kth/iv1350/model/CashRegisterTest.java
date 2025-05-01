package se.kth.iv1350.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CashRegisterTest {

    @Test
    public void testAddPaymentUpdatesBalance() {
        CashRegister register = new CashRegister();
        register.addPayment(new Payment(100f));

        assertEquals(100f, register.getBalance(), 0.001f);
    }
}
