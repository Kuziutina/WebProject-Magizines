package Servlets;

import Helper.Render;
import Objects.Magazine;
import Objects.User;
import Repositories.MagazineRepo;
import Repositories.UserRepo;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        String object = request.getParameter("object");
        String spage = request.getParameter("page");
        String scount = request.getParameter("count");
        Map<String, Object> objects = new HashMap<>();
        objects.put("user", request.getSession().getAttribute("current_user"));


        if (object == null || object.equals("magazine")) {
            int page, count;
            if (spage == null) page = 1;
            else page = Integer.parseInt(spage);

            if (scount == null) count = 20;
            else if (scount.equals("all")) count = Integer.MAX_VALUE;
            else count = Integer.parseInt(scount);

            int max_count;

            MagazineRepo magazineRepo = new MagazineRepo();
            List<Magazine> magazines = magazineRepo.getMagazineByPartName(query);

            max_count = magazines.size()/count;
            if (magazines.size() ==0 || magazines.size() % count != 0) max_count++;

            if (page > max_count) page = max_count;


            List<Magazine> result = new ArrayList<>();
            if (page*count >= magazines.size()) {
                result = magazines.subList((page-1)*count, magazines.size());
            }
            else {
                result = magazines.subList((page-1)* count, page*count);
            }

            objects.put("query", query);
            objects.put("page", page);
            objects.put("count", count);
            objects.put("max_count", max_count);
            objects.put("magazines", result);
        }
        else {
            UserRepo userRepo = new UserRepo();
            List<User> users = userRepo.getUserByPartName(query);

            objects.put("query", query);
            objects.put("users", users);
            objects.put("people", true);
        }

        try {
            Render.render(response, objects, "/search.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
