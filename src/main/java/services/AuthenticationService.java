package services;

import domains.User;
import storages.JdbcStorage;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class AuthenticationService {

    public static boolean isUser(User user) throws SQLException, ClassNotFoundException {

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
}
