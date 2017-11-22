package Servlets;

import DAO.DAOImpl.UserDAO;
import Models.User;

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

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getByConfirmation(confirmation);

        if (user != null) {
            userDAO.updateUserConfirmation(user, "");
            user.setConfirmation("");
            request.getSession().setAttribute("current_user", user);
        }

        response.sendRedirect("/about");
    }
}
