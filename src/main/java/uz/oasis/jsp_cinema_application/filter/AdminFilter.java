package uz.oasis.jsp_cinema_application.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.entity.User;

import java.io.IOException;

@WebFilter(filterName = "Admin page Filter", urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object object = request.getSession().getAttribute("currentUser");
        if (object == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        User currentUser = (User) object;
        if (currentUser.getEmail().equals("dong.lehner@yahoo.com")) {
            filterChain.doFilter(request, response);
            return;
        }
        response.sendRedirect("/");
    }
}
