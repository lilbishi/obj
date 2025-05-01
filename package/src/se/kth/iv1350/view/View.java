package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.ItemDTO;
import se.kth.iv1350.model.SaleDTO;

/*
 This class is a placeholder for the user interface.
 It contains hardcoded calls to the controller to simulate a sale.
 */
public class View {
    private Controller contr;

    /**
     * Creates a new instance of View.
     *
     * @param contr The controller that is used for all operations.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Simulates a sale by calling methods in the controller.
     */
    public void simulateSale() {
        contr.startNewSale();

        contr.registerItem("Arla Strawberry Milk", 29.90f, 0.06f, 2);
        contr.registerItem("Protein Bar", 14.90f, 0.06f, 1);

        contr.payment(100f);
    }

    private void displayItemInfo(ItemDTO item, int amount) {
    System.out.println("Item ID: " + item.getItemID());
    System.out.println("Name: " + item.getName());
    System.out.println("Price per item: " + item.getPrice() + " SEK");
    System.out.println("VAT: " + (item.getVAT() * 100) + "%");
    System.out.println("Quantity: " + amount);
    System.out.println();
}

}

