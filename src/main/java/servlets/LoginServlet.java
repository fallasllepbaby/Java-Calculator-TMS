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
    private JdbcStorage jdbcStorage = new JdbcStorage();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");

        try {
            int id = jdbcStorage.getUserId(mail);
            User user = new User(id,mail, password);
            if (AuthenticationService.isUser(user)) {
                req.getSession().setAttribute("user", user);
                resp.getWriter().println("You have entered!");
            } else {
                resp.getWriter().println("There is no such user");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
