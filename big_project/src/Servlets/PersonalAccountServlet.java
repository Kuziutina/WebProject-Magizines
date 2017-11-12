package Servlets;

import Helper.Render;
import Objects.User;
import Repositories.UserRepo;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "PersonalAccountServlet")
public class PersonalAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getPathInfo().replaceFirst("/", "");

        UserRepo userRepo = new UserRepo();
        User another_user = userRepo.getEasyUserById(Integer.parseInt(id));

        if (another_user == null){
            response.sendRedirect("/error");
        }
        else {
            Map<String, Object> objects = new HashMap<>();
            objects.put("another_user", another_user);
            objects.put("user", request.getSession().getAttribute("current_user"));

            try {
                Render.render(response, objects, "/personal_account.ftl");
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }

    }
}
