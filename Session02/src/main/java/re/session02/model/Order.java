package re.session02.model;

import java.util.Date;

public class Order {
    private String orderId;
    private String productName;
    private double totalAmount;
    private Date orderDate;

    public Order() {}

    public Order(String orderId, String productName, double totalAmount, Date orderDate) {
        this.orderId = orderId;
        this.productName = productName;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
}
