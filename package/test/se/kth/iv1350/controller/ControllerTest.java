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
                
            }
        };

        CashRegister cashRegister = new CashRegister();

        controller = new Controller(fakePrinter, fakeInventory, fakeDiscount, fakeAccounting, cashRegister);
    }

    @Test
    public void testStartNewSaleAndRegisterItem() {
        controller.startNewSale();
        controller.registerItem("Testprodukt", 50f, 0.06f, 2);
        controller.payment(100f); 

        SaleDTO saleInfo = controller.endSale();

        float expectedTotal = 50f * 2 * (1 - 0.1f);
        assertEquals(expectedTotal, saleInfo.getTotalPrice(), 0.01f);
    }

    @Test
    public void testPaymentReturnsCorrectChange() {
        controller.startNewSale();
        controller.registerItem("Testprodukt", 20f, 0.06f, 2); 
        float change = controller.payment(50f); 

        assertEquals(14f, change, 0.01f);
    }

    @Test
    public void testRequestDiscountAppliesCorrectAmount() {
        controller.startNewSale();
        controller.registerItem("Testprodukt", 100f, 0.06f, 1);
        controller.payment(100f); 

        float discount = controller.requestDiscount(123); 

        assertEquals(0.1f, discount, 0.001f);
    }

    @AfterEach
    public void tearDown() {
        controller = null;
    }
}
