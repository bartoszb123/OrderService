package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {


    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static final Connection connection = null;

    private ConnectionDB() {
    }

    public Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception ex) {
//            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE,
//                    "No connection", ex);
            return null;
        }
    }

    private static class ConnectionPoolHolder {
        public static final ConnectionDB INSTANCE = new ConnectionDB();
    }

    public static ConnectionDB getInstance() {

        return ConnectionPoolHolder.INSTANCE;
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASS() {
        return PASS;
    }
}
