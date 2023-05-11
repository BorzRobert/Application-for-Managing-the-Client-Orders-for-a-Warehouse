package Model;
/**
 * This class represents a model for a Bill
 * @author Borz Robert-Ionut
 */
public record Bill(int billId,int clientId, int productId, String clientFirstName, String clientLastName, String clientAddress, String clientEmail, String clientPhoneNumber, String productCategory,int orderId) {}
