import data.ConnectionPool;
import model.Orders;
import repository.RepositoryOrders;
import service.ReadAllFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class main {


    public static void main(String[] args) {

        service.ReaderXML readerXML = new service.ReaderXML();
        List<Orders> orders = readerXML.writeXML();
        RepositoryOrders repositoryOrders = new RepositoryOrders();

        try {
            repositoryOrders.initDB();
            for (Orders order : orders) {
                repositoryOrders.addToDB(order);
            }
            List<Orders> all = repositoryOrders.findAll();
            System.out.println("Dane z bazy: " + all);
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        try {
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


//        service.ReadCSV readCSV = new service.ReadCSV();
//        readCSV.writeCSV();

//        ReadAllFiles readAllFiles = new ReadAllFiles();
//        readAllFiles.writeFiles();
    }


    void initDB() {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        final String createProductTable = "DROP TABLE IF EXISTS Product;"
                + "CREATE TABLE Product("
                + "ProductID INT NOT NULL AUTO_INCREMENT,"
                + "ProductCode VARCHAR(10) NOT NULL DEFAULT '',"
                + "ProductDescription VARCHAR(100) NOT NULL DEFAULT '',"
                + "ProductPrice DECIMAL(7,2) NOT NULL DEFAULT '0.00',"
                + "PRIMARY KEY (ProductID));";

        final String populateProductTable = "INSERT INTO Product VALUES"
                + "('1', 'gw01', 'Coldplay - Ghost Stories', '14.95'),"
                + "('2', 'u201', 'U2 - Songs of Innocence', '13.95'),"
                + "('3', 'nb01', 'Nickelback - No Fixed Address', '13.95'),"
                + "('4', 'cb01', 'Chris Bailey - I Will Always Remember', '14.95');";

        final String loadAll = "SELECT* From Product ";

        try {
            conn.createStatement().executeUpdate(createProductTable);
            conn.createStatement().executeUpdate(populateProductTable);
            ResultSet resultSet = conn.createStatement().executeQuery(loadAll);


            while (resultSet.next()) {
                String prodDescript = resultSet.getString("ProductDescription");
                System.out.println(prodDescript);
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();


        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
            }
            ;
        }

    }
}
