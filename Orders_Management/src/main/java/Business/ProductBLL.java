package Business;

import DAO.ProductDAO;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * This class is used in order to implement the business logic for Product objects
 */
public class ProductBLL {
    private ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * Function used to insert a Product object in the database
     * @param product
     * @return The inserted Product object
     */
    public Product insertProduct(Product product){
        Product st=productDAO.insert(product);
        return st;
    }

    /**
     * Function used in order to update a Product object from the database
     * @param product
     * @return The updated Product object
     */
    public Product updateProductById(Product product){
        Product st=productDAO.updateById(product,product.getProductId(),"productId");
        return st;
    }

    /**
     * Function that returns the Product object that corresponds with the given id
     * @param id
     * @return The desired Product object
     */
    public Product findProductById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Function used in order to delete a Product object from de database with respect to the given id
     * @param id
     */
    public void deleteClientById(int id) {
        productDAO.deleteById(id,"productId");
    }

    /**
     * Function used to retrieve in a list all the Product objects from the database
     * @return A list of Product objects
     */
    public List<Product> findAllProducts() {
        List<Product> productsList =new ArrayList<>();
        productsList=productDAO.findAllProducts();
        if (productsList == null) {
            throw new NoSuchElementException("The products list was not found or is empty!");
        }
        return productsList;
    }
}
