package Servlets;

import Helper.Render;
import Models.Magazine;
import Models.User;
import DAO.DAOImpl.MagazineDAO;
import Services.MagazineService;
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
    private MagazineService magazineService;

    @Override
    public void init() throws ServletException {
        magazineService = new MagazineService(new MagazineDAO());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");

        List<Magazine> newer = magazineService.getNewest();
        List<Magazine> popular = magazineService.getPopular();
        int count = 0;
        if (user != null) {
            user.updateSubscriptions();
            count = user.getSubscriptions().size()/3;
        }


        Map<String, Object> objects = new HashMap<>();
        objects.put("user", user);
        objects.put("count", count);
        objects.put("popular", popular);
        objects.put("newer", newer);


        try {
            Render.render(response, objects, "/main.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
