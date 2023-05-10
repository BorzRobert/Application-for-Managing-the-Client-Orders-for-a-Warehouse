package DAO;

import Model.Product;

import java.lang.invoke.TypeDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import Connection.ConnectionFactory;

public class ProductDAO extends AbstractDAO<Product> {

    // uses basic CRUD methods from superclass

    // TODO: create only product specific queries
    public List<Product> findAllProducts() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();
        String query = createSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("productId"), resultSet.getString("productName"), resultSet.getString("productCategory"), resultSet.getInt("productStock")));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findAllProducts " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return products;
    }
    public Product findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Product st=null;
        String query = createSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("productId") == id)
                    st = new Product(resultSet.getInt("productId"), resultSet.getString("productName"), resultSet.getString("productCategory"), resultSet.getInt("productStock"));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findAllProducts " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return st;
    }

    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("Product");
        return sb.toString();
    }
}
