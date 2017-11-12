package AjaxServlet;

import Helper.SenderEmail;
import Objects.User;
import Repositories.UserRepo;
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

        JSONObject jsonObject = new JSONObject();

        UserRepo userRepo = new UserRepo();
        User user = (User) request.getSession().getAttribute("current_user");

        if (username != null && username != "") {
            userRepo.updateUserUsername(user, username);
            user.setName(username);
        }
        else if (email != null && email != "") {
            if (userRepo.getUserByLogin(email) != null) {
                jsonObject.put("errors", true);
            }
            else {
                String confirmation = String.valueOf(java.util.UUID.randomUUID());
                SenderEmail senderEmail = new SenderEmail("https://localhost:8080/confirmation/" + confirmation, email);
                userRepo.updateUserConfirmation(user, confirmation);
                userRepo.updateUserLogin(user, email);
                user.setConfirmation(confirmation);
                user.setLogin(email);
            }
        }
        else if (newPassword != null && newPassword != "") {
            if (!user.getPassword().equals(lastPassword)) {
                jsonObject.put("errors", true);
            }
            else {
                userRepo.updateUserPassword(user, newPassword);
                user.setPassword(newPassword);
            }
        }

        request.getSession().setAttribute("current_user", user);


        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jsonObject.toString());
        response.getWriter().close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
