package AjaxServlet;

import Objects.User;
import Repositories.UserRepo;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxFriendServlet")
public class AjaxFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");
        String id = request.getParameter("delete_id");
        UserRepo userRepo = new UserRepo();

        userRepo.deleteFriend(user, Integer.parseInt(id));

        JSONObject jsonObject = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jsonObject.toString());
        response.getWriter().close();
    }
}
