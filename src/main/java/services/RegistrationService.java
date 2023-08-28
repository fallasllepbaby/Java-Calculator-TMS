package services;

import domains.User;
import storages.JdbcStorage;

import java.sql.SQLException;

public class RegistrationService {
    private JdbcStorage jdbcStorage = new JdbcStorage();

    public void register(User user) {
        try {
            jdbcStorage.saveUser(user);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
