package uz.oasis.jsp_cinema_application.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import uz.oasis.jsp_cinema_application.entity.User;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class MailService {

    static Random random = new Random();
    static Session mailSession;

    static {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        mailSession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("azizortukov818@gmail.com", "wtsauhxgqdavizim");
            }
        });
    }

    @SneakyThrows
    public static void sendConfirmationCode(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User currentUser = (User) servletRequest.getSession().getAttribute("currentUser");
        int code = random.nextInt(1010, 9090);
        sendMail(currentUser.getEmail(), code);
        filterChain.doFilter(servletRequest, servletResponse);
        servletRequest.getSession().setAttribute("code", code);
    }

    private static void sendMail(String email, Integer emailCode) throws MessagingException {
        Message message = new MimeMessage(mailSession);
        message.setSubject("Ticket Confirmation Code");
        message.setText(emailCode.toString());
        message.setFrom(new InternetAddress("qwrqr12345@gmail.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        Transport.send(message);
    }

}
