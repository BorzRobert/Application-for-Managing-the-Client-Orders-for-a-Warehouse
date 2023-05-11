package Presentation.Product;

import Business.ClientBLL;
import Business.ProductBLL;
import Model.Client;
import Model.Product;
import Presentation.Client.EditClientView;
import Presentation.Client.ViewClientsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Class used in order to model the GUI for the Product operations
 */
public class ProductView extends JDialog {
    private JButton addProductButton;
    private JButton editProductButton;
    private JTextField editProductTextField;
    private JButton deleteProductButton;
    private JTextField deleteProductTextField;
    private JButton viewProductsButton;
    private JButton exitButton;
    private JPanel productViewPanel;

    public ProductView(JFrame parent) {
        super(parent);
        setTitle("ProductView");
        setContentPane(productViewPanel);
        setMinimumSize(new Dimension(500, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ProductBLL productBLL = new ProductBLL();

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProductView myAddProductView=new AddProductView(null);
            }
        });
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(editProductTextField.getText().isEmpty()){
                    showMessage("In order to edit a Product an ID must be provided!");
                    editProductTextField.setText(null);
                    return;
                }
                if(!isInList(productBLL.findAllProducts(),Integer.parseInt(editProductTextField.getText().toString()))){
                    showMessage("The Product with the provided ID doesn't exist!");
                    editProductTextField.setText(null);
                    return;
                }
                EditProductView myEditProductView=new EditProductView(null,Integer.parseInt(editProductTextField.getText().toString()));
                editProductTextField.setText(null);
            }
        });
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deleteProductTextField.getText().isEmpty())
                    showMessage("In order to delete a Product an ID must be provided!");
                else {
                    if (isInList(productBLL.findAllProducts(), Integer.parseInt(deleteProductTextField.getText()))) {
                        productBLL.deleteClientById(Integer.parseInt(deleteProductTextField.getText()));
                        showMessage("The Product with the id=" + deleteProductTextField.getText() + "was successfully deleted!");
                        deleteProductTextField.setText(null);
                    } else {
                        showMessage("The Product with the provided ID doesn't exist!");
                        deleteProductTextField.setText(null);
                    }
                }
            }
        });
        viewProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewProductsView myViewClientsView=new ViewProductsView(null);
            }
        });

        setVisible(true);
    }

    private boolean isInList(List<Product> products, int id) {
        for (Product product : products) {
            if (product.getProductId() == id)
                return true;
        }
        return false;
    }
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
