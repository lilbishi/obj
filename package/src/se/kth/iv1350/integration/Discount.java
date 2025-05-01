package se.kth.iv1350.integration;

import se.kth.iv1350.model.SaleDTO;

/**
 * Handles fetching discount information for customers.
 */
public class Discount {
    /**
     * Creates a new instance of Discount.
     */
    public Discount() {}

    /**
     * Fetches discount based on the provided customer ID and sale information.
     *
     * @param customerID The identifier for the customer.
     * @param saleInfo Information about the current sale.
     * @return The discount to be applied, as a percentage (e.g., 0.10 for 10% discount).
     */
    public float fetchDiscountInfo(int customerID, SaleDTO saleInfo) {

        return 0.0f;
    }
}

