package Presentation.Product;

import Business.ClientBLL;
import Business.ProductBLL;
import Model.Client;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProductView extends JDialog {
    private JPanel editProductPanel;
    private JTextField productNameTextField;
    private JTextField productCategoryTextField;
    private JTextField productStockTextField;
    private JButton editButton;
    private JButton exitButton;

    public EditProductView(JFrame parent, int id) {
        super(parent);
        setTitle("EditProductView");
        setContentPane(editProductPanel);
        setMinimumSize(new Dimension(500, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ProductBLL productBLL=new ProductBLL();
        editInit(id);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product auxProduct=new Product(id,productNameTextField.getText().toString(),productCategoryTextField.getText().toString(),Integer.parseInt(productStockTextField.getText().toString()));
                auxProduct=productBLL.updateProductById(auxProduct);
                showMessage("The product was edited successfully!");
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
    private void editInit(int id) {
        ProductBLL productBLL=new ProductBLL();
        Product product=productBLL.findProductById(id);
        productNameTextField.setText(product.getProductName());
        productCategoryTextField.setText(product.getProductCategory());
        productStockTextField.setText(Integer.toString(product.getProductStock()));
    }
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
