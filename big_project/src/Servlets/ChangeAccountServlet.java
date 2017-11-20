package Servlets;

import Helper.Render;
import Objects.User;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ChangeAccountServlet")
public class ChangeAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("current_user");

        Map<String, Object> objects = new HashMap<>();
        objects.put("user", user);

        try {
            Render.render(response, objects, "/change_account.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
