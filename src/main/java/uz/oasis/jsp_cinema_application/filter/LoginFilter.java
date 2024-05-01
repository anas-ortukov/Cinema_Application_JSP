package uz.oasis.jsp_cinema_application.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.service.UserService;

import java.io.IOException;

@WebFilter(filterName = "Login check Filter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //request for login page and it's servlets are allowed
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURI().equals("/login.jsp") || request.getRequestURI().equals("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        //Check account for other servlets requests
        UserService.checkCookieAndSession(filterChain, request, response);
    }




}
