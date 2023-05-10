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

public class BillDAO extends AbstractDAO<Bill> {

	// uses basic CRUD methods from superclass

	// TODO: create only bill specific queries
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
    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("Bill");
        return sb.toString();
    }
}
