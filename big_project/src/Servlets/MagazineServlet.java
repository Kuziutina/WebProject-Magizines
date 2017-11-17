package Servlets;

import Helper.Render;
import Objects.Magazine;
import Objects.MagazineCopy;
import Objects.User;
import Repositories.MagazineCopyRepo;
import Repositories.MagazineRepo;
import Repositories.UserRepo;
import com.sun.org.apache.regexp.internal.RE;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MagazineServlet")
public class MagazineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path[] = request.getPathInfo().split("/");
        Map<String, Object> objects;
        if (path.length == 2) {
            objects = new HashMap<>();
            int id = Integer.parseInt(path[1]);
            MagazineRepo magazineRepo = new MagazineRepo();
            Magazine magazine = magazineRepo.getMagazineById(id);
            magazine.getCopies();
            magazine.getReviews();
            int score = (int) Math.round(magazine.getScore());
            UserRepo userRepo = new UserRepo();
            User user = (User) request.getSession().getAttribute("current_user");
            if (user != null) {
                objects.put("has", userRepo.hasSubscription(user, id));
            }
            else {
                objects.put("has", false);
            }

            objects.put("user", user);
            objects.put("magazine", magazine);
            objects.put("score", score);

            try {
                Render.render(response, objects, "/magazine.ftl");
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
        else if (path.length == 3) {
            objects = new HashMap<>();
            int id = Integer.parseInt(path[2]);
            MagazineCopyRepo magazineCopyRepo = new MagazineCopyRepo();
            MagazineCopy magazineCopy = magazineCopyRepo.getMagazineCopyById(id);
            magazineCopy.getReviews();
            User user = (User) request.getSession().getAttribute("current_user");

            objects.put("user", user);
            objects.put("magazineCopy", magazineCopy);
            try {
                Render.render(response, objects, "/magazineIssue.ftl");
            } catch (TemplateException e) {
                e.printStackTrace();
            }

        }


    }
}
