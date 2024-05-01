package uz.oasis.jsp_cinema_application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.service.SessionService;

import java.io.IOException;

@WebServlet(name = "Session Servlet", urlPatterns = "/session/*")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/session/save")) {
            SessionService.checkTimeAndSave(req, resp);
        }
    }

}
