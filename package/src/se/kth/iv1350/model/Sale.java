// Updated Sale.java for VAT handling (removes totalVAT field and computes dynamically)
package se.kth.iv1350.model;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Represents an ongoing sale.
 */
public class Sale {
    private Map<ItemDTO, Integer> itemMap;
    private Payment payment;
    private float totalPrice;
    private LocalDateTime saleTime;
    private float discount;

    /**
     * Creates a new instance of Sale.
     */
    public Sale() {
        this.itemMap = new LinkedHashMap<>();
        this.totalPrice = 0;
        this.discount = 0;
    }

    public void addItem(ItemDTO item, int amount) {
        itemMap.put(item, itemMap.getOrDefault(item, 0) + amount);
        totalPrice += item.getPrice() * amount;
    }

    public float calculateTotal() {
        return totalPrice * (1 - discount);
    }

    public float calculateTotalVAT() {
        float vat = 0;
        for (Map.Entry<ItemDTO, Integer> entry : itemMap.entrySet()) {
            ItemDTO item = entry.getKey();
            int quantity = entry.getValue();
            vat += item.getPrice() * item.getVAT() * quantity;
        }
        return vat;
    }

    public void applyDiscount(float discount) {
        this.discount = discount;
    }

    public void setSaleOfTime() {
        this.saleTime = LocalDateTime.now();
    }

    public void regPayment(float amountPaid) {
        this.payment = new Payment(amountPaid);
    }

    public SaleDTO getSaleInfo() {
        if (payment == null) {
            throw new IllegalStateException("Payment is not registered yet. Cannot retrieve sale information.");
        }
        return new SaleDTO(calculateTotal(), calculateTotalVAT(), saleTime, payment.getAmountPaid());
    }

    public Payment getPayment() {
        return payment;
    }

    public ReceiptDTO generateReceipt() {
        if (payment == null) {
            throw new IllegalStateException("Cannot generate receipt without payment.");
        }

        float totalVAT = calculateTotalVAT();
        float change = payment.calculateChange(calculateTotal());

        List<ItemDTO> items = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        for (Map.Entry<ItemDTO, Integer> entry : itemMap.entrySet()) {
            items.add(entry.getKey());
            quantities.add(entry.getValue());
        }

        return new ReceiptDTO(
                calculateTotal(),
                totalVAT,
                payment.getAmountPaid(),
                change,
                saleTime,
                items,
                quantities,
                discount
        );
    }
}
