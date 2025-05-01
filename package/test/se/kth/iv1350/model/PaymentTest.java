package se.kth.iv1350.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    public void testCalculateCorrectChange() {
        Payment payment = new Payment(100f);
        float change = payment.calculateChange(67.23f);

        assertEquals(32.77f, change, 0.01f);
    }
}
