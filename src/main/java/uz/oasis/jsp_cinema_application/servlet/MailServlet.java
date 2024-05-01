package uz.oasis.jsp_cinema_application.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.service.MailService;

import java.io.IOException;

@WebFilter(filterName = "Email", urlPatterns = "/client/confirmation.jsp")
public class MailServlet implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MailService.sendConfirmationCode((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
    }

}
