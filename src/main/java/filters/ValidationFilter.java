package filters;

import domains.User;
import services.MailValidationService;
import services.PasswordValidationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"LoginServlet", "SignupServlet"})
public class ValidationFilter extends HttpFilter {
    private MailValidationService mailValidationService = new MailValidationService();
    private PasswordValidationService passwordValidationService = new PasswordValidationService();
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        if (mailValidationService.validate(mail)) {
            res.sendRedirect("http://localhost:8080/wrongMail");
        } else if (passwordValidationService.validate(password)) {
            res.sendRedirect("http://localhost:8080/wrongPassword");
        } else {
            chain.doFilter(req, res);
        }
    }
}
