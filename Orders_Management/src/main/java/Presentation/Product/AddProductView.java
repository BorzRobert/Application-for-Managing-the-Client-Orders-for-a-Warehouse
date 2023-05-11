package Presentation.Product;

import Business.ClientBLL;
import Business.ProductBLL;
import DAO.AbstractDAO;
import DAO.ProductDAO;
import Model.Client;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/**
 * Class used in order to model the GUI for adding a product
 */
public class AddProductView extends JDialog {
    private JTextField productNameTextField;
    private JTextField productCategoryTextField;
    private JTextField stockTextField;
    private JButton addButton;
    private JButton exitButton;
    private JPanel addProductPanel;

    public AddProductView(JFrame parent) {
        super(parent);
        setTitle("AddProductView");
        setContentPane(addProductPanel);
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkInputs()){
                    Product auxProduct=new Product(getId(productBLL.findAllProducts()),productNameTextField.getText().toString(),productCategoryTextField.getText().toString(),Integer.parseInt(stockTextField.getText().toString()));
                    auxProduct=productBLL.insertProduct(auxProduct);
                    showMessage("Product whit id="+auxProduct.getProductId()+" was succesfully added!");
                    dispose();
                }
            }
        });
        setVisible(true);
    }

    public int getId(List<Product> productsList){
        int max=0;
        for (Product product: productsList) {
            if(product.getProductId()>max)
                max=product.getProductId();
        }
        return max+1;
    }
    public boolean checkInputs(){
        if(!productNameTextField.getText().isEmpty() && !productCategoryTextField.getText().isEmpty() && !stockTextField.getText().isEmpty()){
            if(Integer.parseInt(stockTextField.getText())<0){
                showMessage("The Product's stock must be greater than 0!");
                return false;
            }
            return true;
        }
        else
        {
            showMessage("Some inputs are missing!");
        }
        return false;
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
