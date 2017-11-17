package Servlets;

import Helper.Render;
import Objects.Magazine;
import Objects.User;
import Repositories.MagazineRepo;
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

@WebServlet(name = "MainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");
        MagazineRepo magazineRepo = new MagazineRepo();
        List<Magazine> popular;
        List<Magazine> newer;
        int count = user.getSubscriptions().size()/3;
        user.getSubscriptions();

        Map<String, Object> objects = new HashMap<>();
        objects.put("user", user);
        objects.put("count", count);


        try {
            Render.render(response, objects, "/main.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
