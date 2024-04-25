package uz.oasis.jsp_cinema_application.DB;

import com.github.javafaker.Faker;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.mindrot.jbcrypt.BCrypt;
import uz.oasis.jsp_cinema_application.entity.Hall;
import uz.oasis.jsp_cinema_application.entity.User;
import uz.oasis.jsp_cinema_application.repo.HallRepo;
import uz.oasis.jsp_cinema_application.repo.UserRepo;

import java.util.UUID;

@WebListener
public class DataInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Faker faker = new Faker();
        UserRepo userRepo = new UserRepo();
        HallRepo hallRepo = new HallRepo();
        if (userRepo.findAll().isEmpty()) {
            int i = 0;
            String password = "123";
            String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
            while (i < 100) {
                User user = User.builder()
                        .name(faker.name().firstName())
                        .email(faker.internet().emailAddress())
                        .password(hashpw)
                        .build();
                userRepo.save(user);
                i++;
            }
        }
        if (hallRepo.findAll().isEmpty()) {
            int i = 1;
            while (i <= 5) {
                Hall hall = Hall.builder()
                        .name("A" + i)
                        .build();
                hallRepo.save(hall);
                i++;
            }
        }
        ServletContextListener.super.contextInitialized(sce);
    }
}
