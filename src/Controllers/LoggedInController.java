package Controllers;

import Models.User;

public class LoggedInController extends Controller{

    /*private static LoggedInController instance = null;
    private User user = LoggedInMenu.getLoggedInUser();

    private LoggedInController() {}

    private static void setInstance(LoggedInController instance) {
        LoggedInController.instance = instance;
    }

    public static LoggedInController getInstance() {
        if (LoggedInController.instance == null)
            LoggedInController.setInstance(new LoggedInController());
        return LoggedInController.instance;
    }*/

    private User user;

    public LoggedInController(User user) {
        this.user=user;
    }
}
