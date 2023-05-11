package Business;

import DAO.BillDAO;
import DAO.OrderDAO;
import Model.Bill;
import Model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class is used in order to implement the business logic for Bill objects
 */
public class BillBLL {

    private BillDAO billDAO;

    public BillBLL() {
        billDAO = new BillDAO();
    }

    /**
     * Function used to insert a Bill object in the database
     * @param bill
     * @return The inserted Bill object
     */
    public Bill insertBill(Bill bill){
        Bill st=billDAO.insert(bill);
        return st;
    }

    /**
     * Function used to retrieve in a list all the Bill objects from the database
     * @return A list of Bill objects
     */
    public List<Bill> findAllBills() {
        List<Bill> billsList =new ArrayList<>();
        billsList=billDAO.findAllBills();
        if (billsList == null) {
            throw new NoSuchElementException("The bills list was not found or is empty!");
        }
        return billsList;
    }
}
