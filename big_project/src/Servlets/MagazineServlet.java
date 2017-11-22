package Servlets;

import DAO.DAOImpl.MagazineIssueDAO;
import DAO.DAOImpl.UserDAO;
import Helper.Render;
import Models.Magazine;
import Models.MagazineIssue;
import Models.User;
import DAO.DAOImpl.MagazineDAO;
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
            MagazineDAO magazineDAO = new MagazineDAO();
            Magazine magazine = magazineDAO.find((long) id);
            magazine.getCopies();
            magazine.getReviews();
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
            int id = Integer.parseInt(path[2]);
            MagazineIssueDAO magazineIssueDAO = new MagazineIssueDAO();
            MagazineIssue magazineIssue = magazineIssueDAO.find(id);
            magazineIssue.getReviews();
            User user = (User) request.getSession().getAttribute("current_user");

            objects.put("user", user);
            objects.put("magazineIssue", magazineIssue);
            try {
                Render.render(response, objects, "/magazineIssue.ftl");
            } catch (TemplateException e) {
                e.printStackTrace();
            }

        }
        else if (path.length == 4) {
            objects = new HashMap<>();
            int id = Integer.parseInt(path[2]);
            MagazineIssueDAO magazineIssueDAO = new MagazineIssueDAO();
            MagazineIssue magazineIssue = magazineIssueDAO.find(id);
            User user = (User) request.getSession().getAttribute("current_user");

            objects.put("user", user);
            objects.put("magazineIssue", magazineIssue);
            try {
                Render.render(response, objects, "/issue_read.ftl");
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }


    }
}
