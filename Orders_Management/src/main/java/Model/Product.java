package Model;

public class Product {
    private int productId;
    private String productName;
    private String productCategory;
    private int productStock;

    public Product() {
    }

    public Product(int productId, String productName, String productCategory, int productStock) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productStock = productStock;
    }

    public Product(String productName, String productCategory, int productStock) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productStock = productStock;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public String toString() {
        return "Product [id=" + productId + ", Name=" + productName+" ]";
    }
}
