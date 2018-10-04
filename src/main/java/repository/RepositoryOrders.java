package repository;

import data.ConnectionDB;
import data.ConnectionPool;
import model.Orders;
import service.ReaderXML;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryOrders {

    private String clientId;
    private long requestId;
    private String name;
    private int quantity;
    private double price;

//    private Connection conn = ConnectionPool.getInstance().getConnection();

    private static List<Orders> listOrders = new ArrayList<Orders>();


    public List<Orders> findAll() throws SQLException {
        Statement stmt = null;
//        Connection conn = ConnectionPool.getInstance().getConnection();
        Connection conn = ConnectionDB.getInstance().getConnection();
        ResultSet rs = null;

        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            listOrders.clear();
            String sql = "SELECT* from Orders";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                clientId = rs.getString("clientId");
                requestId = rs.getInt("requestId");
                name = rs.getString("name");
                quantity = rs.getInt("quantity");
                price = rs.getDouble("price");
                listOrders.add(new Orders.OrderBuilder()
                        .buildClientId(clientId)
                        .buildRequestId(requestId)
                        .buildName(name)
                        .buildQuantity(quantity)
                        .buildPrice(price).build()
                );
            }
            rs.close();

        } catch (SQLException ex) {
            ex.getErrorCode();
        } finally {
            stmt.close();
            conn.close();

        }
        return listOrders;

    }


    public void addToDB(Orders orders) throws SQLException {

        Statement stmt = null;
//        Connection conn = ConnectionPool.getInstance().getConnection();
        Connection conn = ConnectionDB.getInstance().getConnection();
        try {
//            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt = conn.createStatement();
            String sql = "insert into Orders (clientId, requestId, name, quantity, price) values" +
                    " ('" + orders.getClientId() + "'," +
                    " '" + orders.getRequestId() + "'," +
                    " '" + orders.getName() + "'," +
                    " '" + orders.getQuantity() + "'," +
                    " '" + orders.getPrice() + "'" +
                    ");";
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            ex.getErrorCode();
        } finally {
            stmt.close();
            conn.close();
        }
    }


    public void initDB() throws SQLException {

        Statement stmt = null;
//        Connection conn = ConnectionPool.getInstance().getConnection();
        Connection conn = ConnectionDB.getInstance().getConnection();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            final String createTable = "DROP TABLE IF EXISTS Orders;"
                    + "CREATE TABLE Orders("
                    + "ID INT NOT NULL AUTO_INCREMENT,"
                    + "clientId VARCHAR(25)   NOT NULL,"
                    + "requestId INT NOT NULL DEFAULT '',"
                    + "name VARCHAR(25) NOT NULL DEFAULT '',"
                    + "quantity INT NOT NULL DEFAULT '',"
                    + "price DECIMAL(7,2) NOT NULL DEFAULT '0.00',"
                    + "PRIMARY KEY (ID));";

            stmt.executeUpdate(createTable);

            ReaderXML readerXML = new ReaderXML();
            List<Orders> orders = readerXML.writeXML();
            for (Orders order : orders) {
                addToDB(order);
            }
        } catch (SQLException ex) {
            ex.getErrorCode();
        } finally {
            stmt.close();
            conn.close();
        }


    }

}
