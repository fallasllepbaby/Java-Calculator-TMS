package storages;

import domains.Operation;
import domains.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcStorage {

    public void saveUser(User user) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (user_mail, user_password) VALUES (?,?)");
        preparedStatement.setString(1, user.getMail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void saveOperation(User user, Operation operation) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_operations (user_id, operation) VALUES (?,?)");
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, operation.toString());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public Map<String, String> getAllUsers() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Map<String, String> users = new HashMap<>();
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
        Statement statement = connection.createStatement();
        String sql = "select user_mail, user_password from users";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String mail = resultSet.getString("user_mail");
            String password = resultSet.getString("user_password");
            users.put(mail, password);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return users;
    }

    public int getUserId(String mail) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
        PreparedStatement preparedStatement = connection.prepareStatement("select user_id from users where user_mail = ?");
        preparedStatement.setString(1, mail);
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("user_id");
        }
        preparedStatement.close();
        System.out.println(id);
        return id;
    }

    public List<String> getOperations(User user) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
        List<String> operations = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("select operation from user_operations where user_id = ?");
        preparedStatement.setInt(1, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            operations.add(resultSet.getString("operation"));
        }

        preparedStatement.close();

        return operations;
    }
}
