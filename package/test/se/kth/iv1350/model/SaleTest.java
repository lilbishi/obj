package se.kth.iv1350.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    @Test
    public void testAddItemIncreasesTotal() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("id1", "Milk", 20f, 0.06f, "");
        sale.addItem(item, 2);

        assertEquals(40f, sale.calculateTotal(), 0.001f);
    }

    @Test
    public void testApplyDiscountReducesTotal() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("id1", "Juice", 100f, 0.06f, "");
        sale.addItem(item, 1);
        sale.applyDiscount(0.10f);

        assertEquals(90f, sale.calculateTotal(), 0.001f);
    }
}
