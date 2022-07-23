package DbTest;

import Database.Jdbc;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.SQLException;

public class jdbcConnectionTest {
    private static Jdbc jdbc;

    @BeforeClass
    public static void beforeClass() {
        jdbc = new Jdbc();
    }

    @org.junit.Test
    public void openConnection() throws SQLException {
        jdbc.openConnection();
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        jdbc.closeConnection();
    }
}
