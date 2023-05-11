package Presentation.Order;

import Business.BillBLL;
import Business.ClientBLL;
import Business.OrderBLL;
import Business.ProductBLL;
import Model.Bill;
import Model.Client;
import Model.Order;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Class used in order to model the GUI for creating a new order
 */
public class OrderView extends JDialog {
    private JComboBox clientComboBox;
    private JComboBox productComboBox;
    private JTextField quantityTextField;
    private JButton addOrderButton;
    private JButton exitButton;
    private JPanel orderViewPanel;

    public OrderView(JFrame parent) {
        super(parent);
        setTitle("OrderView");
        setContentPane(orderViewPanel);
        setMinimumSize(new Dimension(700, 300));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OrderBLL orderBLL = new OrderBLL();
        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        BillBLL billBLL = new BillBLL();
        initComboBox(clientBLL.findAllClients(), productBLL.findAllProducts());

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantityTextField.getText().isEmpty())
                    showMessage("A quantity must be inserted in order to create an order!");
                else {
                    if (Integer.parseInt(quantityTextField.getText().toString()) < 0)
                        showMessage("Invalid quantity!");
                    else {
                        Bill auxBill=null;
                        Order auxOrder=new Order();
                        Client auxClient = new Client();
                        Product auxProduct = new Product();
                        auxClient = (Client) clientComboBox.getSelectedItem();
                        auxProduct = (Product) productComboBox.getSelectedItem();
                        if (auxProduct.getProductStock() >= Integer.parseInt(quantityTextField.getText().toString())) {
                            auxOrder=orderBLL.insertOrder(new Order(getId(orderBLL.findAllOrders()),auxClient.getClientId(),auxProduct.getProductId(),Integer.parseInt(quantityTextField.getText().toString())));
                            auxBill=billBLL.insertBill(new Bill(getIdBill(billBLL.findAllBills()),auxClient.getClientId(),auxProduct.getProductId(),auxClient.getClientFirstName(),auxClient.getClientLastName(),auxClient.getClientAddress(),auxClient.getClientEmailAddress(),auxClient.getClientPhoneNumber(),auxProduct.getProductCategory(),auxOrder.getOrderId()));
                            //TODO: change Product stock
                            auxProduct.setProductStock(auxProduct.getProductStock()-Integer.parseInt(quantityTextField.getText().toString()));
                            auxProduct=productBLL.updateProductById(auxProduct);
                            showMessage("The order with id="+auxOrder.getOrderId()+" has been successfully created!");
                            dispose();
                        } else
                            showMessage("The provided quantity is greater than the existing stock!");
                    }
                }
            }
        });

        setVisible(true);
    }

    private void initComboBox(List<Client> clients, List<Product> products) {
        for (Client client : clients) {
            clientComboBox.addItem(client);
        }
        for (Product product : products) {
            productComboBox.addItem(product);
        }
    }

    public int getId(List<Order> ordersList){
        int max=0;
        for (Order order: ordersList) {
            if(order.getOrderId()>max)
                max=order.getOrderId();
        }
        return max+1;
    }
    public int getIdBill(List<Bill> billsList){
        int max=0;
        for (Bill bill: billsList) {
            if(bill.billId()>max)
                max=bill.billId();
        }
        return max+1;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
