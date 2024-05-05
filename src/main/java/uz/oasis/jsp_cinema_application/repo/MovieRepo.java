package uz.oasis.jsp_cinema_application.repo;

import jakarta.persistence.TypedQuery;
import uz.oasis.jsp_cinema_application.entity.Movie;

import java.util.List;
import java.util.UUID;

public class MovieRepo extends BaseRepo<Movie, UUID> {
    public Integer numMovieSessions(Movie movie) {
        TypedQuery<Long> query =
                em.createQuery("select count(session) from Session session where session.movie.id = :id", Long.class);
        query.setParameter("id", movie.getId());
        return query.getSingleResult().intValue();
    }


    @Override
    public List<Movie> findAll() {
        return em.createQuery("select m from Movie m where m.isArchived = false", Movie.class).getResultList();
    }
}
