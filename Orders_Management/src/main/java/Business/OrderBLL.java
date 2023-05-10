package Business;

import DAO.OrderDAO;
import Model.Client;
import Model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    private OrderDAO orderDAO;

    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    public Order insertOrder(Order order){
        Order st=orderDAO.insert(order);
        return st;
    }
    public List<Order> findAllOrders() {
        List<Order> ordersList =new ArrayList<>();
        ordersList=orderDAO.findAll();
        if (ordersList == null) {
            throw new NoSuchElementException("The orders list was not found or is empty!");
        }
        return ordersList;
    }
}
