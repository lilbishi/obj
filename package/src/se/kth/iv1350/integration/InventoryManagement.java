package se.kth.iv1350.integration;

import se.kth.iv1350.model.ItemDTO;
import se.kth.iv1350.model.SaleDTO;

/**
 * Handles inventory operations, such as fetching item data and updating stock.
 */
public class InventoryManagement {
    /**
     * Creates a new instance of InventoryManagement.
     */
    public InventoryManagement() {}

    /**
     * Fetches item information based on the provided item ID and employee ID.
     *
     * @param itemID The identifier for the item.
     * @param employeeID The identifier for the employee.
     * @return Information about the item.
     */
    public ItemDTO fetchItem(String itemID, String employeeID) {
        if (itemID.equals("abc123")) {
            return new ItemDTO("abc123", "Arla Strawberry Milk", 29.90f, 0.06f, "500g, high fiber, gluten free");
        } else if (itemID.equals("def456")) {
            return new ItemDTO("def456", "Protein Bar", 14.90f, 0.06f, "25g, low sugar");
        }
        return null; // If item is not found
    }

    /**
     * Updates the inventory system after a sale has been completed.
     *
     * @param saleDTO The data of the completed sale.
     */
    public void updateInventory(SaleDTO saleDTO) {


    }
}
