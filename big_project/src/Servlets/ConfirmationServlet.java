package Servlets;

import DAO.DAOImpl.UserDAO;
import DAO.Interfaces.UserDAOInterface;
import Models.User;
import Services.Interfaces.UserServiceInterface;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmationServlet")
public class ConfirmationServlet extends HttpServlet {
    private UserServiceInterface userService;
    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserDAO());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String confirmation = request.getPathInfo().replaceFirst("/", "");

        User user = userService.getByConfirmation(confirmation);

        if (user != null) {
            userService.changeConfirmation(user, confirmation);
            request.getSession().setAttribute("current_user", user);
        }

        response.sendRedirect("/about");
    }
}
