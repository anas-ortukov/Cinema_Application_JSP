package uz.oasis.jsp_cinema_application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.service.UserService;

import java.io.IOException;

@WebServlet(name = "Auth servlet", urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //check user details
        UserService.checkUserDetails(req, resp);
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("currentUser");
        resp.sendRedirect("/");
    }
}
