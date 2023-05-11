package Presentation.Client;

import Business.ClientBLL;
import Model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/**
 * Class used in order to model the GUI for displaying all the existing clients
 */
public class ViewClientsView extends JDialog {
    private JTable table;
    private JPanel clientsPanel;
    private JButton exitButton;

    public ViewClientsView(JFrame parent) {
        super(parent);
        setTitle("ViewClientView");
        setContentPane(clientsPanel);
        setMinimumSize(new Dimension(750, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ClientBLL clientBLL = new ClientBLL();
        //TODO:Add Clients in JTable
        fillTable(clientBLL.findAllClients());

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
    private void fillTable(List<Client> clients){
        Client auxClient=new Client();
        String[] columnNames=new String[7];
        int fieldsNumber=0;

        for (Field field : auxClient.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(auxClient);
                columnNames[fieldsNumber]=field.getName();
                fieldsNumber++;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Column 1", "Column 2", "Column 3","Column 4","Column 5","Column 6","Column 7"});
        model.addRow(new Object[]{columnNames[0], columnNames[1], columnNames[2],columnNames[3],columnNames[4],columnNames[5],columnNames[6]});
        for (Client client : clients)
            model.addRow(new Object[]{Integer.toString(client.getClientId()), client.getClientFirstName(), client.getClientLastName(), client.getClientAddress(), client.getClientEmailAddress(), client.getClientPhoneNumber(), Integer.toString(client.getClientAge())});

        table.setModel(model);
    }
}
