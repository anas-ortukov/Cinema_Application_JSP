package uz.oasis.jsp_cinema_application.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import uz.oasis.jsp_cinema_application.entity.Movie;
import uz.oasis.jsp_cinema_application.entity.enums.Genre;
import uz.oasis.jsp_cinema_application.repo.MovieRepo;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class MovieService {

    static MovieRepo movieRepo = new MovieRepo();

    public static void saveMovie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String[] genres = req.getParameterValues("genres");
        byte[] photo = null;
        if (req.getSession().getAttribute("moviePhoto")!= null) {
            photo = (byte[]) req.getSession().getAttribute("moviePhoto");
        }
        Movie movie = Movie.builder()
                .title(title)
                .description(description)
                .genres(Arrays.asList(genres))
                .photo(photo)
                .build();
        movieRepo.save(movie);
        req.getSession().removeAttribute("moviePhoto");
        resp.sendRedirect("/admin/movie.jsp");
    }

    public static String formatGenre(Genre genre) {
        return genre.name().substring(0, 1).toUpperCase() + genre.name().substring(1).toLowerCase();
    }

    @SneakyThrows
    public static void archiveMovie(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        if (id != null) {
            try {
                Movie movie = movieRepo.findById(UUID.fromString(id));
                movieRepo.begin();
                movie.setArchived(true);
                movieRepo.commit();
            }catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/admin/movie.jsp");
    }
}
