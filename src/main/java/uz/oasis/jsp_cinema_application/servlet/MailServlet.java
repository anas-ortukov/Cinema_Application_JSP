package uz.oasis.jsp_cinema_application.servlet;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.entity.User;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@WebFilter(filterName = "Email", urlPatterns = "/client/confirmation.jsp")
public class MailServlet implements Filter {

    Random random = new Random();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        try {
            int code = random.nextInt(1010, 9090);
            sendMail(currentUser.getEmail(), code);
            filterChain.doFilter(request, response);
            request.getSession().setAttribute("code", code);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



    private static void sendMail(String email, Integer emailCode) throws MessagingException {
        Properties properties = new Properties();
        properties.put( "mail.smtp.host","smtp.gmail.com" );
        properties.put( "mail.smtp.port","465" );
        properties.put( "mail.smtp.ssl.enable","true");
        properties.put( "mail.smtp.auth","true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("azizortukov818@gmail.com", "wtsauhxgqdavizim");
            }
        });
        Message message = new MimeMessage( session );
        message.setSubject("Ticket Confirmation Code");
        message.setText(emailCode.toString());
        message.setFrom(new InternetAddress("qwrqr12345@gmail.com"));
        message.setRecipient( Message.RecipientType.TO,new InternetAddress(email) );
        Transport.send( message );
    }
}
