package se.kth.iv1350.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the data contained in a receipt.
 */
public class ReceiptDTO {
    private float totalVAT;
    private float totalPrice;
    private float amountPaid;
    private float change;
    private LocalDateTime date;
    private List<ItemDTO> items;
    private List<Integer> quantities;
    private float discountAmount;

    /**
     * Creates a new instance of ReceiptDTO.
     *
     * @param totalPrice The total price including VAT and discount.
     * @param totalVAT The total VAT amount.
     * @param amountPaid The amount the customer paid.
     * @param change The change to give back to the customer.
     * @param date The date and time of the sale.
     * @param items The list of items bought.
     * @param quantities The corresponding quantity of each item.
     * @param discountAmount The total discount amount applied.
     */
    public ReceiptDTO(float totalPrice, float totalVAT, float amountPaid, float change, LocalDateTime date,
                      List<ItemDTO> items, List<Integer> quantities, float discountAmount) {
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.amountPaid = amountPaid;
        this.change = change;
        this.date = date;
        this.items = items;
        this.quantities = quantities;
        this.discountAmount = discountAmount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getTotalVAT() {
        return totalVAT;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public float getChange() {
        return change;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }
}
