package uz.oasis.jsp_cinema_application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.service.SeatService;

import java.io.IOException;

@WebServlet(name = "Seat servlet", urlPatterns = "/seat/*")
public class SeatServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/seat/add")) {
            SeatService.setSeatToSession(req, resp);
        }else if (req.getRequestURI().equals("/seat/buy")) {
            SeatService.confirmation(req, resp);
        }
    }

}
