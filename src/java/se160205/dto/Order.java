package se160205.dto;

/**
 *
 * @author Huynh Van Phuot
 */
public class Order {

    private int id;
    private String orderDate;
    private String shipDate;
    private int status;
    private int accId;

    public Order() {
    }

    public Order(int id, String orderDate, String shipDate, int status, int accId) {
        this.id = id;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
        this.accId = accId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", orderDate=" + orderDate + ", shipDate=" + shipDate + ", status=" + status + ", accId=" + accId + '}';
    }

}
