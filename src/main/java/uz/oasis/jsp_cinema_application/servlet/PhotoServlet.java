package uz.oasis.jsp_cinema_application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.oasis.jsp_cinema_application.entity.Movie;
import uz.oasis.jsp_cinema_application.repo.MovieRepo;

import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "Movie Photo servlet", urlPatterns = "/moviePhoto")
public class PhotoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part moviePhoto = req.getPart("moviePhoto");
        if (moviePhoto != null) {
            req.getSession().setAttribute("moviePhoto", moviePhoto.getInputStream().readAllBytes());
            resp.sendRedirect("/admin/addMovie.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            MovieRepo movieRepo = new MovieRepo();
            UUID movieId = UUID.fromString(id);
            Movie movie = movieRepo.findById(movieId);
            resp.getOutputStream().write(movie.getPhoto());
            return;
        }
        Object object = req.getSession().getAttribute("moviePhoto");

        if (object != null) {
            byte[] moviePhoto = (byte[]) req.getSession().getAttribute("moviePhoto");
            resp.getOutputStream().write(moviePhoto);
            return;
        }
    }
}
