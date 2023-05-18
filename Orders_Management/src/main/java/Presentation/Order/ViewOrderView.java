package Presentation.Order;

import Business.ClientBLL;
import Business.OrderBLL;
import Model.Client;
import Model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;
/**
 * Class used in order to model the GUI for displaying all the existing orders
 */
public class ViewOrderView extends JDialog {
    private JPanel viewOrderViewPanel;
    private JTable table;
    private JButton exitButton;

    public ViewOrderView(JFrame parent) {
        super(parent);
        setTitle("ViewOrderView");
        setContentPane(viewOrderViewPanel);
        setMinimumSize(new Dimension(750, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OrderBLL orderBLL=new OrderBLL();

        fillTable(orderBLL.findAllOrders());

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void fillTable(List<Order> orders){
        Order auxOrder=new Order();
        String[] columnNames=new String[4];
        int fieldsNumber=0;

        for (Field field : auxOrder.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(auxOrder);
                columnNames[fieldsNumber]=field.getName();
                fieldsNumber++;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Column 1", "Column 2", "Column 3","Column 4"});
        model.addRow(new Object[]{columnNames[0], columnNames[1], columnNames[2],columnNames[3]});
        for (Order order : orders)
            model.addRow(new Object[]{Integer.toString(order.getOrderId()),Integer.toString(order.getClientId()),Integer.toString(order.getProductId()),Integer.toString(order.getOrderQuantity())});

        table.setModel(model);
    }
}
