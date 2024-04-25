package uz.oasis.jsp_cinema_application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.entity.Movie;
import uz.oasis.jsp_cinema_application.repo.MovieRepo;

import java.io.IOException;

@WebServlet(name = "Movie servlet", urlPatterns = "/movie/*")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/movie/save")) {
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
                    .genre(genre)
                    .photo(photo)
                    .build();
            MovieRepo movieRepo = new MovieRepo();
            movieRepo.save(movie);
            req.getSession().removeAttribute("moviePhoto");
            resp.sendRedirect("/admin/movie.jsp");
        }
    }
}
