package Business;

import DAO.BillDAO;
import DAO.OrderDAO;
import Model.Bill;
import Model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BillBLL {

    private BillDAO billDAO;

    public BillBLL() {
        billDAO = new BillDAO();
    }

    public Bill insertBill(Bill bill){
        Bill st=billDAO.insert(bill);
        return st;
    }
    public List<Bill> findAllBills() {
        List<Bill> billsList =new ArrayList<>();
        billsList=billDAO.findAllBills();
        if (billsList == null) {
            throw new NoSuchElementException("The bills list was not found or is empty!");
        }
        return billsList;
    }
}
