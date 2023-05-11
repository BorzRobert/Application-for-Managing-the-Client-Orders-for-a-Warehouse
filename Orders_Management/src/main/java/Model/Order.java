package Model;
/**
 * This class represents a model for an Order
 * @author Borz Robert-Ionut
 */
public class Order {
    private int orderId;
    private int clientId;
    private int productId;
    private int orderQuantity;

    public Order(){

    }
    public Order(int orderId, int clientId, int productId, int orderQuantity) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getProductId() {
        return productId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
