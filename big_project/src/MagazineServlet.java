import java.io.IOException;

public class MagazineServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String id = (String) request.getAttribute("value");
        response.setContentType("text/html");
        response.getWriter().println("<a href=\"/magazine/25\">keiks edition " + id + "</a>");
        response.getWriter().close();

    }
}
