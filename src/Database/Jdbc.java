package Database;

import java.sql.*;
import java.util.logging.Logger;

public class Jdbc {

    private static final Logger LOGGER = Logger.getLogger(Jdbc.class.getName());
    private Connection connection;

    public Jdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.severe(e.getMessage());
            throw new IllegalStateException("Load dataBase Driver", e);
        }
    }

    public Connection openConnection() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mySocialMedia",
                "root",
                "HESAM1381");
        LOGGER.info("connection established successfully");
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            LOGGER.info("connection closed successfully");
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
