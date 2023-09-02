package servlets;

import domains.Operation;
import services.CalculatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GuestServlet", urlPatterns = "/guest")
public class GuestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("1) Login  (localhost:8080/guest/login?mail=example@bk.ru&password=12345)");
        resp.getWriter().println("2) Sign up  (localhost:8080/guest/signup?mail=example@bk.ru&password=12345)");
    }
}
