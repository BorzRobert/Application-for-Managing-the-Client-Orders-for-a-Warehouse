package Presentation.Client;

import Business.ClientBLL;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;

public class AddClientView extends JDialog {
    private JTextField ageTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailAddressTextField;
    private JTextField addressTextField;
    private JTextField lastNameTextField;
    private JButton addButton;
    private JTextField firstNameTextField;
    private JPanel addClientPanel;
    private JButton exitButton;

    public AddClientView(JFrame parent) {
        super(parent);
        setTitle("AddClientView");
        setContentPane(addClientPanel);
        setMinimumSize(new Dimension(500, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ClientBLL clientBLL=new ClientBLL();

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
                    Client auxClient=new Client(getId(clientBLL.findAllClients()),firstNameTextField.getText(),lastNameTextField.getText(),addressTextField.getText(),emailAddressTextField.getText(),phoneNumberTextField.getText(),Integer.parseInt(ageTextField.getText()));
                    auxClient=clientBLL.insertClient(auxClient);
                    showMessage("Client whit id="+auxClient.getClientId()+" was succesfully added!");
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    public int getId(List<Client> clientsList){
        int max=0;
        for (Client client: clientsList) {
            if(client.getClientId()>max)
                max=client.getClientId();
        }
        return max+1;
    }
    public boolean checkInputs(){
        if(!firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty() && !ageTextField.getText().isEmpty() && !phoneNumberTextField.getText().isEmpty() && !addressTextField.getText().isEmpty() && !emailAddressTextField.getText().isEmpty() ){
            if(Integer.parseInt(ageTextField.getText())<18){
                showMessage("The Client must be at least 18!");
                return false;
            }
            if(!isValid(emailAddressTextField.getText())){
                showMessage("Invalid email address!");
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
    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
