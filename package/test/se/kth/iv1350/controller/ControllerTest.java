package se.kth.iv1350.controller;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @Test
    public void testRegisterItemAndEndSaleReturnsCorrectTotal() {
        Controller contr = new Controller(
                new Printer(),
                new InventoryManagement(),
                new Discount(),
                new AccountingSystem(),
                new CashRegister()
        );

        contr.startNewSale();
        contr.registerItem("TestItem", 20f, 0.06f, 2);
        contr.payment(100f);

        SaleDTO saleInfo = contr.endSale();
        assertEquals(40f, saleInfo.getTotalPrice(), 0.001f);
    }
}
