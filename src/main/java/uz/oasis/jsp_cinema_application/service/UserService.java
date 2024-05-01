package uz.oasis.jsp_cinema_application.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.entity.User;
import uz.oasis.jsp_cinema_application.repo.UserRepo;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static uz.oasis.jsp_cinema_application.repo.BaseRepo.em;

public class UserService {

    static UserRepo userRepo = new UserRepo();

    public static void checkUserDetails(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<User> userOptional = userRepo.findByDetails(email, password);
        if (userOptional.isPresent()) {
            req.getSession().setAttribute("currentUser", userOptional.get());
            if (req.getParameter("remember_me") != null) {
                setCookie(resp, userOptional.get());
            }
            resp.sendRedirect("/client/index.jsp");
            return;
        }
        resp.sendRedirect("/login.jsp?multiple=true");
    }

    private static void setCookie(HttpServletResponse resp, User user) {
        Cookie cookie = new Cookie("userId", user.getId().toString());
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setSecure(false);
        resp.addCookie(cookie);
    }

    public static void checkCookieAndSession(FilterChain filterChain, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object object = request.getSession().getAttribute("currentUser");
        if (object == null) {
            Optional<User> userByCookie = getUserByCookie(request.getCookies());
            if (userByCookie.isPresent()) {
                request.getSession().setAttribute("currentUser", userByCookie.get());
                filterChain.doFilter(request, response);
                return;
            }
            response.sendRedirect("/login.jsp");
            return;
        }
        if (request.getRequestURI().equals("/")) {
            response.sendRedirect("/client/index.jsp");
            return;
        }
        filterChain.doFilter(request, response);
    }

    public static Optional<User> getUserByCookie(Cookie[] cookies) {
        if (cookies == null) {
            return Optional.empty();
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                UUID userId = UUID.fromString(cookie.getValue());
                User user = em.find(User.class, userId);
                return Optional.ofNullable(user);
            }
        }
        return Optional.empty();
    }

}
