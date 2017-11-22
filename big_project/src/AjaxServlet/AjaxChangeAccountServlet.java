package AjaxServlet;

import DAO.DAOImpl.UserDAO;
import Helper.MD5Hash;
import Models.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxChangeAccountServlet")
public class AjaxChangeAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String newPassword = request.getParameter("new_password");
        String lastPassword = request.getParameter("last_password");
        boolean change_email = false;
        String confirmation = "";

        JSONObject jsonObject = new JSONObject();

        UserDAO userDAO = new UserDAO();
        User user = (User) request.getSession().getAttribute("current_user");

        if (username != null && username != "") {
            if (userDAO.getByName(username) == null) {
                userDAO.updateUserUsername(user, username);
                user.setName(username);
            }
            else {
                jsonObject.put("username_used", "");
            }
        }
        else if (email != null && email != "") {
            if (userDAO.getByLogin(email) != null) {
                jsonObject.put("errors", true);
            }
            else {
                change_email = true;
                confirmation = "";
                userDAO.updateUserConfirmation(user, confirmation);
                userDAO.updateUserLogin(user, email);
                user.setConfirmation(confirmation);
                user.setLogin(email);
            }
        }
        else if (newPassword != null && newPassword != "") {
            if (!user.getPassword().equals(MD5Hash.getHash(lastPassword))) {
                jsonObject.put("errors", true);
            }
            else {
                newPassword = MD5Hash.getHash(newPassword);
                userDAO.updateUserPassword(user, newPassword);
                user.setPassword(newPassword);
            }
        }

        request.getSession().setAttribute("current_user", user);


        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jsonObject.toString());
        response.getWriter().close();

//        if (change_email) {
//            SenderEmail senderEmail = new SenderEmail("localhost:8080/confirmation/" + confirmation, email);
//            senderEmail.run();
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
