package uz.oasis.jsp_cinema_application.repo;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;
import uz.oasis.jsp_cinema_application.entity.User;

import java.util.Optional;
import java.util.UUID;

public class UserRepo extends BaseRepo <User, UUID> {

    public Optional<User> findByDetails(String email, String password) {
        try {
            TypedQuery<User> query = em.createQuery("from User where email = :email", User.class);
            query.setParameter("email", email);
            User singleResult = query.getSingleResult();
            if (BCrypt.checkpw(password, singleResult.getPassword())) {
                return Optional.of(singleResult);
            }
        }catch (NoResultException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
