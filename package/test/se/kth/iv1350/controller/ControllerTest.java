package se.kth.iv1350.controller;

import org.junit.jupiter.api.*;
import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        Printer fakePrinter = new Printer() {
            @Override
            public void printReceipt(ReceiptDTO receipt) {

            }
        };

        InventoryManagement fakeInventory = new InventoryManagement() {
            @Override
            public ItemDTO fetchItem(String itemID, String employeeID) {
                // Return a valid item only for test
                if (itemID.equals("abc123")) {
                    return new ItemDTO("abc123", "Test Milk", 50f, 0.06f, "Milk description");
                }
                return null;
            }

            @Override
            public void updateInventory(SaleDTO saleDTO) {

            }
        };

        Discount fakeDiscount = new Discount() {
            @Override
            public float fetchDiscountInfo(int customerID, SaleDTO saleInfo) {
                return 0.1f;
            }
        };

        AccountingSystem fakeAccounting = new AccountingSystem() {
            @Override
            public void updateAccounting(SaleDTO saleAfterDiscount) {
                // Stubbed
            }
        };

        CashRegister cashRegister = new CashRegister();

        controller = new Controller(fakePrinter, fakeInventory, fakeDiscount, fakeAccounting, cashRegister);
    }

    @Test
    public void testStartNewSaleAndRegisterItem() {
        controller.startNewSale();
        controller.registerItem("abc123", 2);
        controller.payment(100f);

        SaleDTO saleInfo = controller.endSale();

        float expectedTotal = 50f * 2 * (1 - 0.1f);
        assertEquals(expectedTotal, saleInfo.getTotalPrice(), 0.01f);
    }

    @Test
    public void testPaymentReturnsCorrectChange() {
        controller.startNewSale();
        controller.registerItem("abc123", 2); // 50*2 = 100
        float change = controller.payment(110f); // 10% discount → 90 → change = 20

        assertEquals(20f, change, 0.01f);
    }

    @Test
    public void testRequestDiscountAppliesCorrectAmount() {
        controller.startNewSale();
        controller.registerItem("abc123", 1);
        controller.payment(100f); // simulate full flow

        float discount = controller.requestDiscount(123); // Should be 0.1f from stub
        assertEquals(0.1f, discount, 0.001f);
    }

    @AfterEach
    public void tearDown() {
        controller = null;
    }
}
