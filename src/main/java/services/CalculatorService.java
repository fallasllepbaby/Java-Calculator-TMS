package services;

import java.sql.SQLException;
import domains.Operation;
import domains.User;
import storages.JdbcStorage;

public class CalculatorService {
    JdbcStorage jdbcStorage = new JdbcStorage();

    public void calculate(Operation operation) throws SQLException, ClassNotFoundException {
        User user = AuthenticationService.getUser();
        switch (operation.getType()) {
            case "sum" :
                operation.setResult(operation.getNum1() + operation.getNum2());
                jdbcStorage.saveOperation(user,operation);
                break;
            case "sub" :
                operation.setResult(operation.getNum1() - operation.getNum2());
                jdbcStorage.saveOperation(user,operation);
                break;
            case "div" :
                operation.setResult(operation.getNum1() / operation.getNum2());
                jdbcStorage.saveOperation(user,operation);
                break;
            case "mul" :
                operation.setResult(operation.getNum1() * operation.getNum2());
                jdbcStorage.saveOperation(user,operation);
                break;
        }
    }

//    public void showHistory() throws SQLException {
//        writer.write(jdbcSample.getAllOperations(user));
//    }

}
