package uz.oasis.jsp_cinema_application.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.entity.Movie;
import uz.oasis.jsp_cinema_application.repo.MovieRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieService {

    public static void saveMovie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String genre = req.getParameter("genre");
        byte[] photo = null;
        if (req.getSession().getAttribute("moviePhoto")!= null) {
            photo = (byte[]) req.getSession().getAttribute("moviePhoto");
        }
        Movie movie = Movie.builder()
                .title(title)
                .description(description)
                .genres(new ArrayList<>(List.of(genre)))
                .photo(photo)
                .build();
        MovieRepo movieRepo = new MovieRepo();
        movieRepo.save(movie);
        req.getSession().removeAttribute("moviePhoto");
        resp.sendRedirect("/admin/movie.jsp");
    }

}
