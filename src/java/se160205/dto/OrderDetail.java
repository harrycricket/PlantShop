package se160205.dto;

/**
 *
 * @author Huynh Van Phuot
 */
public class OrderDetail {

    private int id;
    private int orderId;
    private int plantId;
    private String plantName;
    private int price;
    private String imgPath;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, int plantId, String plantName, int price, String imgPath, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.plantId = plantId;
        this.plantName = plantName;
        this.price = price;
        this.imgPath = imgPath;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "id=" + id + ", orderId=" + orderId + ", plantId=" + plantId + ", plantName=" + plantName + ", price=" + price + ", imgPath=" + imgPath + ", quantity=" + quantity + '}';
    }

}
