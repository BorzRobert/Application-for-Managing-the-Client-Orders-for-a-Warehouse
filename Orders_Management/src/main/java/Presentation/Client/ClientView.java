package Presentation.Client;

import Business.ClientBLL;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientView extends JDialog {
    private JPanel clientViewPanel;
    private JButton addClientButton;
    private JButton editClientButton;
    private JButton deleteClientButton;
    private JButton viewClientsButton;
    private JButton exitButton;
    private JTextField deleteClientTextField;
    private JTextField editClientTextField;

    public ClientView(JFrame parent) {
        super(parent);
        setTitle("ClientView");
        setContentPane(clientViewPanel);
        setMinimumSize(new Dimension(500, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ClientBLL clientBLL = new ClientBLL();

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddClientView myAddClientView = new AddClientView(null);
            }
        });
        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deleteClientTextField.getText().isEmpty())
                    showMessage("In order to delete a Client an ID must be provided!");
                else {
                    if (isInList(clientBLL.findAllClients(), Integer.parseInt(deleteClientTextField.getText()))) {
                        clientBLL.deleteClientById(Integer.parseInt(deleteClientTextField.getText()));
                        showMessage("The client with the id=" + deleteClientTextField.getText() + "was successfully deleted!");
                        deleteClientTextField.setText(null);
                    } else {
                        showMessage("The Client with the provided ID doesn't exist!");
                        deleteClientTextField.setText(null);
                    }
                }
            }
        });
        editClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(editClientTextField.getText().isEmpty()){
                    showMessage("In order to edit a Client an ID must be provided!");
                    editClientTextField.setText(null);
                    return;
                }
                if(!isInList(clientBLL.findAllClients(),Integer.parseInt(editClientTextField.getText().toString()))){
                    showMessage("The Client with the provided ID doesn't exist!");
                    editClientTextField.setText(null);
                    return;
                }
                EditClientView myEditClientView=new EditClientView(null,Integer.parseInt(editClientTextField.getText().toString()));
                editClientTextField.setText(null);
            }
        });
        viewClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewClientsView myViewClientsView=new ViewClientsView(null);
            }
        });

        setVisible(true);
    }

    private boolean isInList(List<Client> clients, int id) {
        for (Client client : clients) {
            if (client.getClientId() == id)
                return true;
        }
        return false;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
