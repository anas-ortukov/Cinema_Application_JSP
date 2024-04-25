package uz.oasis.jsp_cinema_application.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "Login check Filter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURI().equals("/login.jsp") || request.getRequestURI().equals("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        Object object = request.getSession().getAttribute("currentUser");
        if (object == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        if (request.getRequestURI().equals("/")) {
            response.sendRedirect("/client/index.jsp");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
