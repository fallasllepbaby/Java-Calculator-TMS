package services;

import domains.User;
import storages.JdbcStorage;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class AuthenticationService {

    private static User user;

    private static boolean isUser(User user) throws SQLException, ClassNotFoundException {

        JdbcStorage jdbcStorage = new JdbcStorage();
        Map<String, String> users = jdbcStorage.getAllUsers();
        Iterator<Map.Entry<String, String>>
                iterator = users.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();

            if (user.getMail().equals(entry.getKey()) && user.getPassword().equals(entry.getValue())) {
                return true;
            }
        }

        return false;
    }
    public static void login(User user) {
        try {
            if (isUser(user)) {
                AuthenticationService.user = user;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logout() {
        AuthenticationService.user = null;
    }

    public static User getUser() {
        return user;
    }
}
