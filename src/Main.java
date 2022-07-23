import Database.Jdbc;
import Views.WelcomeMenu;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

public class Main {
    public static void main(String[] args) {
        Jdbc.getInstance().initialize();
        WelcomeMenu welcomeMenu = WelcomeMenu.getInstance();
        welcomeMenu.run();
    }
}
