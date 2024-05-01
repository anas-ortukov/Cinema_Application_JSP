package uz.oasis.jsp_cinema_application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.service.MovieService;

import java.io.IOException;

@WebServlet(name = "Movie servlet", urlPatterns = "/movie/*")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/movie/save")) {
            MovieService.saveMovie(req, resp);
        }
    }


}
