package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository extends Jdbc{

    public static void insertUser(String username, String password, String firstname, String lastname, String bio, String birthDate, String securityQuestionAnswer, String accountType) {
        String query = " insert into users (username, password, first_name, last_name," +
                " bio, birth_date, security_answer, account_type)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = Jdbc.getInstance().openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstname);
            preparedStatement.setString(4, lastname);
            preparedStatement.setString(5, bio);
            preparedStatement.setString(6, birthDate);
            preparedStatement.setString(7, securityQuestionAnswer);
            preparedStatement.setString(8, accountType);
            preparedStatement.execute();
            Jdbc.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
