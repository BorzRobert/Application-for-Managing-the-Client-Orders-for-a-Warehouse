package Presentation.Product;

import Business.ProductBLL;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public class ViewProductsView extends JDialog {
    private JTable table;
    private JPanel viewProductsPanel;
    private JButton exitButton;

    public ViewProductsView(JFrame parent) {
        super(parent);
        setTitle("ViewProductView");
        setContentPane(viewProductsPanel);
        setMinimumSize(new Dimension(750, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ProductBLL productBLL = new ProductBLL();
        //TODO:Add Clients in JTable
        fillTable(productBLL.findAllProducts());

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
    private void fillTable(List<Product> products){
        Product auxProduct=new Product();
        String[] columnNames=new String[4];
        int fieldsNumber=0;

        for (Field field : auxProduct.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(auxProduct);
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
        for (Product product : products)
            model.addRow(new Object[]{Integer.toString(product.getProductId()),product.getProductName(),product.getProductCategory(),Integer.toString(product.getProductStock())});

        table.setModel(model);
    }
}
