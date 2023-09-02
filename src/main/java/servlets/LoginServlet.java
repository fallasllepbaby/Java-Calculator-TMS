package servlets;

import domains.User;
import services.AuthenticationService;
import services.RegistrationService;
import storages.JdbcStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/guest/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JdbcStorage jdbcStorage = new JdbcStorage();
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");

        try {
            int id = jdbcStorage.getUserId(mail);
            User user = new User(id,mail, password);
            req.getSession().setAttribute("user", user);
            AuthenticationService.login(user);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        resp.getWriter().println("You have entered!");
        resp.getWriter().println(AuthenticationService.getUser());
    }
}
