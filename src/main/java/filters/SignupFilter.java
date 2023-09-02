package filters;

import domains.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"CalculateServlet", "HistoryServlet", "LogoutServlet"})
public class SignupFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println(req.getSession().getAttribute("user"));
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect("http://localhost:8080/guest");
        }
    }
}