package Database;

import Models.BusinessAccount;
import Models.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class Jdbc {
    private static Jdbc instance = null;

    private static final Logger LOGGER = Logger.getLogger(Jdbc.class.getName());
    private Connection connection;

    private static void setInstance (Jdbc instance) {
        Jdbc.instance = instance;
    }

    public static Jdbc getInstance() {
        if (Jdbc.instance == null)
            Jdbc.setInstance(new Jdbc());
        return Jdbc.instance;
    }

    public void initialize () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            loadDataInStart();
        } catch (ClassNotFoundException e) {
            LOGGER.severe(e.getMessage());
            throw new IllegalStateException("Load dataBase Driver", e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDataInStart() throws SQLException {
        String query = "select * from users";
        openConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String bio = resultSet.getString("bio");
            String birthDate = resultSet.getString("birth_date");
            String securityAnswer = resultSet.getString("security_answer");
            String accountType = resultSet.getString("account_type");
            LocalDate birthDay = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            if(accountType.equals("Normal"))
                new User(firstName, lastName, username, password, bio, birthDay, securityAnswer, accountType);
            else if(accountType.equals("Business"))
                new BusinessAccount(firstName, lastName, username, password, bio, birthDay, securityAnswer,accountType);
        }

        closeConnection();
    }

    public Connection openConnection() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mySocialMedia",
                "root",
                "------");
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
