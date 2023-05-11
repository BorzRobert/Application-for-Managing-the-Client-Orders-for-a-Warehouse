package Presentation;

import Presentation.Client.ClientView;
import Presentation.Order.OrderView;
import Presentation.Product.ProductView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class used in order to model the GUI for the application
 */
public class MainView extends JDialog{
    private JPanel mainViewPanel;
    private JButton orderViewButton;
    private JButton productViewButton;
    private JButton clientViewButton;
    private JButton exitButton;

    public MainView(JFrame parent) {
        super(parent);
        setTitle("MainView");
        setContentPane(mainViewPanel);
        setMinimumSize(new Dimension(500, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        clientViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientView myClientView=new ClientView(null);
            }
        });
        productViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductView myProductView=new ProductView(null);
            }
        });
        orderViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderView myOrderView=new OrderView(null);
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
}
