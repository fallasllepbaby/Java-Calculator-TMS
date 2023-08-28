package servlets;

import domains.User;
import services.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/guest/signup")
public class SignupServlet extends HttpServlet {
    private RegistrationService registrationService = new RegistrationService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        User user = new User(mail, password);
        registrationService.register(user);

        resp.getWriter().println("Your mail is " + mail);
    }
}
