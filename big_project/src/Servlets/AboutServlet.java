package Servlets;

import Helper.Render;
import Models.User;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "AboutServlet")
public class AboutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* User newUser = new User();
        newUser.setName(request.getParameter("username"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setLogin(request.getParameter("mail"));

        UserDAO userRepo = new UserDAO();
        if (!userRepo.addUser(newUser)) {
            userRepo.addUser(newUser);
        }

        try {
            Render.render(response, new HashMap<>(), "/about.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<>();
        User current_user = (User) request.getSession().getAttribute("current_user");

        if (current_user != null) {
            map.put("user", current_user);
        }
        try {
            Render.render(response, map, "/about.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
