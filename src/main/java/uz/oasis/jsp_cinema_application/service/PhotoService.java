package uz.oasis.jsp_cinema_application.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.oasis.jsp_cinema_application.entity.Movie;
import uz.oasis.jsp_cinema_application.repo.MovieRepo;

import java.io.IOException;
import java.util.UUID;

public class PhotoService {

    public static void setPhotoToSession(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Part moviePhoto = req.getPart("moviePhoto");
        if (moviePhoto != null) {
            req.getSession().setAttribute("moviePhoto", moviePhoto.getInputStream().readAllBytes());
            resp.sendRedirect("/admin/addMovie.jsp");
        }
    }

    public static void sendPhotoArrays(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        }
    }

}
