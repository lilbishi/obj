package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.ItemDTO;

public class View {
    private Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }

    public void simulateSale() {
        contr.startNewSale();

        registerItem("abc123", 2);
        registerItem("def456", 1);
        registerItem("xyz999", 1);


        contr.payment(100f);
    }

    private void registerItem(String itemID, int quantity) {
        ItemDTO item = contr.registerItem(itemID, quantity);
        if (item != null) {
            displayItemInfo(item, quantity);
            System.out.printf("Total price so far: %.2f SEK\n\n", contr.getCurrentTotalPrice());
        }

    }

    private void displayItemInfo(ItemDTO item, int amount) {
        System.out.println("Item ID: " + item.getItemID());
        System.out.println("Name: " + item.getName());
        System.out.println("Description: " + item.getDescription());
        System.out.println("Price per item: " + item.getPrice() + " SEK");
        System.out.println("VAT: " + (item.getVAT() * 100) + "%");
        System.out.println("Quantity: " + amount);
    }
}


