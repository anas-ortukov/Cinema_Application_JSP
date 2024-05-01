package uz.oasis.jsp_cinema_application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.service.PhotoService;

import java.io.IOException;

@MultipartConfig
@WebServlet(name = "Movie Photo servlet", urlPatterns = "/moviePhoto")
public class PhotoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PhotoService.setPhotoToSession(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PhotoService.sendPhotoArrays(req, resp);
    }


}
