package uz.oasis.jsp_cinema_application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import uz.oasis.jsp_cinema_application.entity.User;
import uz.oasis.jsp_cinema_application.repo.UserRepo;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Auth servlet", urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {

    UserRepo userRepo = new UserRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (BCrypt.checkpw(password, user.getPassword())) {
                req.getSession().setAttribute("currentUser", user);
                resp.sendRedirect("/client/index.jsp");
                return;
            }
        }
        resp.sendRedirect("/login.jsp?multiple=true");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("currentUser");
        resp.sendRedirect("/");
    }
}
