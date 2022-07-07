package main.java.ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.Customer;
import ca.jrvs.apps.jdbc.CustomerDAO;
import ca.jrvs.apps.jdbc.DatabaseConnectionManager;
import ca.jrvs.apps.jdbc.Order;
import ca.jrvs.apps.jdbc.OrderDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCExecutor {
    public static void main(String... args){
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
            "hplussport", "postgres", "password");
        try{
            Connection connection = dcm.getConnection();
            OrderDAO orderDAO = new OrderDAO(connection);
            List<Order> orders = orderDAO.getOrdersForCustomer(789);
            orders.forEach(System.out::println);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}