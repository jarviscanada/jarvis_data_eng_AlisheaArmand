package main.java.ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.Customer;
import ca.jrvs.apps.jdbc.CustomerDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {
    public static void main(String[] args) {
        ca.jrvs.apps.jdbc.DatabaseConnectionManager dcm = new ca.jrvs.apps.jdbc.DatabaseConnectionManager("localhost", "hplussport", "postgres", "password");
        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = new Customer();
            customer.setFirstName("George");
            customer.setFirstName("Washington");
            customer.setEmail("george.washington@wh.gov");
            customer.setPhone("(555) 555-6543");
            customer.setAddress("1234 Main st");
            customer.setCity("Mount Vernon");
            customer.setState("VA");
            customer.setZipCode("22121");

            customerDAO.create(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
