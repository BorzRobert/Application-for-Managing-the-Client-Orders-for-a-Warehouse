package Business;

import DAO.ProductDAO;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    public Product insertProduct(Product product){
        Product st=productDAO.insert(product);
        return st;
    }
    public Product updateProductById(Product product){
        Product st=productDAO.updateById(product,product.getProductId(),"productId");
        return st;
    }
    public Product findProductById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }
    public void deleteClientById(int id) {
        productDAO.deleteById(id,"productId");
    }

    public List<Product> findAllProducts() {
        List<Product> productsList =new ArrayList<>();
        productsList=productDAO.findAllProducts();
        if (productsList == null) {
            throw new NoSuchElementException("The products list was not found or is empty!");
        }
        return productsList;
    }
}
