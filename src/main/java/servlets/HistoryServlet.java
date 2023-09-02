package servlets;

import domains.Operation;
import domains.User;
import services.AuthenticationService;
import services.CalculatorService;
import services.RegistrationService;
import storages.JdbcStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

@WebServlet(name = "HistoryServlet", urlPatterns = "/user/history")
public class HistoryServlet extends HttpServlet {
    private JdbcStorage jdbcStorage = new JdbcStorage();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("User: " + AuthenticationService.getUser());
        Iterator<String> iterator;
        try {
            iterator = jdbcStorage.getOperations(AuthenticationService.getUser()).iterator();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        while (iterator.hasNext()) {
            resp.getWriter().println(iterator.next());
        }
    }
}
