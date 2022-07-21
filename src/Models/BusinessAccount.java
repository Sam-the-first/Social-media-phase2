package Models;

import java.time.LocalDate;

public class BusinessAccount extends User {

    public BusinessAccount(String firstname, String lastname, String username, String password, String bio, LocalDate birthDate, String securityAnswer) {
        super(firstname, lastname, username, password, bio, birthDate, securityAnswer);
    }
}
