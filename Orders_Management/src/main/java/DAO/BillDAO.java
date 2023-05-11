package DAO;

import Model.Bill;
import Model.Client;
import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import Connection.ConnectionFactory;

/**
 * Class used in order to access the mySQL database and create only Bill specific queries
 */
public class BillDAO extends AbstractDAO<Bill> {

	// uses basic CRUD methods from superclass

	// TODO: create only bill specific queries

    /**
     * Function used in order to retrieve in a list all the Object of type Bill from the database
     * @return The list containing all the objects of type Bill from the database
     */
    public List<Bill> findAllBills() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Bill> bills = new ArrayList<>();
        String query = createSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bills.add(new Bill(resultSet.getInt("billId"),resultSet.getInt("clientId"), resultSet.getInt("productId"),resultSet.getString("clientFirstName"),resultSet.getString("clientLastName"),resultSet.getString("clientAddress"),resultSet.getString("clientEmail"),resultSet.getString("clientPhoneNumber"),resultSet.getString("productCategory"),resultSet.getInt("orderId")));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findAllProducts " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return bills;
    }

    /**
     * Function used in order to create a less specific SELECT query
     * @return The desired String containing the query
     */
    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("Bill");
        return sb.toString();
    }
}
