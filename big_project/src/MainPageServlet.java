import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;

public class MainPageServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        response.setContentType("text/html");
//        response.getWriter().println("<a href=\"/magazine\" value=\"23\">keks</a>");
        try {
            Render.render(response, new HashMap<>(), "/login.ftl");
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
