package Presentation.Client;

import Business.ClientBLL;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditClientView extends JDialog {
    private JTextField ageTextField;
    private JButton editButton;
    private JButton exitButton;
    private JTextField phoneNumberTextField;
    private JTextField emailAddressTextField;
    private JTextField addressTextField;
    private JTextField lastNameTextField;
    private JTextField firstNameTextField;
    private JPanel editClientPanel;

    public EditClientView(JFrame parent, int id) {
        super(parent);
        setTitle("EditClientView");
        setContentPane(editClientPanel);
        setMinimumSize(new Dimension(500, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ClientBLL clientBLL = new ClientBLL();
        editInit(id);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client auxClient=new Client(id,firstNameTextField.getText().toString(),lastNameTextField.getText().toString(),addressTextField.getText().toString(),emailAddressTextField.getText().toString(),phoneNumberTextField.getText().toString(),Integer.parseInt(ageTextField.getText().toString()));
                auxClient=clientBLL.updateClientById(auxClient);
                showMessage("The client was edited successfully!");
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
        ClientBLL clientBLL=new ClientBLL();
        Client client=clientBLL.findClientById(id);
        firstNameTextField.setText(client.getClientFirstName());
        lastNameTextField.setText(client.getClientLastName());
        addressTextField.setText(client.getClientAddress());
        emailAddressTextField.setText(client.getClientEmailAddress());
        phoneNumberTextField.setText(client.getClientPhoneNumber());
        ageTextField.setText(Integer.toString(client.getClientAge()));
    }
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
