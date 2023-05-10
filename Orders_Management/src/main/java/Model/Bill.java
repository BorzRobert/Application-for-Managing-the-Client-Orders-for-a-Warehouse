package Model;

public record Bill(int billId,int clientId, int productId, String clientFirstName, String clientLastName, String clientAddress, String clientEmail, String clientPhoneNumber, String productCategory,int orderId) {}
