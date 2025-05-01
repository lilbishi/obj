package se.kth.iv1350.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryManagementTest {

    @Test
    public void testFetchItemReturnsCorrectItemForKnownID() {
        InventoryManagement inventory = new InventoryManagement();
        ItemDTO item = inventory.fetchItem("abc123", "any");

        assertNotNull(item, "Item should not be null");
        assertEquals("Arla Strawberry Milk", item.getName());
        assertEquals(29.90f, item.getPrice(), 0.001f);
    }

    @Test
    public void testFetchItemReturnsNullForUnknownID() {
        InventoryManagement inventory = new InventoryManagement();
        ItemDTO item = inventory.fetchItem("xyz999", "any");

        assertNull(item, "Should return null for unknown itemID");
    }
}
