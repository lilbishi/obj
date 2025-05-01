package se.kth.iv1350.controller;

import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;

/**
 * This is the application's only controller.
 * All calls to model and integration pass through here.
 */
public class Controller {
    private Printer printer;
    private Sale sale;
    private InventoryManagement inventory;
    private AccountingSystem accounting;
    private Discount discount;
    private CashRegister cashRegister;

    /**
     * Creates a new instance of Controller, which coordinates all other parts of the application.
     *
     * @param printer The printer to print receipts.
     * @param inventory The inventory system.
     * @param discount The discount system.
     * @param accounting The accounting system.
     * @param cashRegister The cash register.
     */
    public Controller(Printer printer, InventoryManagement inventory, Discount discount,
                      AccountingSystem accounting, CashRegister cashRegister) {
        this.printer = printer;
        this.inventory = inventory;
        this.discount = discount;
        this.accounting = accounting;
        this.cashRegister = cashRegister;
    }

    /**
     * Starts a new sale.
     */
    public void startNewSale() {
        sale = new Sale();
    }

    /**
     * Registers an item in the current sale.
     *


     * @return The registered item information.
     */
    public void registerItem(String name, float price, float vat, int quantity) {
        ItemDTO item = new ItemDTO(null, name, price, vat, "");
        sale.addItem(item, quantity);
    }


    /**
     * Ends the sale and returns the current sale's data.
     *
     * @return Summary of the sale.
     */
    public SaleDTO endSale() {
        return sale.getSaleInfo();
    }

    /**
     * Applies a discount to the current sale based on customer ID.
     *
     * @param customerID The ID of the customer.
     * @return The discount applied (as a fraction, e.g., 0.1 for 10%).
     */
    public float requestDiscount(int customerID) {
        SaleDTO saleInfo = sale.getSaleInfo();
        float discountAmount = discount.fetchDiscountInfo(customerID, saleInfo);
        sale.applyDiscount(discountAmount);
        return discountAmount;
    }

    /**
     * Registers payment for the sale, generates and prints the receipt,
     * and updates all external systems.
     *
     * @param amountPaid The amount paid by the customer.
     * @return The change to give back to the customer.
     */
    public float payment(float amountPaid) {
        if (sale == null) {
            throw new IllegalStateException("No sale has been started.");
        }

        sale.setSaleOfTime();


        sale.regPayment(amountPaid);


        float discountAmount = discount.fetchDiscountInfo(1, sale.getSaleInfo());
        sale.applyDiscount(discountAmount);

        ReceiptDTO receipt = sale.generateReceipt();

        printer.printReceipt(receipt);
        SaleDTO saleInfo = sale.getSaleInfo();

        accounting.updateAccounting(saleInfo);
        inventory.updateInventory(saleInfo);
        cashRegister.addPayment(sale.getPayment());

        return sale.getPayment().calculateChange(saleInfo.getTotalPrice());
    }

}



