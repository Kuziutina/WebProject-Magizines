package AjaxServlet;

import Objects.User;
import Repositories.UserRepo;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxExitServlet")
public class AjaxExitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String current = (String) request.getSession().getAttribute("current_user");

        UserRepo userRepo = new UserRepo();
        User user = userRepo.getUserByLogin(current);
        if (user.getCookie_login() != null && user.getCookie_login() != "") {
            userRepo.updateUserCookie(user, "");

            Cookie sessionCookie = new Cookie("userid", null);
            sessionCookie.setMaxAge(0);
            response.addCookie(sessionCookie);
        }

        request.getSession().setAttribute("current_user", null);
        JSONObject jo = new JSONObject();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().print(jo.toString());
        response.getWriter().close();

    }
}
