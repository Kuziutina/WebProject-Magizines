package Servlets;

import Helper.Render;
import Models.User;
import Repositories.UserRepo;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FriendsServlet")
public class FriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");

        UserRepo userRepo = new UserRepo();
        List<User> friends = userRepo.getFriends(user);
        Map<String, Object> objects = new HashMap<>();

        objects.put("friends", friends);
        objects.put("user", user);
        try {
            Render.render(response, objects, "/my_friends.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
