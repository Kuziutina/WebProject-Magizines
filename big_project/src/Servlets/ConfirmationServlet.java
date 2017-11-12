package Servlets;

import Objects.User;
import Repositories.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmationServlet")
public class ConfirmationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String confirmation = request.getPathInfo().replaceFirst("/", "");

        UserRepo userRepo = new UserRepo();
        User user = userRepo.getUserByConfirmation(confirmation);

        if (user != null) {
            userRepo.updateUserConfirmation(user, "");
            request.getSession().setAttribute("current_user", user);
        }

        response.sendRedirect("/about");
    }
}
