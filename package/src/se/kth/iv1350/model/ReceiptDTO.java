package se.kth.iv1350.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ReceiptDTO {
    private float totalVAT;
    private float totalPrice;
    private float amountPaid;
    private float change;
    private LocalDateTime date;
    private List<ItemDTO> items;
    private List<Integer> quantities;
    private float discountAmount;

    public ReceiptDTO(float totalPrice, float totalVAT, float amountPaid, float change, LocalDateTime date,
                      List<ItemDTO> items, List<Integer> quantities, float discountAmount) {
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.amountPaid = amountPaid;
        this.change = change;
        this.date = date;
        this.items = Collections.unmodifiableList(new ArrayList<>(items));
        this.quantities = Collections.unmodifiableList(new ArrayList<>(quantities));
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

