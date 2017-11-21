package AjaxServlet;

import Models.User;
import Repositories.UserRepo;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxSubscribeServlet", urlPatterns = "/ajax_subscribe_servlet")
public class AjaxSubscribeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");
        int magazine_id = Integer.parseInt(request.getParameter("magazine_id"));
        boolean has = Boolean.parseBoolean(request.getParameter("has"));

        UserRepo userRepo = new UserRepo();
        if (has) {
            user.getSubscriptions();
            userRepo.addSubscription(user, magazine_id);
        }
        else {
            userRepo.deleteSubscription(user, magazine_id);
        }

        JSONObject jo = new JSONObject();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jo.toString());
        response.getWriter().close();

    }
}
