package Business;

import DAO.OrderDAO;
import Model.Client;
import Model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * This class is used in order to implement the business logic for Order objects
 */
public class OrderBLL {
    private OrderDAO orderDAO;

    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    /**
     * Function used to insert an Order object in the database
     * @param order
     * @return The inserted Order object
     */
    public Order insertOrder(Order order){
        Order st=orderDAO.insert(order);
        return st;
    }

    /**
     * Function used to retrieve in a list all the Order objects from the database
     * @return A list of Order objects
     */
    public List<Order> findAllOrders() {
        List<Order> ordersList =new ArrayList<>();
        ordersList=orderDAO.findAll();
        if (ordersList == null) {
            throw new NoSuchElementException("The orders list was not found or is empty!");
        }
        return ordersList;
    }
}
