package Servlets;

import DAO.DAOImpl.MagazineIssueDAO;
import DAO.DAOImpl.UserDAO;
import Helper.Render;
import Models.Magazine;
import Models.MagazineIssue;
import Models.User;
import DAO.DAOImpl.MagazineDAO;
import Services.Interfaces.MagazineIssueServiceInterface;
import Services.Interfaces.MagazineServiceInterface;
import Services.MagazineIssueService;
import Services.MagazineService;
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

    private MagazineServiceInterface magazineService;
    private MagazineIssueServiceInterface magazineIssueService;

    @Override
    public void init() throws ServletException {
        magazineService = new MagazineService(new MagazineDAO());
        magazineIssueService = new MagazineIssueService(new MagazineIssueDAO());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path[] = request.getPathInfo().split("/");
        int id;
        Map<String, Object> objects;

        if (path.length == 2) {
            id = Integer.parseInt(path[1]);
            objects = new HashMap<>();

            Magazine magazine = magazineService.getMagazineById(id);

            int score = (int) Math.round(magazine.getScore());
            UserDAO userDAO = new UserDAO();
            User user = (User) request.getSession().getAttribute("current_user");
            if (user != null) {
                objects.put("has", userDAO.hasSubscription(user, id));
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
            id = Integer.parseInt(path[2]);


            MagazineIssue magazineIssue = magazineIssueService.getMagazineIssue(id);
            magazineIssueService.initMagazineIssue(magazineIssue);

            User user = (User) request.getSession().getAttribute("current_user");

            objects.put("user", user);
            objects.put("magazineIssue", magazineIssue);
            try {
                Render.render(response, objects, "/magazineIssue.ftl");
            } catch (TemplateException e) {
                e.printStackTrace();
            }

        }
        else if (path.length == 4 && path[3].equals("read")) {
            objects = new HashMap<>();
            id = Integer.parseInt(path[2]);

            MagazineIssue magazineIssue = magazineIssueService.getMagazineIssue(id);

            User user = (User) request.getSession().getAttribute("current_user");

            objects.put("user", user);
            objects.put("magazineIssue", magazineIssue);
            try {
                Render.render(response, objects, "/issue_read.ftl");
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }

        else {
            response.sendRedirect("/error");
        }


    }
}
