package se.kth.iv1350.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.SaleDTO;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountTest {

    @Test
    public void testFetchDiscountReturnsZeroPercent() {
        Discount discount = new Discount();
        SaleDTO dummySale = new SaleDTO(100f, 6f, null, 100f);
        float result = discount.fetchDiscountInfo(1, dummySale);

        assertEquals(0.0f, result, 0.001f);
    }
}
